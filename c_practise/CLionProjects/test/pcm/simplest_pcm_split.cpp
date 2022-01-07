//
// Created by wei tao on 2022/1/7.
//
#include "simplest_pcm_split.h"
#include <stdio.h>
#include <iostream>
using namespace std;

int simplest_pcm16le_split(char *url, char *left, char *right) {
    cout << "url = " << url << endl;
    cout << "left = " << left << endl;
    cout << "right = " << right << endl;
    FILE *source = fopen(url, "rb+");
    FILE *out_left = fopen(left,"wb+");
    FILE *out_right = fopen(right,"wb+");

    unsigned char *buff = (unsigned char *) malloc(4);

    while (!feof(source)) {
        fread(buff, 1, 4, source);
        fwrite(buff,1, 2,out_left);
        fwrite(buff + 2,1,2,out_right);
    }

    free(buff);
    fclose(source);
    fclose(out_left);
    fclose(out_right);
    return 0;
}