//
// Created by wei tao on 2022/1/16.
//
#include "play_callback.h"
#include "video_play.h"
#include "gles_core.h"

GLESPlay *glesPlay = 0;
void playVideo(JavaVM *javaVm, JNIEnv *env, jobject thiz, jstring video_path,
               jobject surface) {
    LogD("%s playVideo",__FILE_NAME__);
    const char *yuv420Path = env->GetStringUTFChars(video_path,JNI_FALSE);
    PlayCallback *playCallback = new PlayCallback(javaVm,env,thiz);
    glesPlay = new GLESPlay(env,thiz,playCallback,yuv420Path,surface);
    //这里prepare 内部会开启一个子线程，由于开启会造成 堆栈溢出 固取消了  JNI 中开启
    //    gles_play->prepare();
    glesPlay->start();
    env->ReleaseStringUTFChars(video_path,yuv420Path);
}

void onDestroy() {
    LogD("$s onDestroy", __FILE_NAME__);

    if(glesPlay) {
        glesPlay->release();
    }
}

