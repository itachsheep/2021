//
// Created by wei tao on 2021/8/6.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jint JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_init(JNIEnv *env, jobject thiz, jstring mp3_file_path,
                                              jstring pcm_file_path) {
    // TODO: implement init()
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_decode(JNIEnv *env, jobject thiz) {
    // TODO: implement decode()
}


extern "C" JNIEXPORT void JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_destroy(JNIEnv *env, jobject thiz) {
    // TODO: implement destroy()
}