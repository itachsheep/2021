//
// Created by wei tao on 2021/4/25.
//

#ifndef MJNINDK_FFMEDIAPLAYER_H
#define MJNINDK_FFMEDIAPLAYER_H

#include <jni.h>

class FFMediaPlayer {
public:
    FFMediaPlayer(){};
    ~FFMediaPlayer(){};
    void Init(JNIEnv *jniEnv, jobject obj, char* url, int renderType, jobject surface);
    void UnInit();

private:
    JavaVM *m_JavaVM = nullptr;
    jobject m_JavaObj = nullptr;
};

#endif //MJNINDK_FFMEDIAPLAYER_H
