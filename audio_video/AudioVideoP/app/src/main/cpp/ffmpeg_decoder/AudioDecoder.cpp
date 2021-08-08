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
    LOGCATD(TAG,"~AudioDecoder");
    if(NULL != decoderFilePath){
        delete[] decoderFilePath;
        decoderFilePath = NULL;
    }
}

int AudioDecoder::getMusicMeta(const char *fileString, int *metaData) {
    init(fileString);
    int sampleRate = avCodecContext->sample_rate;
    LOGCATD(TAG,"getMusicMeta sampleRate is %d", sampleRate);
    int bitRate = avCodecContext->bit_rate;
    LOGCATD(TAG,"getMusicMeta bitRate is %d", bitRate);
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
    LOGCATD(TAG,"enter init");
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
    LOGCATD(TAG,"init file: %s", filePath);


}