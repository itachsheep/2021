//
// Created by wei tao on 2022/1/12.
//

#ifndef CPP_LRN_OPENSL_AUDIO_PLAY_H
#define CPP_LRN_OPENSL_AUDIO_PLAY_H
#include <pthread.h>
#include <iostream>
#include "audio_engine.h"
#include <unistd.h>
#define SAMPLE_FORMAT_16 16

class OpenSLAudioPlay {
private:
    AudioEngine *mAudioEngine;
    SLObjectItf mPlayerObj;
    SLPlayItf  mPlayer;
    SLAndroidSimpleBufferQueueItf mBufferQueue;
    SLEffectSendItf mEffectSend;
    SLVolumeItf mVolume;
    SLmilliHertz mSampleRate;
    int mSampleFormat;
    int mChannels;
    uint8_t *mBuffers[2];
    SLuint32 mBuffSize;
    int mIndex;
    pthread_mutex_t mMutex;
public:
    OpenSLAudioPlay(int sampleRate,int sampleFormat,int channels);
    bool init();
    void enqueueSample(void *data, size_t length);
    void release();
    ~OpenSLAudioPlay();

    // 一帧音频播放完毕后就会回调这个函数
    friend void playerCallback(SLAndroidSimpleBufferQueueItf bq, void *context) {
        OpenSLAudioPlay *player = (OpenSLAudioPlay *)context;
        //可以增加延时，实现慢速播放
        usleep(1 * 1000);//停留 n 毫秒
        pthread_mutex_unlock(&player->mMutex);
    }
};

#endif //CPP_LRN_OPENSL_AUDIO_PLAY_H
