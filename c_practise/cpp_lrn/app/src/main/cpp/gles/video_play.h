//
// Created by wei tao on 2022/1/16.
//

#ifndef CPP_LRN_VIDEO_PLAY_H
#define CPP_LRN_VIDEO_PLAY_H
#include <jni.h>
#include "my_log.h"
void playVideo(JavaVM *javaVm, JNIEnv *env, jobject thiz, jstring video_path,
               jobject surface);
void onDestroy();
#endif //CPP_LRN_VIDEO_PLAY_H
