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

    int add(int a,int b){ //非静态函数
        return  a + b;
    }

    void test_pointer();

    Box(int w):width(w) {
        LogD("Box cs");
    }

    friend void printWith(Box box) {
        LogD( "%s printWidth box width = %d ", __FILE_NAME__,box.width);
    }
};

typedef int (Box::*FuncCal)(int,int);

void test_multi_dimensional_array();
void printUser(JNIEnv *env, jobject thiz,
               jobject user);

void test_string(char *source);
void test_pointer();

#define GET_STR(x) #x
void test_define_hong();
void test_func_pointer();

#endif //CPP_LRN_BASE_C_H
