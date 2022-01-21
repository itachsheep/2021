#include <jni.h>
#include <string>
#include "opensl/audio_play.h"
#include "gles/video_play.h"
#include "base_c.h"
#include "triangle/draw_triangle.h"

const JavaVM *globalJavaVm = nullptr;

int JNI_OnLoad(JavaVM *javaVm, void *pVoid) {
    LogD("%s JNI_OnLoad", __FILE_NAME__);
    globalJavaVm = javaVm;
    return JNI_VERSION_1_6;
}

//////////////////////////////// 基础语法测试
extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_printUser(JNIEnv *env, jobject thiz,
                                          jobject user) {
    printUser(env, thiz, user);
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_testFriend(JNIEnv *env, jobject thiz) {
//    LogD(__FILE_NAME__,"test_friend!!");
    LogD("test_friend!!");
    /*Box box;
    box.setWidth(100);
    printWith(box);*/

    //test_multi_dimensional_array();
    //test_start();
    //test_string("hello");
    //test_pointer();
    //test_define_hong();
    test_func_pointer();
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_tao_cpp_1lrn_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


//////////////////////////////// 播放 pcm
extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativePlayPcm(JNIEnv *env, jobject thiz, jstring pcm_path) {
    playPcm(env, pcm_path);
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeStopPcm(JNIEnv *env, jobject thiz) {
    stopPcm();
}

//////////////////////////////// 播放yuv
extern "C"
JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativePlayVideo(JNIEnv *env, jobject thiz, jstring video_path,
                                                jobject surface) {
    playVideo(const_cast<JavaVM *>(globalJavaVm),env, thiz, video_path, surface);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeOnDestroy(JNIEnv *env, jobject thiz) {
    onDestroy();
}

//////////////////////////////// 绘制三角

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeGlesInit(JNIEnv *env, jobject thiz) {
    glesInit();
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeGlesRender(JNIEnv *env, jobject thiz) {
    glesRender();
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeGlesResize(JNIEnv *env, jobject thiz, jint width,
                                                 jint height) {
    glesResize(width,height);
}


////////////////////////////////





////////////////////////////////

