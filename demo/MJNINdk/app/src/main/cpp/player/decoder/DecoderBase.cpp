//
// Created by wei tao on 2021/4/26.
//
#include "DecoderBase.h"
#include "LogUtil.h"

void DecoderBase::Start() {
    if(m_Thread == nullptr) {
        StartDecodingThread();
    } else {
        std::unique_lock<std::mutex> lock(m_Mutex);
        m_DecoderState = STATE_DECODING;
        m_Cond.notify_all();
    }
}

void DecoderBase::StartDecodingThread()
{
    m_Thread = new thread(DoAVDecoding,this);
}

void DecoderBase::DoAVDecoding(DecoderBase *decoder)
{
    LOGCATD("DecoderBase::DoAVDecoding");
    do
    {
        if(decoder->InitFFDecoder() != 0)
        {
            break;
        }
        decoder->OnDecoderReady();
        decoder->DecodingLoop();
    } while (false);
}

int DecoderBase::InitFFDecoder()
{
    int result = -1;
    do
    {
        //1,创建封装格式上下文
        m_AVFormatContext = avformat_alloc_context();

        //2.打开文件
        if(avformat_open_input(&m_AVFormatContext,m_Url,NULL,NULL) != 0)
        {
            LOGCATE("DecoderBase::InitFFDecoder avformat_open_input fail.");
            break;
        }

        //3,获取音视频流
        if(avformat_find_stream_info(m_AVFormatContext,NULL) < 0)
        {
            LOGCATE("DecoderBase::InitFFDecoder avformat_find_stream_info fail.");
            break;
        }

        //4.获取音视频流索引
        for (int i = 0; i < m_AVFormatContext->nb_streams; ++i) {
            if(m_AVFormatContext->streams[i]->codecpar->codec_type == m_MediaType){
                m_StreamIndex = i;
                break;
            }
        }

    } while (false);
}

void DecoderBase::Pause() {
    std::unique_lock<std::mutex> lock(m_Mutex);
    m_DecoderState = STATE_PAUSE;
}

void DecoderBase::Stop() {
    LOGCATE("DecoderBase::Stop");
    std::unique_lock<std::mutex> lock(m_Mutex);
    m_DecoderState = STATE_STOP;
    m_Cond.notify_all();
}

void DecoderBase::SeekToPosition(float position) {
    LOGCATE("DecoderBase::SeekToPosition position=%f", position);
    std::unique_lock<std::mutex> lock(m_Mutex);
    m_SeekPosition = position;
    m_DecoderState = STATE_DECODING;
    m_Cond.notify_all();
}

float DecoderBase::GetCurrentPosition() {
    //std::unique_lock<std::mutex> lock(m_Mutex);//读写保护
    //单位 ms
    return m_CurTimeStamp;
}

int DecoderBase::Init(const char *url, AVMediaType mediaType)
{
    LOGCATD("DecoderBase::Init url=%s, mediaType=%d", url, mediaType);
    strcpy(m_Url,url);
    m_MediaType = mediaType;
    return 0;
}

void DecoderBase::UnInit() {
    LOGCATE("DecoderBase::UnInit m_MediaType=%d", m_MediaType);
    if(m_Thread) {
        Stop();
        /**
         *  join()操作是在std::thread t(func)后“某个”合适的地方调用，
         *  其作用是回收对应创建的线程的资源，避免造成资源的泄露。
         *
         *  由于join是等待被创建线程的结束，并回收它的资源。
         *  因此，join的调用位置就比较关键。比如，以下的调用位置都是错误的。
         *
         */
        m_Thread->join();
        delete m_Thread;
        m_Thread = nullptr;
    }
    LOGCATE("DecoderBase::UnInit end, m_MediaType=%d", m_MediaType);
}

