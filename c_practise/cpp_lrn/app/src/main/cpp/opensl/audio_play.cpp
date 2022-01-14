//
// Created by wei tao on 2022/1/12.
//

#include "audio_play.h"

void test_start() {
    LogD("%s test_start",__FILE_NAME__);
    OpenSLAudioPlay openSlAudioPlay(44100,16,1);
    openSlAudioPlay.init();
}