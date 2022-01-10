//
// Created by wei tao on 2022/1/10.
//

#include "simplest_flv_parser.h"

void parse_audio(const char *out_audio, int output_a, FILE *fp_in, FILE *my_out, FILE *&afh, TAG_HEADER &tagHeader);

void
parse_video(const char *out_video, int output_v, FILE *fp_in, FILE *my_out, FLV_HEADER &flv, uint &previousTagSize_z,
            FILE *&vfh, TAG_HEADER &tagHeader);

void strcat_audio_tag_with_type(char *audiotag_str, int x);

void strcat_audio_tag_with_hz(char *audiotag_str, int x);

void strcat_audio_tag_with_size(char *audiotag_str, int x);

void strcat_audio_tag_with_first_byte(char *audiotag_str, int x);

void strcat_video_with_frame(char *videotag_str, int x);

void strcat_video_with_first_byte(char *videotag_str, int x);

/**
 * //reverse_bytes - turn a BigEndian byte array into a LittleEndian integer
 * @return
 */
uint reverse_bytes(byte *p, char c) {
    int r = 0;
    int i = 0;
    //printf("c = %d \n", c);
    for (int i = 0; i < c; ++i) {
        //printf("%d, %X \n",i,*(p + i));
        r |= (*(p + i) << ((c - 1) * 8) - 8 * i);
        //printf("r = %X \n", r);
    }
    return r;
}

int simplest_flv_parser(char *source, char *out_audio, char *out_video) {
    //whether output audio/video stream
    int output_a = 1;
    int output_v = 1;

    FILE *fp_in = NULL, *vfh = NULL, *afh = NULL;
    FILE *my_out = stdout;

    FLV_HEADER flv;
    TAG_HEADER tagHeader;
    uint previousTagSize, previousTagSize_z = 0;
    uint ts = 0, ts_new = 0;

    fp_in = fopen(source, "rb+");
    if (!fp_in) {
        printf("open source file error \n");
        return -1;
    }

    fread((char *) &flv, 1, sizeof(FLV_HEADER), fp_in);
    fprintf(my_out, "============== FLV Header ==============\n");
    fprintf(my_out, "Signature:  0x %c %c %c \n", flv.Signature[0], flv.Signature[1], flv.Signature[2]);
    fprintf(my_out, "Version:    0x %X \n", flv.Version);
    fprintf(my_out, "Flags  :    0x %X \n", flv.Flags);
    fprintf(my_out, "HeaderSize: 0x %X \n", reverse_bytes((byte *) &flv.DataOffset, sizeof(flv.DataOffset)));
    fprintf(my_out, "======================================== \n");

    //move the file pointer to the end of the header
    // 函数设置文件指针stream的位置。如果执行成功，stream将指向以fromWhere(SEEK_SET)为基准，
    // 偏移offset(size)个字节的位置。如果执行失败(比如offset超过文件自身大小)，
    // 则不改变stream指向的位置。
    //  返回值: 成功，返回0，否则返回其他值。
    uint seek = reverse_bytes((byte *) &flv.DataOffset, sizeof(flv.DataOffset));
    printf("seek = %d \n", seek);
    fseek(fp_in, seek, SEEK_SET);
    do {
        previousTagSize = getw(fp_in);
        fread((void *) &tagHeader, sizeof(TAG_HEADER), 1, fp_in);
        //int temp_datasize1=reverse_bytes((byte *)&tagheader.DataSize, sizeof(tagheader.DataSize));
        int tagheader_datasize = tagHeader.DataSize[0] * 65536 + tagHeader.DataSize[1] * 256 + tagHeader.DataSize[2];
        int tagheader_timestamp =
                tagHeader.TimeStamp[0] * 65536 + tagHeader.TimeStamp[1] * 256 + tagHeader.TimeStamp[2];
        char tagtype_str[10];
        switch (tagHeader.TagType) {
            case TAG_TYPE_AUDIO:
                sprintf(tagtype_str, "AUDIO");
                break;
            case TAG_TYPE_VIDEO:
                sprintf(tagtype_str, "VIDEO");
                break;
            case TAG_TYPE_SCRIPT:
                sprintf(tagtype_str, "SCRIPT");
                break;
            default:
                sprintf(tagtype_str, "UNKNOWN");
                break;
        }
        fprintf(my_out, "[%6s] %6d %6d |", tagtype_str, tagheader_datasize, tagheader_timestamp);
        //if we are not past the end of file, process the tag
        if (feof(fp_in)) {
            break;
        }

        //process tag by type
        switch (tagHeader.TagType) {
            case TAG_TYPE_AUDIO: {
                parse_audio(out_audio, output_a, fp_in, my_out, afh, tagHeader);
                break;
            }
            case TAG_TYPE_VIDEO: {
                parse_video(out_video, output_v, fp_in, my_out, flv, previousTagSize_z, vfh, tagHeader);
                break;
            }
            default:
                ////skip the data of this tag
                fseek(fp_in, reverse_bytes((byte *) &tagHeader.DataSize, sizeof(tagHeader.DataSize)), SEEK_CUR);
        }
        fprintf(my_out, "\n");
    } while (!feof(fp_in));

    fclose(fp_in);
    fclose(vfh);
    fclose(afh);
    fclose(my_out);
    return 0;
}

void parse_video(const char *out_video,
                 int output_v, FILE *fp_in, FILE *my_out,
                 FLV_HEADER &flv, uint &previousTagSize_z,
                 FILE *&vfh, TAG_HEADER &tagHeader) {
    char videotag_str[100] = {0};
    strcat(videotag_str, "| ");
    char tagdata_first_byte;
    tagdata_first_byte = fgetc(fp_in);
    int x = tagdata_first_byte & 0xF0;
    x = x >> 4;
    strcat_video_with_frame(videotag_str, x);
    x = tagdata_first_byte & 0x0F;
    strcat_video_with_first_byte(videotag_str, x);
    fprintf(my_out, "%s", videotag_str);
    fseek(fp_in, -1, SEEK_CUR);
    //if the output file hasn't been opened, open it.
    if (vfh == NULL && output_v != 0) {
        //write the flv header (reuse the original file's hdr) and first previoustagsize
        vfh = fopen(out_video, "wb");
        fwrite((char *) &flv, 1, sizeof(flv), vfh);
        fwrite((char *) &previousTagSize_z, 1, sizeof(previousTagSize_z), vfh);
    }
    //TagData + Previous Tag Size
    int data_size = reverse_bytes((byte *) &tagHeader.DataSize, sizeof(tagHeader.DataSize)) + 4;
    if (output_v != 0) {
        //TagHeader
        fwrite((char *) &tagHeader, 1, sizeof(tagHeader), vfh);
        //TagData
        for (int i = 0; i < data_size; i++)
            fputc(fgetc(fp_in), vfh);
    } else {
        for (int i = 0; i < data_size; i++)
            fgetc(fp_in);
    }
    ////rewind 4 bytes, because we need to read the previoustagsize again for the loop's sake
    fseek(fp_in, -4, SEEK_CUR);
}

void strcat_video_with_first_byte(char *videotag_str, int x) {
    switch (x) {
        case 1:
            strcat(videotag_str, "JPEG (currently unused)");
            break;
        case 2:
            strcat(videotag_str, "Sorenson H.263");
            break;
        case 3:
            strcat(videotag_str, "Screen video");
            break;
        case 4:
            strcat(videotag_str, "On2 VP6");
            break;
        case 5:
            strcat(videotag_str, "On2 VP6 with alpha channel");
            break;
        case 6:
            strcat(videotag_str, "Screen video version 2");
            break;
        case 7:
            strcat(videotag_str, "AVC");
            break;
        default:
            strcat(videotag_str, "UNKNOWN");
            break;
    }
}

void strcat_video_with_frame(char *videotag_str, int x) {
    switch (x) {
        case 1:
            strcat(videotag_str, "key frame  ");
            break;
        case 2:
            strcat(videotag_str, "inter frame");
            break;
        case 3:
            strcat(videotag_str, "disposable inter frame");
            break;
        case 4:
            strcat(videotag_str, "generated keyframe");
            break;
        case 5:
            strcat(videotag_str, "video info/command frame");
            break;
        default:
            strcat(videotag_str, "UNKNOWN");
            break;
    }
    strcat(videotag_str, "| ");
}

void parse_audio(const char *out_audio,
                 int output_a, FILE *fp_in,
                 FILE *my_out, FILE *&afh, TAG_HEADER &tagHeader) {
    char audiotag_str[100] = {0};
    strcat(audiotag_str, "| ");
    char tagdata_first_byte;
    tagdata_first_byte = fgetc(fp_in);
    int x = tagdata_first_byte & 0xF0;
    x = x >> 4;
    strcat_audio_tag_with_type(audiotag_str, x);
    x = tagdata_first_byte & 0x0C;
    x = x >> 2;
    strcat_audio_tag_with_hz(audiotag_str, x);
    x = tagdata_first_byte & 0x02;
    x = x >> 1;
    strcat_audio_tag_with_size(audiotag_str, x);
    x = tagdata_first_byte & 0x01;
    strcat_audio_tag_with_first_byte(audiotag_str, x);
    fprintf(my_out, "%s", audiotag_str);

    //if the output file hasn't been opened, open it.
    if (output_a != 0 && afh == NULL) {
        afh = fopen(out_audio, "wb");
    }

    //TagData - First Byte Data
    int data_size = reverse_bytes((byte *) &tagHeader.DataSize, sizeof(tagHeader.DataSize)) - 1;
    if (output_a != 0) {
        //TagData+1
        for (int i = 0; i < data_size; i++)
            fputc(fgetc(fp_in), afh);

    } else {
        for (int i = 0; i < data_size; i++)
            fgetc(fp_in);
    }
}

void strcat_audio_tag_with_first_byte(char *audiotag_str, int x) {
    switch (x) {
        case 0:
            strcat(audiotag_str, "Mono");
            break;
        case 1:
            strcat(audiotag_str, "Stereo");
            break;
        default:
            strcat(audiotag_str, "UNKNOWN");
            break;
    }
}

void strcat_audio_tag_with_size(char *audiotag_str, int x) {
    switch (x) {
        case 0:
            strcat(audiotag_str, "8Bit");
            break;
        case 1:
            strcat(audiotag_str, "16Bit");
            break;
        default:
            strcat(audiotag_str, "UNKNOWN");
            break;
    }
    strcat(audiotag_str, "| ");
}

void strcat_audio_tag_with_hz(char *audiotag_str, int x) {
    switch (x) {
        case 0:
            strcat(audiotag_str, "5.5-kHz");
            break;
        case 1:
            strcat(audiotag_str, "1-kHz");
            break;
        case 2:
            strcat(audiotag_str, "22-kHz");
            break;
        case 3:
            strcat(audiotag_str, "44-kHz");
            break;
        default:
            strcat(audiotag_str, "UNKNOWN");
            break;
    }
    strcat(audiotag_str, "| ");
}

void strcat_audio_tag_with_type(char *audiotag_str, int x) {
    switch (x) {
        case 0:
            strcat(audiotag_str, "Linear PCM, platform endian");
            break;
        case 1:
            strcat(audiotag_str, "ADPCM");
            break;
        case 2:
            strcat(audiotag_str, "MP3");
            break;
        case 3:
            strcat(audiotag_str, "Linear PCM, little endian");
            break;
        case 4:
            strcat(audiotag_str, "Nellymoser 16-kHz mono");
            break;
        case 5:
            strcat(audiotag_str, "Nellymoser 8-kHz mono");
            break;
        case 6:
            strcat(audiotag_str, "Nellymoser");
            break;
        case 7:
            strcat(audiotag_str, "G.711 A-law logarithmic PCM");
            break;
        case 8:
            strcat(audiotag_str, "G.711 mu-law logarithmic PCM");
            break;
        case 9:
            strcat(audiotag_str, "reserved");
            break;
        case 10:
            strcat(audiotag_str, "AAC");
            break;
        case 11:
            strcat(audiotag_str, "Speex");
            break;
        case 14:
            strcat(audiotag_str, "MP3 8-Khz");
            break;
        case 15:
            strcat(audiotag_str, "Device-specific sound");
            break;
        default:
            strcat(audiotag_str, "UNKNOWN");
            break;
    }
    strcat(audiotag_str, "| ");
}
