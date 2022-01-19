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

GLESPlay::GLESPlay(JNIEnv *env, jobject thiz, PlayCallback *playCallback, const char *source, jobject surface) {
    this->playCallback = playCallback;
    this->env = env;
    this->thiz = thiz;
    this->surface = surface;

    // 这里有坑，这里赋值之后，不能给其他地方用，因为被释放了，变成了悬空指针
    // this->data_source = source;
    // 解决上面的坑，自己Copy才行
    // [strlen(data_source)] 这段代码有坑：因为（hello  而在C++中是 hello\n），所以需要加1
    this->data_source = new char[strlen(source) + 1];
    strcpy(data_source,source);
}

GLESPlay::~GLESPlay() {
    if(playCallback) {
        delete playCallback;
        playCallback = 0;
    }
}

void GLESPlay::showMessage(JNIEnv *env, const char *message, bool success) {
    if(this->playCallback) {
        if(success) {
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
    showMessage(env,"start",true);
    //开始播放标志
    isPlay = true;
    FuncShowMessage p_func_ShowMessage = &GLESPlay::showMessage;
    initEglContext(env,surface,this,p_func_ShowMessage);

    GLint vsh = initShader(vertexShader,GL_VERTEX_SHADER);
    GLint fsh = initShader(fragYUV420P,GL_FRAGMENT_SHADER);
    //创建渲染程序
    GLint program = glCreateProgram();
    if(program == 0) {
        LogE("%s glCreateProgram failed",__FILE_NAME__);
        return;
    }
    //向渲染程序中加入着色器
    glAttachShader(program,vsh);
    glAttachShader(program,fsh);


}
void GLESPlay::start() {
    this->playYUV(this->surface);
}

void GLESPlay::prepare() {
    pthread_create(&pid_prepare,0,readYUVThread,this);
}

void GLESPlay::release() {
    //TODO
}