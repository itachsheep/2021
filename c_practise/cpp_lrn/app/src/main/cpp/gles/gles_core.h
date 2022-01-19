//
// Created by wei tao on 2022/1/16.
//

#ifndef CPP_LRN_GLES_CORE_H
#define CPP_LRN_GLES_CORE_H
#include <jni.h>
#include <pthread.h>
#include <string>

#include "play_callback.h"


class GLESPlay {
public:
    GLESPlay(JNIEnv *env, jobject thiz, PlayCallback *playCallback,
            const char *data_source, jobject surface);
    ~GLESPlay();
    void playYUV(jobject surface);
    void release();
    void prepare();
    void start();
private:
    PlayCallback *playCallback = 0;
    void showMessage(JNIEnv *env,const char *,bool );
    JNIEnv *env = 0;
    jobject thiz;
    pthread_t pid_prepare;
    char *data_source = 0;
    jobject surface;
    bool isPlay;
    pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
};

typedef void (GLESPlay::*FuncShowMessage)(JNIEnv *env,const char *,bool );


#endif //CPP_LRN_GLES_CORE_H
