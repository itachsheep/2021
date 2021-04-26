//
// Created by wei tao on 2021/4/26.
//

#ifndef MJNINDK_VIDEODECODER_H
#define MJNINDK_VIDEODECODER_H
extern "C" {
#include <libavutil/imgutils.h>
#include <libswscale/swscale.h>
#include <libavcodec/jni.h>
};

#include "DecoderBase.h"
class VideoDecoder : public DecoderBase {
public:
    VideoDecoder(char* url)
    {
        Init(url,AVMEDIA_TYPE_VIDEO);
    }

    virtual ~VideoDecoder()
    {
        UnInit();
    }
};
#endif //MJNINDK_VIDEODECODER_H
