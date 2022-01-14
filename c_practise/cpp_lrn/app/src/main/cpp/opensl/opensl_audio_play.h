//
// Created by wei tao on 2022/1/12.
//

#ifndef CPP_LRN_OPENSL_AUDIO_PLAY_H
#define CPP_LRN_OPENSL_AUDIO_PLAY_H
#include <pthread.h>
#include "audio_engine.h"
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
    ~OpenSLAudioPlay();
    friend void playerCallback();
};

#endif //CPP_LRN_OPENSL_AUDIO_PLAY_H
