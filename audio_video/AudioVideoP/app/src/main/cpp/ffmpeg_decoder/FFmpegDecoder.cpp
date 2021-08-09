//
// Created by wei tao on 2021/8/6.
//

#include <jni.h>
#include <string>
#include "LogUtils.h"
#include "DecoderController.h"
#define TAG __FILE_NAME__

DecoderController decoderController;
extern "C" JNIEXPORT jint JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_init(JNIEnv *env, jobject thiz, jstring mp3_file_path,
                                              jstring pcm_file_path) {
    LOGCATD("%s init called",TAG);
    const char *mp3FilePath = env->GetStringUTFChars(mp3_file_path,NULL);
    const char *pcmFilePath = env->GetStringUTFChars(pcm_file_path,NULL);
    decoderController.init(mp3FilePath,pcmFilePath);
    return -1;
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_decode(JNIEnv *env, jobject thiz) {
    // TODO: implement decode()
}


extern "C" JNIEXPORT void JNICALL
Java_com_tao_ffmpegDecoder_FFmpegDecoder_destroy(JNIEnv *env, jobject thiz) {
    // TODO: implement destroy()
}