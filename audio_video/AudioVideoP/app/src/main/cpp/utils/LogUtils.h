//
// Created by wei tao on 2021/7/25.
//

#ifndef AUDIOVIDEOP_LOGUTILS_H
#define AUDIOVIDEOP_LOGUTILS_H

#include <sys/time.h>
#include <android/log.h>
#include <cstdio>
#include <string>
//using namespace std;

#define TAG "AudioVideoP."
#define MYLOCATD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define MYLOCATE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

static void LOGCATD(char* tag, char* msg) {
    MYLOCATD("%s: %s", tag, msg);
}

static void LOGCATE(char* tag, char* msg) {
    MYLOCATE("%s: %s", tag, msg);
}


#endif //AUDIOVIDEOP_LOGUTILS_H
