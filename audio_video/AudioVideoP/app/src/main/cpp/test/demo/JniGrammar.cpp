//
// Created by wei tao on 2021/9/11.
//
#include "LogUtils.h"
#include <jni.h>
#include <string>
#include "MyGrammar.h"
extern "C" JNIEXPORT void JNICALL
Java_com_tao_practice_Grammar_testNewMalloc(JNIEnv *env, jobject thiz) {
    LOGCATD("------------new-------------");
    TestNewMalloc* p = new TestNewMalloc();
    p->setValue(100);
    p->printValue();
    delete p;

    LOGCATD("------------malloc-------------");
    TestNewMalloc* q = (TestNewMalloc*)malloc(sizeof(TestNewMalloc));
    q->setValue(200);
    q->printValue();
    free(q);
}
