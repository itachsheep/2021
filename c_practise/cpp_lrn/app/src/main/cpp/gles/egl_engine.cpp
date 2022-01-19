//
// Created by wei tao on 2022/1/18.
//

#include "egl_engine.h"

EGLDisplay display;
ANativeWindow *nativeWindow = 0;
EGLSurface winSurface;
EGLContext eglContext;
int width = 640;
int height = 272;

GLint initShader(const char *source,int type) {
    //创建shader
    GLint shader = glCreateShader(type);
    if(shader == 0) {
        LogE("%s initShader error 1",__FILE_NAME__);
        return 0;
    }

    //加载shader
    glShaderSource(shader,
            1,//shader数量
            &source,
            0);//代码长度，传0则读到字符串结尾
    //编译shader
    glCompileShader(shader);
    GLint status;
    glGetShaderiv(shader,GL_COMPILE_STATUS,&status);
    if(status == 0) {
        LogE("%s glCompileShader error, type = %d, source = %s",
                __FILE_NAME__,
                type,source);
        return 0;
    }

    LogD("%s initShader success",__FILE_NAME__);
    return shader;
}

unsigned int initEglContext(JNIEnv *env,jobject surface,
        GLESPlay *glesPlay,
        FuncShowMessage funcShowMessage) {
    (glesPlay->*funcShowMessage)(env,"initEglContext start", true);

    //1.获取原始窗口
    nativeWindow = ANativeWindow_fromSurface(env,surface);
    //获取Display
    display = eglGetDisplay(EGL_DEFAULT_DISPLAY);
    if(display == EGL_NO_DISPLAY) {
        LogE("%s egl display failed",__FILE_NAME__);
        (glesPlay->*funcShowMessage)(env,"egl display failed", false);
        return EGL_INIT_FAILED;
    }
    //2.初始化egl，后两个参数为主次版本号
    if(EGL_TRUE != eglInitialize(display,0,0)) {
        LogE("%s eglInitialize failed",__FILE_NAME__);
        //showMessage(env,"eglInitialize failed", false);
        return EGL_INIT_FAILED;
    }
    //3.1 surface配置，可以理解为窗口
    EGLConfig eglConfig;
    EGLint configNum;
    EGLint configSpec[] = {
            EGL_RED_SIZE, 8,
            EGL_GREEN_SIZE, 8,
            EGL_BLUE_SIZE, 8,
            EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
            EGL_NONE
    };
    if(EGL_TRUE != eglChooseConfig(display,configSpec,
                                   &eglConfig,
                                   1,
                                   &configNum)) {
        LogE("%s eglChooseConfig failed",__FILE_NAME__);
        //showMessage(env,"eglChooseConfig failed", false);
        return EGL_INIT_FAILED;
    }
    //3.2创建surface(egl和NativeWindow进行关联。最后一个参数为属性信息，0表示默认版本)
    winSurface = eglCreateWindowSurface(display,eglConfig,nativeWindow,0);
    if(EGL_NO_SURFACE == winSurface) {
        LogE("%s eglCreateWindowSurface failed",__FILE_NAME__);
        //showMessage(env,"eglCreateWindowSurface failed", false);
        return EGL_INIT_FAILED;
    }
    //4 创建关联上下文
    const EGLint ctxAttr[] = {
            EGL_CONTEXT_CLIENT_VERSION, 2, EGL_NONE
    };
    //EGL_NO_CONTEXT表示不需要多个设备共享上下文
    eglContext = eglCreateContext(display,eglConfig,EGL_NO_CONTEXT,ctxAttr);
    if(EGL_NO_CONTEXT == eglContext) {
        LogE("%s eglCreateContext failed",__FILE_NAME__);
        //showMessage(env,"eglCreateContext failed", false);
        return EGL_INIT_FAILED;
    }
    //将egl和opengl关联
    //两个surface一个读一个写。第二个一般用来离线渲染？
    if(EGL_TRUE != eglMakeCurrent(display,winSurface,winSurface,eglContext)) {
        LogE("%s eglMakeCurrent failed",__FILE_NAME__);
        //showMessage(env,"eglMakeCurrent failed", false);
        return EGL_INIT_FAILED;
    }
    return EGL_INIT_SUCCESS;
}