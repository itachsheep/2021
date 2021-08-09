//
// Created by wei tao on 2021/8/7.
//
#include "AudioDecoder.h"

#define TAG __FILE_NAME__

AudioDecoder::AudioDecoder() {
    this->seek_seconds = 0.0f;
    this->seek_req = false;
    this->seek_resp = false;
    decoderFilePath = NULL;
}

AudioDecoder::~AudioDecoder() {
    LOGCATD("%s ~AudioDecoder",TAG);
    if(NULL != decoderFilePath){
        delete[] decoderFilePath;
        decoderFilePath = NULL;
    }
}

int AudioDecoder::getMusicMeta(const char *fileString, int *metaData) {
    init(fileString);
    int sampleRate = avCodecContext->sample_rate;
    LOGCATD("%s getMusicMeta sampleRate is %d",TAG, sampleRate);
    int bitRate = avCodecContext->bit_rate;
    LOGCATD("%s getMusicMeta bitRate is %d",TAG, bitRate);
    destroy();
    metaData[0] = sampleRate;
    metaData[1] = bitRate;
    return 0;
}

void AudioDecoder::init(const char *fileString, int packetBufferSizeParam) {
    init(fileString);
    packetBufferSize = packetBufferSizeParam;
}

int AudioDecoder::init(const char *filePath) {
    LOGCATD("%s enter init",TAG);
    audioBuffer = NULL;
    position = -1.0f;
    audioBufferCursor = 0;
    audioBufferSize = 0;
    swrContext = NULL;
    swrBuffer = NULL;
    swrBufferSize = 0;
    seek_success_read_frame_success = true;
    isNeedFirstFrameCorrectFlag = true;
    firstFrameCorrectionInSecs = 0.0f;

    avcodec_register_all();
    av_register_all();
    avFormatContext = avformat_alloc_context();

    // 打开输入文件
    LOGCATD("%s init file: %s",TAG, filePath);
    if(NULL != decoderFilePath) {
        int length = strlen(filePath);
        decoderFilePath = new char[length + 1];
        //由于最后一个是'\0' 所以memset的长度要设置为length+1
        memset(decoderFilePath, 0, length + 1);
        memcpy(decoderFilePath, filePath, length + 1);
    }

    int result = avformat_open_input(&avFormatContext,decoderFilePath,NULL,NULL);
    if(result !=0) {
        LOGCATE("%s avformat_open_input failed",TAG);
        return -1;
    }

    avFormatContext->max_analyze_duration = 50000;
    //检查在文件中的流的信息
    result = avformat_find_stream_info(avFormatContext, NULL);
    if(result < 0) {
        LOGCATE("%s find_stream_info failed",TAG);
        return -1;
    }

    //查找音频流
    stream_index = av_find_best_stream(avFormatContext, AVMEDIA_TYPE_AUDIO, -1, -1, NULL, 0);
    LOGCATD("%s stream_index is %d", TAG,stream_index);

    // 没有音频
    if (stream_index == -1) {
        LOGCATE("%s no audio stream",TAG);
        return -1;
    }

    //音频流
    AVStream* audioStream = avFormatContext->streams[stream_index];
    if (audioStream->time_base.den && audioStream->time_base.num){
        timeBase = av_q2d(audioStream->time_base);
    } else if (audioStream->codec->time_base.den && audioStream->codec->time_base.num) {
        timeBase = av_q2d(audioStream->codec->time_base);
    }

    //获得音频流的解码器上下文
    avCodecContext = audioStream->codec;
    // 根据解码器上下文找到解码器
    LOGCATD("%s avCodecContext->codec_id is %d AV_CODEC_ID_AAC is %d",
            TAG,avCodecContext->codec_id, AV_CODEC_ID_AAC);
    AVCodec * avCodec = avcodec_find_decoder(avCodecContext->codec_id);
    if (avCodec == NULL) {
        LOGCATE("%s avCodec is null ",TAG);
        return -1;
    }

    // 打开解码器
    result = avcodec_open2(avCodecContext, avCodec, NULL);
    if (result < 0) {
        LOGCATE("%s avcodec_open2 failed is %d",TAG, result);
        return -1;
    }

    //4、判断是否需要resampler
    if (!audioCodecIsSupported()) {
        LOGCATD("%s because of audio Codec Is Not Supported so we will init swresampler...",TAG);
        /**
         * 初始化resampler
         * @param s               Swr context, can be NULL
         * @param out_ch_layout   output channel layout (AV_CH_LAYOUT_*)
         * @param out_sample_fmt  output sample format (AV_SAMPLE_FMT_*).
         * @param out_sample_rate output sample rate (frequency in Hz)
         * @param in_ch_layout    input channel layout (AV_CH_LAYOUT_*)
         * @param in_sample_fmt   input sample format (AV_SAMPLE_FMT_*).
         * @param in_sample_rate  input sample rate (frequency in Hz)
         * @param log_offset      logging level offset
         * @param log_ctx         parent logging context, can be NULL
         */
        swrContext = swr_alloc_set_opts(NULL, av_get_default_channel_layout(OUT_PUT_CHANNELS), AV_SAMPLE_FMT_S16, avCodecContext->sample_rate,
                                        av_get_default_channel_layout(avCodecContext->channels), avCodecContext->sample_fmt, avCodecContext->sample_rate, 0, NULL);
        if (!swrContext || swr_init(swrContext)) {
            if (swrContext) {
                swr_free(&swrContext);
            }
            avcodec_close(avCodecContext);
            LOGCATE("%s init resampler failed...",TAG);
            return -1;
        }
    }

    LOGCATD("%s channels is %d sampleRate is %d",
            TAG,avCodecContext->channels, avCodecContext->sample_rate);
    pAudioFrame = avcodec_alloc_frame();
//	LOGI("leave AccompanyDecoder::init");
    return 1;
}

bool AudioDecoder::audioCodecIsSupported() {
    bool res = (avCodecContext->sample_fmt == AV_SAMPLE_FMT_S16);
    LOGCATD("%s audioCodecIsSupported res = %d",TAG,res);
    return res;
}