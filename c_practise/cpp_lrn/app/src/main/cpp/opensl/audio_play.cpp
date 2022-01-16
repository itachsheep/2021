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
int playCount = -1;

void *playThreadFunc(void *arg) {
    const int buffSize = 2048;
    short buffer[buffSize];
    while (isPlaying && !feof(pcmFile)) {
        int length = fread(buffer, 1, buffSize, pcmFile);
        //LogD("%s pcm read length = %d", __FILE_NAME__, length);
        slAudioPlayer->enqueueSample(buffer, length);
    }
    return 0;
}


pthread_t tIds[100];
void playPcm(JNIEnv *env, jstring pcmPath_) {
    playCount++;
    const char *pcmPath = env->GetStringUTFChars(pcmPath_, NULL);
    if (slAudioPlayer) {
        slAudioPlayer->release();
        delete slAudioPlayer;
        slAudioPlayer = nullptr;
    }
    slAudioPlayer = new OpenSLAudioPlay(44100, SAMPLE_FORMAT_16, 1);
    slAudioPlayer->init();
    pcmFile = fopen(pcmPath, "r");
    isPlaying = true;
    LogD("%s playPcm playCount = %d, pcmFile = %p", __FILE_NAME__,playCount,pcmFile);
    pthread_create(&tIds[playCount], nullptr, playThreadFunc, NULL);
    env->ReleaseStringUTFChars(pcmPath_, pcmPath);
}

void stopPcm() {
    isPlaying = false;
    if (slAudioPlayer) {
        slAudioPlayer->release();
        delete slAudioPlayer;
        slAudioPlayer = nullptr;
    }
    LogD("stopPcm pcmFile = %p ",pcmFile);
    if (pcmFile) {
        fclose(pcmFile);
//        delete pcmFile;
        pcmFile = nullptr;
    }
}