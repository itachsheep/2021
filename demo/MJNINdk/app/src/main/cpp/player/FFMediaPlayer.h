//
// Created by wei tao on 2021/4/25.
//

#ifndef MJNINDK_FFMEDIAPLAYER_H
#define MJNINDK_FFMEDIAPLAYER_H

#include <jni.h>
#include "VideoRender.h"
#include "VideoDecoder.h"

#define JAVA_PLAYER_EVENT_CALLBACK_API_NAME "playerEventCallback"

#define MEDIA_PARAM_VIDEO_WIDTH         0x0001
#define MEDIA_PARAM_VIDEO_HEIGHT        0x0002
#define MEDIA_PARAM_VIDEO_DURATION      0x0003


class FFMediaPlayer {
public:
    FFMediaPlayer(){};
    ~FFMediaPlayer(){};
    void Init(JNIEnv *jniEnv, jobject obj, char* url, int renderType, jobject surface);
    void UnInit();
    void Play();
    void Pause();
    void Stop();
    void SeekToPosition(float position);
    long GetMediaParams(int paramType);

private:
    JNIEnv *GetJNIEnv(bool *isAttach);
    jobject GetJavaObj();
    JavaVM *GetJavaVM();

    JavaVM *m_JavaVM = nullptr;
    jobject m_JavaObj = nullptr;

    static void PostMessage(void *context, int msgType, float msgCode);

    VideoDecoder* m_VideoDecoder = nullptr;
    VideoRender *m_VideoRender = nullptr;
};

#endif //MJNINDK_FFMEDIAPLAYER_H
