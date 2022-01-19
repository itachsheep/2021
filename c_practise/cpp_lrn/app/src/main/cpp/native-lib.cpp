#include <jni.h>
#include <string>
#include "opensl/audio_play.h"
#include "gles/video_play.h"
#include "base_c.h"

const JavaVM *globalJavaVm = nullptr;

int JNI_OnLoad(JavaVM *javaVm, void *pVoid) {
    LogD("%s JNI_OnLoad", __FILE_NAME__);
    globalJavaVm = javaVm;
    return JNI_VERSION_1_6;
}

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
    test_string("hello");
    //test_pointer();
    //test_define_hong();
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_tao_cpp_1lrn_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativePlayPcm(JNIEnv *env, jobject thiz, jstring pcm_path) {
    playPcm(env, pcm_path);
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_cpp_1lrn_AudioPlay_nativeStopPcm(JNIEnv *env, jobject thiz) {
    stopPcm();
}

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


