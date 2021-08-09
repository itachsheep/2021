//
// Created by wei tao on 2021/8/9.
//
#include "DecoderController.h"
#include "LogUtils.h"

#define TAG __FILE_NAME__
DecoderController::DecoderController() {
    audioDecoder = NULL;
    pcmFile = NULL;
}

DecoderController::~DecoderController() {
    LOGCATD("%s ~DecoderController",TAG);
}

void DecoderController::init(const char *audioPath, const char *pcmFilePath) {
    //初始化两个decoder
    AudioDecoder *tempDecoder = new AudioDecoder();
    int metaData[2];
    tempDecoder->getMusicMeta(audioPath,metaData);
    delete tempDecoder;

}