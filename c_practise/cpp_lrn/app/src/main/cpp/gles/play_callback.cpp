//
// Created by wei tao on 2022/1/16.
//

#include "play_callback.h"

PlayCallback::PlayCallback(JavaVM *javaVm, JNIEnv *env, jobject thiz_AudioPlay) {
    this->javaVm = javaVm;
    this->env = env;
    //提升全局
    this->instance = env->NewGlobalRef(thiz_AudioPlay);
}

void PlayCallback::toJavaMessage(const char *message) {

    jclass jAudioPlayClass = env->GetObjectClass(this->instance);
    jmethodID jmethodId = env->GetMethodID(jAudioPlayClass,"showMessage",
                                           "(Ljava/lang/String;)V");
    jstring msg = env->NewStringUTF(message);
    env->CallVoidMethod(this->instance,jmethodId,msg);
    LogD("%s toJavaMessage %s ",__FILE_NAME__,message);
}

void PlayCallback::onSucceed(const char *message) {
    toJavaMessage(message);
}

void PlayCallback::onError(const char *message) {
    toJavaMessage(message);
}

PlayCallback::~PlayCallback() {

}

