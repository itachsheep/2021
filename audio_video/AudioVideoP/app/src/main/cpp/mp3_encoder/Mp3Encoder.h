//
// Created by wei tao on 2021/7/25.
//

#ifndef AUDIOVIDEOP_MP3ENCODER_H
#define AUDIOVIDEOP_MP3ENCODER_H

#include <lame/lame.h>

class Mp3Encoder {
private:
    FILE *pcmFile;
    FILE *mp3File;
    lame_t lameClient;
public:
    Mp3Encoder();

    ~Mp3Encoder();

    int Init(const char *pcmFilePath, const char *mp3FilePath,
             int sampleRate, int channels, int bitRate);
    void Encode();
    void Destroy();
};


#endif //AUDIOVIDEOP_MP3ENCODER_H
