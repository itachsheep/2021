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

#define MTAG "AudioVideoP."
#define LOGCATD(...) __android_log_print(ANDROID_LOG_DEBUG,MTAG,__VA_ARGS__)
#define LOGCATE(...) __android_log_print(ANDROID_LOG_ERROR,MTAG,__VA_ARGS__)

//static void LOGCATD(char* tag, const char* msg, ...) {
//    LOGCATD("%s: %s", tag, msg);
//}
//
//static void LOGCATE(char* tag, char* msg) {
//    LOGCATE("%s: %s", tag, msg);
//}


#endif //AUDIOVIDEOP_LOGUTILS_H
