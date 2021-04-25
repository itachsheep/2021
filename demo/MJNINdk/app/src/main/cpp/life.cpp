#include <jni.h>
#include <string>
#include "util/LogUtil.h"

extern "C" {
#include <libavcodec/version.h>
#include <libavcodec/avcodec.h>
#include <libavformat/version.h>
#include <libavutil/version.h>
#include <libavfilter/version.h>
#include <libswresample/version.h>
#include <libswscale/version.h>
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tao_mjnindk_FFMediaPlayer_stringFromJNI(JNIEnv *env, jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_tao_mjnindk_FFMediaPlayer_getFFmpegVersion(JNIEnv *env, jclass clazz) {
    char strBuffer[1024 * 4] = {};
    strcat(strBuffer,"libavcodec: ");
    strcat(strBuffer,AV_STRINGIFY(LIBAVCODEC_VERSION));
    strcat(strBuffer, "\nlibavformat : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVFORMAT_VERSION));
    strcat(strBuffer, "\nlibavutil : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVUTIL_VERSION));
    strcat(strBuffer, "\nlibavfilter : ");
    strcat(strBuffer, AV_STRINGIFY(LIBAVFILTER_VERSION));
    strcat(strBuffer, "\nlibswresample : ");
    strcat(strBuffer, AV_STRINGIFY(LIBSWRESAMPLE_VERSION));
    strcat(strBuffer, "\nlibswscale : ");
    strcat(strBuffer, AV_STRINGIFY(LIBSWSCALE_VERSION));
    strcat(strBuffer, "\navcodec_configure : \n");
    strcat(strBuffer, avcodec_configuration());
    strcat(strBuffer, "\navcodec_license : ");
    strcat(strBuffer, avcodec_license());

    LOGCATD("%s: strBuffer = %s",__FUNCTION__ ,strBuffer);
    return env->NewStringUTF(strBuffer);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_tao_mjnindk_FFMediaPlayer_native_1Init(JNIEnv *env, jobject thiz, jstring url,
                                                jint render_type, jobject surface) {
    // TODO: implement native_Init()
}