//
// Created by wei tao on 2021/7/26.
//
#include "Mp3Encoder.h"
#include "LogUtils.h"

Mp3Encoder::Mp3Encoder() {

}

Mp3Encoder::~Mp3Encoder() {
    LOGCATD("~Mp3Encoder");
}

int Mp3Encoder::Init(const char *pcmFilePath, const char *mp3FilePath,
                     int sampleRate, int channels, int bitRate) {
    int ret = -1;
    pcmFile = fopen(pcmFilePath, "rb");
    if (pcmFile) {
        mp3File = fopen(mp3FilePath, "wb");
        if (mp3File) {
            LOGCATD("Init lame");
            lameClient = lame_init();
            lame_set_in_samplerate(lameClient, sampleRate);
            lame_set_out_samplerate(lameClient, sampleRate);
            lame_set_num_channels(lameClient, channels);
            lame_set_brate(lameClient, bitRate);
            lame_init_params(lameClient);
        }
    }
}

void Mp3Encoder::Encode() {
    int bufferSize = 1024 * 256;//256byte?
    short *buffer = new short[bufferSize / 2];
    short *leftBuffer = new short[bufferSize / 4];
    short *rightBuffer = new short[bufferSize / 4];
    uint8_t *mp3_buffer = new uint8_t[bufferSize];
    int readBufferSize = 0;
    while ((readBufferSize == fread(buffer, 2, bufferSize / 2, pcmFile)) > 0){
        for (int i = 0; i < readBufferSize; ++i) {
            if(i % 2 == 0) {
                leftBuffer[i / 2] = buffer[i];
            } else {
                rightBuffer[i / 2] = buffer[i];
            }
        }
        //todo:
        int wroteSize = lame_encode_buffer(lameClient,leftBuffer,rightBuffer,
                readBufferSize / 2,/* number of samples per channel */
                mp3_buffer,/* pointer to encoded MP3 stream */
                bufferSize);/* number of valid octets in this stream */
        fwrite(mp3_buffer,1,wroteSize,mp3File);
        LOGCATD("Encode wroteSize = %d",wroteSize);
    }
    delete[] buffer;
    delete[] leftBuffer;
    delete[] rightBuffer;
    delete [] mp3_buffer;
}

void Mp3Encoder::Destroy() {
    if(pcmFile) {
        fclose(pcmFile);
    }
    if(mp3File) {
        fclose(mp3File);
    }
}
