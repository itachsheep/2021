//
// Created by wei tao on 2021/4/26.
//
#include "FFMediaPlayer.h"
void FFMediaPlayer::Init(JNIEnv *jniEnv, jobject obj, char *url, int renderType, jobject surface)
{
    jniEnv->GetJavaVM(&m_JavaVM);
    m_JavaObj = jniEnv->NewGlobalRef(obj);

}