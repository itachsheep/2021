//
// Created by wei tao on 2021/8/7.
//

#ifndef AUDIOVIDEOP_AUDIODECODER_H
#define AUDIOVIDEOP_AUDIODECODER_H

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "LogUtils.h"

#ifndef UINT64_C
#define UINT64_C(value)__CONCAT(value,ULL)
#endif

#ifndef INT64_MIN
#define INT64_MIN  (-9223372036854775807LL - 1)
#endif

#ifndef INT64_MAX
#define INT64_MAX	9223372036854775807LL
#endif

//含义：AudioPacket = struct AudioPacket
//如果没有typedef，就必须用struct AudioPacket audioPacket;来声明
typedef struct AudioPacket {
    static const int AUDIO_PACKET_ACTION_PLAY = 0;
    static const int AUDIO_PACKET_ACTION_PAUSE = 100;
    static const int AUDIO_PACKET_ACTION_SEEK = 101;

    short * buffer;
    int size;
    float position;
    int action;
    float extra_param1;
    float extra_param2;

    AudioPacket(){
        buffer = NULL;
        size = 0;
        position = -1;
        action = 0;
        extra_param1 = 0;
        extra_param2 = 0;
    }

    ~AudioPacket(){
        LOGCATD(__FILE_NAME__, "~AudioPacket");
        if(NULL != buffer) {
            delete [] buffer;
            buffer = NULL;
        }
    }

public:
    void testPrint() {
        LOGCATD(__FILE_NAME__, "this for test print");
    }

} AudioPacket;


#endif //AUDIOVIDEOP_AUDIODECODER_H
