//
// Created by wei tao on 2021/4/26.
//

#ifndef MJNINDK_NATIVERENDER_H
#define MJNINDK_NATIVERENDER_H
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <jni.h>
#include "VideoRender.h"

class NativeRender : public VideoRender {
public:
    NativeRender(JNIEnv *env, jobject surface);
    virtual ~NativeRender();
    virtual void Init(int videoWidth, int videoHeight, int *dstSize);
    virtual void RenderVideoFrame(NativeImage *pImage);
    virtual void UnInit();

private:
    ANativeWindow_Buffer m_NativeWindowBuffer;
    ANativeWindow *m_NativeWindow = nullptr;
    int m_DstWidth;
    int m_DstHeight;
};


#endif //MJNINDK_NATIVERENDER_H
