//
// Created by wei tao on 2021/8/8.
//
#include <jni.h>
#include <string>
#include "LogUtils.h"

#define TAG __FILE_NAME__

void printChar(char *filePath) {

    //字符串拷贝：
    //方式一
    /*int len = strlen(filePath) + 1;
    char *tt = new char[len];
    memset(tt,0,len);
    memcpy(tt,filePath,sizeof(char) * len);
    LOGCATD("%s ------ tt = %s, len = %d",TAG,tt,len);*/

    //方式二
    char *ss;
    strcpy(ss,filePath);
    LOGCATD("%s ss = %s",TAG,ss);

}

extern "C" JNIEXPORT void JNICALL
Java_com_tao_ctest_CTest_testChar(JNIEnv *env, jobject thiz) {
    char *path = "12345";
    LOGCATD("%s path = %s",TAG,path);
    printChar(path);
}







