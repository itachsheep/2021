//
// Created by wei tao on 2022/1/18.
//

#ifndef CPP_LRN_EGL_ENGINE_H
#define CPP_LRN_EGL_ENGINE_H
#include <EGL/egl.h>
#include <GLES2/gl2.h>
#include <android/native_window_jni.h>
#include "my_log.h"
#include "play_callback.h"
#include "gles_core.h"

#define EGL_INIT_SUCCESS 1;
#define EGL_INIT_FAILED 0;

//顶点着色器，每个顶点执行一次，可以并行执行
#define GET_STR(x) #x

static const char *vertexShader = GET_STR(
        attribute vec4 aPosition;//输入的顶点坐标，会在程序指定将数据输入到该字段
        attribute vec2 aTextCoord;//输入的纹理坐标，会在程序指定将数据输入到该字段
        varying vec2 vTextCoord;//输出的纹理坐标
        void main() {
            //这里其实是将上下翻转过来（因为安卓图片会自动上下翻转，所以转回来）
            vTextCoord = vec2(aTextCoord.x, 1.0 - aTextCoord.y);
            //直接把传入的坐标值作为传入渲染管线。gl_Position是OpenGL内置的
            gl_Position = aPosition;
        }
);

//图元被光栅化为多少片段，就被调用多少次
static const char *fragYUV420P = GET_STR(
        precision mediump float;
        varying vec2 vTextCoord;
        //输入的yuv三个纹理
        uniform sampler2D yTexture;//采样器
        uniform sampler2D uTexture;//采样器
        uniform sampler2D vTexture;//采样器
        void main() {
            vec3 yuv;
            vec3 rgb;
            //分别取yuv各个分量的采样纹理（r表示？）
            yuv.x = texture2D(yTexture, vTextCoord).g;
            /// texture2D 第一个参数代表图片纹理，第二个参数代表纹理坐标点，
            /// 通过GLSL的内建函数texture2D来获取对应位置纹理的颜色RGBA值
            yuv.y = texture2D(uTexture, vTextCoord).g - 0.5;
            yuv.z = texture2D(vTexture, vTextCoord).g - 0.5;
            rgb = mat3(
                    1.0, 1.0, 1.0,
                    0.0, -0.39465, 2.03211,
                    1.13983, -0.5806, 0.0
            ) * yuv;
            //gl_FragColor是OpenGL内置的
            gl_FragColor = vec4(rgb, 1.0);
        }
);

GLint initShader(const char *source,int type);
unsigned int initEglContext(JNIEnv *env,jobject surface,
        GLESPlay *glesPlay,FuncShowMessage showMessage);
GLint initProgram(JNIEnv *env,GLESPlay *glesPlay,
                  FuncShowMessage funcShowMessage);
void initTexture(GLint program);

extern EGLDisplay display;
extern ANativeWindow *nativeWindow;
extern EGLSurface winSurface;
extern EGLContext eglContext;
extern int height;
extern int width;
extern GLuint textures[3];
#endif //CPP_LRN_EGL_ENGINE_H
