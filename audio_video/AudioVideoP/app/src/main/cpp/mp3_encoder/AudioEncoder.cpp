//
// Created by wei tao on 2021/7/25.
//
#include <jni.h>
#include <string>

#include "LogUtils.h"
#include "Mp3Encoder.h"

Mp3Encoder* encoder = NULL;

extern "C" JNIEXPORT void JNICALL
Java_com_tao_mp3encoder_Mp3Encoder_encode(JNIEnv *env, jobject thiz) {
    LOGCATD("Encode");
    if(encoder != NULL) {
        encoder->Encode();
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_mp3encoder_Mp3Encoder_destroy(JNIEnv *env, jobject thiz) {
    LOGCATD("Destroy");
    if(encoder != NULL) {
        encoder->Destroy();
    }
}

extern "C" JNIEXPORT jint JNICALL
Java_com_tao_mp3encoder_Mp3Encoder_init(JNIEnv *env, jobject thiz, jstring pcm_path,
                                        jstring mp3_path, jint channels, jint bit_rate,
                                        jint sample_rate) {
    const char* pcmPath = env->GetStringUTFChars(pcm_path,NULL);
    const char* mp3Path = env->GetStringUTFChars(mp3_path,NULL);
    encoder = new Mp3Encoder();
    encoder->Init(pcmPath,mp3Path,sample_rate,channels,bit_rate);
    env->ReleaseStringUTFChars(pcm_path,pcmPath);
    env->ReleaseStringUTFChars(mp3_path,mp3Path);
}
