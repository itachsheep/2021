//
// Created by wei tao on 2022/1/11.
//
#include "audio_engine.h"

void AudioEngine::createEngine() {
    // 音频的播放，就涉及到了，OpenLSES
    //// 第一大步：创建引擎并获取引擎接口
    // 1.1创建引擎对象：SLObjectItf engineObject
    SLresult result = slCreateEngine(&engineObj,0,NULL,0,NULL,NULL);
    if(SL_RESULT_SUCCESS != result) {
        LogE("createEngine error 1");
        return;
    }

    // 1.2 初始化引擎
    result = (*engineObj)->Realize(engineObj,SL_BOOLEAN_FALSE);
    if(SL_BOOLEAN_FALSE != result) {
        LogE("createEngine error 2");
        return;
    }

    // 1.3 获取引擎接口 SLEngineItf engineInterface
    result = (*engineObj)->GetInterface(engineObj,SL_IID_ENGINE,&engine);
    if(SL_RESULT_SUCCESS != result) {
        LogE("createEngine error 3");
        return;
    }

    //// 第二大步 设置混音器
    // 2.1 创建混音器：SLObjectItf outputMixObject
    result = (*engine)->CreateOutputMix(engine, &outputMixObj,0,0,0);
    if(SL_RESULT_SUCCESS != result) {
        LogE("createEngine error 4");
        return;
    }

    // 2.2 初始化 混音器
    result = (*outputMixObj)->Realize(outputMixObj,SL_BOOLEAN_FALSE);
    if(SL_BOOLEAN_FALSE != result) {
        LogE("createEngine error 5");
        return;
    }
}
void AudioEngine::release() {
    if(outputMixObj) {
        (*outputMixObj)->Destroy(outputMixObj);
        outputMixObj = nullptr;
    }

    if(engineObj) {
        (*engineObj)->Destroy(engineObj);
        engineObj = nullptr;
    }
}

