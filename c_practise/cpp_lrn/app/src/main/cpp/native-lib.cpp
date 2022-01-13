#include <jni.h>
#include <string>
#include "opensl/audio_play.h"


extern "C" JNIEXPORT jstring JNICALL
Java_com_tao_cpp_1lrn_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativePlayPcm(JNIEnv *env, jobject thiz, jstring pcm_path) {

}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeStopPcm(JNIEnv *env, jobject thiz) {

}