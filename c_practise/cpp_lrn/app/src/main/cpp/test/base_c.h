//
// Created by wei tao on 2022/1/13.
//

#ifndef CPP_LRN_BASE_C_H
#define CPP_LRN_BASE_C_H
#include <jni.h>
#include <string>

#include "my_log.h"

class Box {
private:
    int width;
public:
    void setWidth(int w);

    Box(int w):width(w) {
        LogD("Box cs");
    }

    friend void printWith(Box box) {
        LogD( "%s printWidth box width = %d ", box.width,__FILE_NAME__);
    }
};

void test_multi_dimensional_array();
void printUser(JNIEnv *env, jobject thiz,
               jobject user);

void test_string(char *source);

#endif //CPP_LRN_BASE_C_H
