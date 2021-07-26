//
// Created by wei tao on 2021/7/25.
//

#ifndef AUDIOVIDEOP_LOGUTILS_H
#define AUDIOVIDEOP_LOGUTILS_H

#include <sys/time.h>
#include <android/log.h>

#define TAG "AudioVideoP."
#define LOGCATD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGCATE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)


#endif //AUDIOVIDEOP_LOGUTILS_H
