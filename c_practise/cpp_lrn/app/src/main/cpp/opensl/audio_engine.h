//
// Created by wei tao on 2022/1/11.
//

#ifndef CPP_LRN_AUDIO_ENGINE_H
#define CPP_LRN_AUDIO_ENGINE_H

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>
#include <stdio.h>
#include <assert.h>
#include "my_log.h"

class AudioEngine {
public:
    SLObjectItf engineObj;
    SLEngineItf engine;
    SLObjectItf outputMixObj;

    AudioEngine(): engineObj(nullptr),engine(nullptr),outputMixObj(nullptr) {
        createEngine();
    }
    virtual ~AudioEngine(){
        release();
    }
private:
    void createEngine();
    virtual void release();

};

#endif //CPP_LRN_AUDIO_ENGINE_H
