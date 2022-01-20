//
// Created by wei tao on 2022/1/18.
//

#include "egl_engine.h"

EGLDisplay display;
ANativeWindow *nativeWindow = 0;
EGLSurface winSurface;
EGLContext eglContext;
int width = 640;//YUV文件中一帧yuv 宽和高，固定，不能改，应该需要动态读取
//int width = 320;
int height = 272;
//int height = 136;
GLuint textures[3] = {0};

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

GLint initProgram(JNIEnv *env,GLESPlay *glesPlay,
                            FuncShowMessage funcShowMessage) {
    GLint vsh = initShader(vertexShader,GL_VERTEX_SHADER);
    GLint fsh = initShader(fragYUV420P,GL_FRAGMENT_SHADER);
    //创建渲染程序
    GLint program = glCreateProgram();
    if(program == 0) {
        LogE("%s glCreateProgram failed",__FILE_NAME__);
        return 0;
    }
    //向渲染程序中加入着色器
    glAttachShader(program,vsh);
    glAttachShader(program,fsh);
    //链接程序
    glLinkProgram(program);
    GLint status = 0;
    glGetProgramiv(program,GL_LINK_STATUS,&status);
    if(status == 0) {
        LogE("glLinkProgram failed");
        (glesPlay->*funcShowMessage)(env, "glLinkProgram failed", false);
        //showMessage(env, "glLinkProgram failed", false);
        return 0;
    }
    //激活渲染程序
    glUseProgram(program);
    LogD("%s initProgram success",__FILE_NAME__);
    return program;
}

void initTexture(GLint program) {

    //加入三维顶点数据
    static float ver[] = {
            1.0f, -1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            -1.0f, 1.0f, 0.0f
    };
    GLuint aPos = static_cast<GLuint>(glGetAttribLocation(program,"aPosition"));
    glEnableVertexAttribArray(aPos);
    glVertexAttribPointer(aPos,3,GL_FLOAT,GL_FALSE,0,ver);
    //加入纹理坐标数据
    static float fragment[] = {
            1.0f, 0.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f
    };
    GLuint aTex = static_cast<GLuint>(glGetAttribLocation(program,"aTextCoord"));
    glEnableVertexAttribArray(aTex);
    glVertexAttribPointer(aTex,2,GL_FLOAT,GL_FALSE,0,fragment);
    //纹理初始化
    //设置纹理层对应的对应采样器？
    /**
     *  //获取一致变量的存储位置
    GLint textureUniformY = glGetUniformLocation(program, "SamplerY");
    GLint textureUniformU = glGetUniformLocation(program, "SamplerU");
    GLint textureUniformV = glGetUniformLocation(program, "SamplerV");
    //对几个纹理采样器变量进行设置
    glUniform1i(textureUniformY, 0);
    glUniform1i(textureUniformU, 1);
    glUniform1i(textureUniformV, 2);
     */
    ///对sampler变量，使用函数glUniform1i和glUniform1iv进行设置
    glUniform1i(glGetUniformLocation(program,"yTexture"),0);
    glUniform1i(glGetUniformLocation(program,"uTexture"),1);
    glUniform1i(glGetUniformLocation(program,"vTexture"),2);

    ///纹理ID
    textures[3] = {0};
    ///创建若干个纹理对象，并且得到纹理ID
    glGenTextures(3, textures);
    for (int i = 0; i < 3; ++i) {
        LogD("%s initTexture textures = %d", __FILE_NAME__,textures[i]);
    }

    ///绑定纹理。后面的的设置和加载全部作用于当前绑定的纹理对象
    ///GL_TEXTURE0、GL_TEXTURE1、GL_TEXTURE2 的就是纹理单元，
    /// GL_TEXTURE_1D、GL_TEXTURE_2D、CUBE_MAP为纹理目标
    /// 通过 glBindTexture 函数将纹理目标和纹理绑定后，
    /// 对纹理目标所进行的操作都反映到对纹理上
    glBindTexture(GL_TEXTURE_2D, textures[0]);
    //缩小的过滤器
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    //放大的过滤器
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    /// 设置纹理的格式和大小
    /// 加载纹理到 OpenGL，读入 buffer 定义的位图数据，并把它复制到当前绑定的纹理对象
    /// 当前绑定的纹理对象就会被附加上纹理图像。
    /// width,height表示每几个像素公用一个yuv元素？
    /// 比如width / 2表示横向每两个像素使用一个元素？
    glTexImage2D(GL_TEXTURE_2D,
                 0,//细节基本 默认0
                 GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个亮度的颜色通道的意思）
                 width,//加载的纹理宽度。最好为2的次幂(这里对y分量数据当做指定尺寸算，但显示尺寸会拉伸到全屏？)
                 height,//加载的纹理高度。最好为2的次幂
                 0,//纹理边框
                 GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                 GL_UNSIGNED_BYTE,//像素点存储的数据类型
                 NULL //纹理的数据（先不传）
    );


    //绑定纹理
    glBindTexture(GL_TEXTURE_2D, textures[1]);
    //缩小的过滤器
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    //设置纹理的格式和大小
    glTexImage2D(GL_TEXTURE_2D,
                 0,//细节基本 默认0
                 GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个颜色通道的意思）
                 width / 2,//u数据数量为屏幕的4分之1
                 height / 2,
                 0,//边框
                 GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                 GL_UNSIGNED_BYTE,//像素点存储的数据类型
                 NULL //纹理的数据（先不传）
    );

    //绑定纹理
    glBindTexture(GL_TEXTURE_2D, textures[2]);
    //缩小的过滤器
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    //设置纹理的格式和大小
    glTexImage2D(GL_TEXTURE_2D,
                 0,//细节基本 默认0
                 GL_LUMINANCE,//gpu内部格式 亮度，灰度图（这里就是只取一个颜色通道的意思）
                 width / 2,
                 height / 2,//v数据数量为屏幕的4分之1
                 0,//边框
                 GL_LUMINANCE,//数据的像素格式 亮度，灰度图
                 GL_UNSIGNED_BYTE,//像素点存储的数据类型
                 NULL //纹理的数据（先不传）
    );

    LogD("%s initTexture end ",__FILE_NAME__);
}