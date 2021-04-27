//
// Created by wei tao on 2021/4/23.
//
#ifndef MJNINDK_LOGUTIL_H
#define MJNINDK_LOGUTIL_H

#include <sys/time.h>
#include<android/log.h>

#define TAG "LifeTip."
#define LOGCATD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGCATE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

static long long GetSysCurrentTime()
{
    struct timeval time;
    gettimeofday(&time,NULL);
    long long curTime = ((long long)(time.tv_sec))*1000+time.tv_usec/1000;
    return curTime;
}

#endif
