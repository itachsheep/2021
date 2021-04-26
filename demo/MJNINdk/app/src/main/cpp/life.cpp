#include <jni.h>
#include <string>
#include "util/LogUtil.h"
#include <FFMediaPlayer.h>

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
Java_com_tao_mjnindk_FFMediaPlayer_native_1Init(JNIEnv *env, jobject obj, jstring jurl,
                                                jint render_type, jobject surface) {
    const char* url = env->GetStringUTFChars(jurl,nullptr);
    FFMediaPlayer* player = new FFMediaPlayer();
    player->Init(env,obj, const_cast<char *>(url),render_type,surface);
    env->ReleaseStringUTFChars(jurl,url);

    /**
     * reinterpret_cast运算符是用来处理无关类型之间的转换；
     * 它会产生一个新的值，这个值会有与原始参数（expressoin）有完全相同的比特位。
     *
     *IBM的C++指南里倒是明确告诉了我们reinterpret_cast可以，或者说应该在什么地方用来作为转换运算符：

        从指针类型到一个足够大的整数类型
        从整数类型或者枚举类型到指针类型
        从一个指向函数的指针到另一个不同类型的指向函数的指针
        从一个指向对象的指针到另一个不同类型的指向对象的指针
        ...
     * 所以总结来说：reinterpret_cast用在任意指针（或引用）类型之间的转换；
     * 以及指针与足够大的整数类型之间的转换；从整数类型（包括枚举类型）到指针类型，无视大小。
     */
    return reinterpret_cast<jlong>(player);
}