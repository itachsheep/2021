//
// Created by wei tao on 2022/1/13.
//

#include "base_c.h"

void Box::setWidth(int w) {
    //this->width = w;
    width = w;
}

void test_multi_dimensional_array() {
    int arr[2][3] = {
            {1, 2, 3},
            {4, 5, 6}
    };
    LogD( "%s arr[1][1] = %d", arr[1][1],__FILE_NAME__);

    int num = 10;
    int *b = &num;

    int sr[2] = {1, 2};
    int mr[3] = {5, 6, 8};
    /// 首先a[2] 代表是个数组，类型为 int*
    /// 所以a[0] 元素是 int * ，也就是一个int类型指针，所以，要赋值一个指针
    int *a[2] = {sr, mr};
    LogD( "%s a[0] pointer = %d", a[0],__FILE_NAME__);
    LogD( "%s a[0] value = %d", *a[0],__FILE_NAME__);
}

/**
 * 二个参数的意义取决于该方法是静态还是实例方法(static or an instance method)。
 * 当本地方法作为一个实例方法时，第二个参数相当于对象本身，即 this.
 * 当本地方法作为一个静态方法时，指向所在类.
 * @param env
 * @param thiz
 * @param user
 */
void printUser(JNIEnv *env, jobject thiz,
               jobject user) {
    LogD("%s printUser",__FILE_NAME__);
    jboolean a = true;
    LogD("%s printUser a = %d",__FILE_NAME__,a);
    a = false;
    LogD("%s printUser a = %d",__FILE_NAME__,a);
    jclass jAudioPlayClass = env->FindClass("com/tao/cpp_lrn/AudioPlay");
    if(!jAudioPlayClass) {
        LogE("%s jAudioPlayClass null ");
    } else {
        jboolean res = env->IsInstanceOf(thiz,jAudioPlayClass);
        //#define JNI_FALSE 0
        //#define JNI_TRUE 1
        LogD("IsInstanceOf =  %d",res);
    }

}
