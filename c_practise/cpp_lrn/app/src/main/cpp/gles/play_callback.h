//
// Created by wei tao on 2022/1/16.
//

#ifndef CPP_LRN_PLAY_CALLBACK_H
#define CPP_LRN_PLAY_CALLBACK_H
#include <jni.h>
#include "my_log.h"

class PlayCallback {
public:
    PlayCallback(JavaVM *javaVm, JNIEnv *env, jobject job);

    ~PlayCallback();
    void onSucceed(const char *message);
    void onError(const char* message);
    void toJavaMessage(const char* message);
private:
    JavaVM *javaVm = 0;
    JNIEnv *env = 0;
    jobject instance;
    jmethodID jmd_showMessage;
};
#endif //CPP_LRN_PLAY_CALLBACK_H
