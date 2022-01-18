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
 *
 * @param env
 * @param thiz
 *  第二个参数的意义取决于该方法是静态还是实例方法(static or an instance method)。
 * 当本地方法作为一个实例方法时，第二个参数相当于对象本身，即 this.
 * 当本地方法作为一个静态方法时，指向所在类.
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

    jclass jUserClass = env->FindClass("com/tao/cpp_lrn/User");
    jboolean isUserClass = env->IsInstanceOf(user,jUserClass);
    LogD("isUserClass =  %d",isUserClass);

    jmethodID methodId = env->GetMethodID(jUserClass,
            "getResult","()Ljava/lang/String;");
    jstring result = (jstring)env->CallObjectMethod(user,methodId);
    const char *myRes = env->GetStringUTFChars(result,NULL);
    LogD("getResult =  %s",myRes);
}

void test_string(char *source) {
    int len = strlen(source);
    LogD("%s len = %d",__FILE_NAME__,len);
    char *dst = new char[len + 1];
    strcpy(dst,source);
    LogD("%s dst = %s",__FILE_NAME__,dst);
}

void test_pointer() {
    Box *box = 0;
    LogD("%s box = %p",__FILE_NAME__,box);
    box = nullptr;
    LogD("%s box --> %p",__FILE_NAME__,box);

}

void test_define_hong() {
    const char * ss = GET_STR(
            1111
            2222
            3333
            );
    LogD("%s test_define_hong ss = %s",__FILE_NAME__,ss);
    /*static const char *vertexShader = GET_STR(
            attribute
            vec4 aPosition;//输入的顶点坐标，会在程序指定将数据输入到该字段
            attribute
            vec2 aTextCoord;//输入的纹理坐标，会在程序指定将数据输入到该字段
            varying
            vec2 vTextCoord;//输出的纹理坐标
            void main() {
                //这里其实是将上下翻转过来（因为安卓图片会自动上下翻转，所以转回来）
                vTextCoord = vec2(aTextCoord.x, 1.0 - aTextCoord.y);
                //直接把传入的坐标值作为传入渲染管线。gl_Position是OpenGL内置的
                gl_Position = aPosition;
            }
    );*/
}
