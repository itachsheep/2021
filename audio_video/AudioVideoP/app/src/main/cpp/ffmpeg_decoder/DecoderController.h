//
// Created by wei tao on 2021/8/9.
//

#ifndef AUDIOVIDEOP_DECODERCONTROLLER_H
#define AUDIOVIDEOP_DECODERCONTROLLER_H
#include "AudioDecoder.h"
#include <unistd.h>

#define CHANNEL_PER_FRAME	2
#define BITS_PER_CHANNEL		16
#define BITS_PER_BYTE		8

/** decode data to queue and queue size **/
#define QUEUE_SIZE_MAX_THRESHOLD 25
#define QUEUE_SIZE_MIN_THRESHOLD 20

class DecoderController {
protected:
    FILE *pcmFile;

    /** 伴奏的解码器 **/
    AudioDecoder *audioDecoder;
    /** 伴奏和原唱的采样频率与解码伴奏和原唱的每个packet的大小 **/
    int audioSampleRate;
    int audioPacketBufferSize;
public:
    DecoderController();
    ~DecoderController();

    /** 初始两个decoder,并且根据上一步算出的采样率，
     * 计算出伴奏和原唱的bufferSize **/
    void init(const char* audioPath, const char* pcmFilePath);
    /** 解码操作 **/
    void decode();
    /** 销毁这个controller **/
    void destroy();
};


#endif //AUDIOVIDEOP_DECODERCONTROLLER_H
