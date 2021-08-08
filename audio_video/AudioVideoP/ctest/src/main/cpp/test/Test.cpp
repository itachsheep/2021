//
// Created by wei tao on 2021/8/8.
//
#include <jni.h>
#include <string>
#include "LogUtils.h"

#define TAG __FILE_NAME__

void printChar(char *filePath) {
    char *tt = filePath;
    LOGCATD("%s tt = %s",TAG,tt);
}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_ctest_CTest_testChar(JNIEnv *env, jobject thiz) {
    char *path = "12345";
    LOGCATD("%s path = %s",TAG,path);
    printChar(path);
}







