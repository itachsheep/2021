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
#include "LogUtil.h"
#include "VideoRender.h"
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

    int GetVideoWidth()
    {
        return m_VideoWidth;
    }

    int GetVideoHeight()
    {
        return m_VideoHeight;
    }

    void SetVideoRender(VideoRender* videoRender)
    {
        m_VideoRender = videoRender;
    }

    static long GetVideoDecoderTimestampForAVSync(void* context);

private:
    virtual void OnDecoderReady();
    virtual void OnDecoderDone();
    virtual void OnFrameAvailable(AVFrame *frame);

    const AVPixelFormat DST_PIXEL_FORMAT = AV_PIX_FMT_RGBA;

    int m_VideoWidth = 0;
    int m_VideoHeight = 0;
    int m_RenderWidth = 0;
    int m_RenderHeight = 0;

    AVFrame* m_RGBAFrame = nullptr;
    uint8_t* m_FrameBuffer = nullptr;

    VideoRender* m_VideoRender = nullptr;
    SwsContext* m_SwsContext = nullptr;
};
#endif //MJNINDK_VIDEODECODER_H
