//
// Created by wei tao on 2022/1/18.
//

#ifndef CPP_LRN_EGL_ENGINE_H
#define CPP_LRN_EGL_ENGINE_H
#include <EGL/egl.h>

EGLDisplay display;
ANativeWindow *nativeWindow = 0;
EGLSurface winSurface;
EGLContext eglContext;
int width = 640;
int height = 272;

//顶点着色器，每个顶点执行一次，可以并行执行
#define GET_STR(x) #x

#endif //CPP_LRN_EGL_ENGINE_H
