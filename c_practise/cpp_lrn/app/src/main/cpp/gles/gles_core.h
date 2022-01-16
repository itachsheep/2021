//
// Created by wei tao on 2022/1/16.
//

#ifndef CPP_LRN_GLES_CORE_H
#define CPP_LRN_GLES_CORE_H
#include <jni.h>
#include "play_callback.h"

class GLESPlay {
public:
    GLESPlay(JNIEnv *env, jobject thiz, PlayCallback *playCallback,
            const char *data_source, jobject pJobject);

private:

};


#endif //CPP_LRN_GLES_CORE_H
