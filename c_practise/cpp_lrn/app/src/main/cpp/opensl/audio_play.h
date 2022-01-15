//
// Created by wei tao on 2022/1/12.
//

#ifndef CPP_LRN_AUDIO_PLAY_H
#define CPP_LRN_AUDIO_PLAY_H
#include "opensl_audio_play.h"
#include "my_log.h"
class MyPlayer {
private:

public:

};

void test_start();
void playPcm(JNIEnv *env,jstring pcmPath_);
void stopPcm();

#endif //CPP_LRN_AUDIO_PLAY_H
