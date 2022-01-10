//
// Created by wei tao on 2022/1/10.
//

#ifndef TEST_SIMPLEST_FLV_PARSER_H
#define TEST_SIMPLEST_FLV_PARSER_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Important!
#pragma pack(1)

#define TAG_TYPE_SCRIPT 18
#define TAG_TYPE_AUDIO  8
#define TAG_TYPE_VIDEO  9

typedef unsigned char byte;
typedef unsigned int uint;

typedef struct {
    byte Signature[3];
    byte Version;
    byte Flags;
    uint DataOffset;
} FLV_HEADER;

typedef struct {
    byte TagType;
    byte DataSize[3];
    byte TimeStamp[3];
    uint Reserved;
} TAG_HEADER;


int simplest_flv_parser(char *source,char *out_audio, char * out_video) ;

#endif //TEST_SIMPLEST_FLV_PARSER_H
