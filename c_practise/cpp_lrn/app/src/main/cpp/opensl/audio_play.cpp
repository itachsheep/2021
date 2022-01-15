//
// Created by wei tao on 2022/1/12.
//

#include "audio_play.h"

void test_start() {
    LogD("%s test_start", __FILE_NAME__);
    OpenSLAudioPlay openSlAudioPlay(44100, 16, 1);
    openSlAudioPlay.init();
}

OpenSLAudioPlay *slAudioPlayer = nullptr;
FILE *pcmFile = nullptr;
bool isPlaying = false;

void *playThreadFunc(void *arg) {
    const int buffSize = 2048;
    short buffer[buffSize];
    while (isPlaying && !feof(pcmFile)) {
        fread(buffer,1,buffSize,pcmFile);
        slAudioPlayer->enqueueSample(buffer,buffSize);
    }

    return 0;
}

void playPcm(JNIEnv *env, jstring pcmPath_) {
    LogD("%s playPcm", __FILE_NAME__);
    const char *pcmPath = env->GetStringUTFChars(pcmPath_, NULL);
    if (slAudioPlayer) {
        slAudioPlayer->release();
        delete slAudioPlayer;
        slAudioPlayer = nullptr;
    }
    slAudioPlayer = new OpenSLAudioPlay(11, 22, 33);
    slAudioPlayer->init();
    pcmFile = fopen(pcmPath, "r");
    isPlaying = true;
    pthread_t playThread;
    pthread_create(&playThread, nullptr, playThreadFunc, 0);

    env->ReleaseStringUTFChars(pcmPath_, pcmPath);
}

void stopPcm() {
    LogD("%s stopPcm",__FILE_NAME__);
    isPlaying = false;
    if(slAudioPlayer) {
        slAudioPlayer->release();
        delete slAudioPlayer;
        slAudioPlayer = nullptr;
    }
    if(pcmFile) {
        fclose(pcmFile);
        delete pcmFile;
        pcmFile = nullptr;
    }
}