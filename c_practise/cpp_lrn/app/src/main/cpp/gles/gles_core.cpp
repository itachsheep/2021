//
// Created by wei tao on 2022/1/16.
//

#include "gles_core.h"
#include "egl_engine.h"

/**
 * 播放 YUV 线程
 */
void *readYUVThread(void *args) {
    GLESPlay *glesPlay = static_cast<GLESPlay *>(args);
    glesPlay->start();
    return 0;
}

GLESPlay::GLESPlay(JNIEnv *env, jobject thiz, PlayCallback *playCallback, const char *source,
                   jobject surface) {
    this->playCallback = playCallback;
    this->env = env;
    this->thiz = thiz;
    this->surface = surface;

    // 这里有坑，这里赋值之后，不能给其他地方用，因为被释放了，变成了悬空指针
    // this->data_source = source;
    // 解决上面的坑，自己Copy才行
    // [strlen(data_source)] 这段代码有坑：因为（hello  而在C++中是 hello\n），所以需要加1
    this->data_source = new char[strlen(source) + 1];
    strcpy(data_source, source);
}

GLESPlay::~GLESPlay() {
    if (playCallback) {
        delete playCallback;
        playCallback = 0;
    }
}

void GLESPlay::showMessage(JNIEnv *env, const char *message, bool success) {
    if (this->playCallback) {
        if (success) {
            this->playCallback->onSucceed(message);
        } else {
            this->playCallback->onError(message);
        }
    }
}

void GLESPlay::playYUV(jobject surface) {
    //加锁
    pthread_mutex_lock(&mutex);

    //先判断资源是否没有释放，避免播放异常
    release();
    showMessage(env, "start", true);
    //开始播放标志
    isPlay = true;
    FuncShowMessage p_func_ShowMessage = &GLESPlay::showMessage;
    initEglContext(env, surface, this, p_func_ShowMessage);
    GLint program = initProgram(env, this, p_func_ShowMessage);
    if (program == 0) {
        LogE("%s initProgram failed", __FILE_NAME__);
        return;
    }
    //生成、绑定纹理，纹理坐标等
    initTexture(program);
    unsigned char *buf[3] = {0};
    buf[0] = new unsigned char[width * height];//y
    buf[1] = new unsigned char[width * height / 4];//u
    buf[2] = new unsigned char[width * height / 4];//v

    FILE *fp = fopen(data_source, "rb");
    if (!fp) {
        LogE("%s playYUV yuv file open failed", __FILE_NAME__);
        return;
    }
    while (!feof(fp)) {
        //解决异常退出,终止读取数据
        if (!isPlay) {
            return;
        }
        fread(buf[0], 1, width * height, fp);
        fread(buf[1], 1, width * height / 4, fp);
        fread(buf[2], 1, width * height / 4, fp);
        //激活第一层纹理，绑定到创建的纹理
        //下面的width,height主要是显示尺寸？
        glActiveTexture(GL_TEXTURE0);
        //绑定y对应的纹理
        glBindTexture(GL_TEXTURE_2D, textures[0]);
        //替换纹理，比重新使用glTexImage2D性能高多
        glTexSubImage2D(GL_TEXTURE_2D, 0,
                        0, 0,//相对原来的纹理的offset
                        width, height,//加载的纹理宽度、高度。最好为2的次幂
                        GL_LUMINANCE, GL_UNSIGNED_BYTE,
                        buf[0]);

        //激活第二层纹理，绑定到创建的纹理
        glActiveTexture(GL_TEXTURE1);
        //绑定u对应的纹理
        glBindTexture(GL_TEXTURE_2D, textures[1]);
        glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0,
                        width / 2,
                        height / 2,
                        GL_LUMINANCE, GL_UNSIGNED_BYTE, buf[1]);

        //激活第三层纹理，绑定到创建的纹理
        glActiveTexture(GL_TEXTURE2);
        //绑定v对应的纹理
        glBindTexture(GL_TEXTURE_2D, textures[2]);
        glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, width / 2, height / 2, GL_LUMINANCE,
                        GL_UNSIGNED_BYTE,
                        buf[2]);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        //窗口显示，交换双缓冲区
        eglSwapBuffers(display, winSurface);
    }
    showMessage(env, "onComplete", true);
    release();
    isPlay = false;
    pthread_mutex_unlock(&mutex);
}

void GLESPlay::start() {
    this->playYUV(this->surface);
}

void GLESPlay::prepare() {
    pthread_create(&pid_prepare, 0, readYUVThread, this);
}

void GLESPlay::release() {
    LogD("%s release ", __FILE_NAME__);
    if (display || winSurface || eglContext) {
        //销毁显示设备
        eglDestroySurface(display, winSurface);
        //销毁上下文
        eglDestroyContext(display, eglContext);
        //释放窗口
        ANativeWindow_release(nativeWindow);
        //释放线程
        eglReleaseThread();
        //停止
        eglTerminate(display);
        eglMakeCurrent(display, winSurface, EGL_NO_SURFACE, eglContext);
        eglContext = EGL_NO_CONTEXT;
        display = EGL_NO_SURFACE;
        winSurface = nullptr;
        winSurface = 0;
        nativeWindow = 0;
        isPlay = false;
    }
}