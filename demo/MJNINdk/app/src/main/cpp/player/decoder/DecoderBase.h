//
// Created by wei tao on 2021/4/26.
//

#ifndef MJNINDK_DECODERBASE_H
#define MJNINDK_DECODERBASE_H

extern "C" {
#include <libavcodec/avcodec.h>
#include <libavformat/avformat.h>
#include <libavutil/frame.h>
#include <libavutil/time.h>
#include <libavcodec/jni.h>
};


#include "Decoder.h"

#define MAX_PATH   2048
class DecoderBase : public Decoder {
public:
    DecoderBase(){}
    virtual ~DecoderBase(){};
    //开始播放
    virtual void Start();
    //暂停播放
    virtual void Pause();
    //停止
    virtual void Stop();
    //获取时长
    virtual float GetDuration()
    {
        //ms to s
        return m_Duration * 1.0f / 1000;
    }

protected:
    virtual int Init(const char* url, AVMediaType mediaType);
    virtual void UnInit();
private:
    //总时长 ms
    long m_Duration = 0;
    char m_Url[MAX_PATH] = {0};
    AVMediaType m_MediaType = AVMEDIA_TYPE_UNKNOWN;
};
#endif //MJNINDK_DECODERBASE_H
