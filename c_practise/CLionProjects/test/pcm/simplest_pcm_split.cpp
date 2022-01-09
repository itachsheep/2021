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
    FILE *out_left = fopen(left, "wb+");
    FILE *out_right = fopen(right, "wb+");

    unsigned char *buff = (unsigned char *) malloc(4);

    while (!feof(source)) {
        fread(buff, 1, 4, source);
        fwrite(buff, 1, 2, out_left);
        fwrite(buff + 2, 1, 2, out_right);
    }

    free(buff);
    fclose(source);
    fclose(out_left);
    fclose(out_right);
    return 0;
}

int simplest_pcm16le_doublespeed(char *url, char *out) {
    FILE *fp_in = fopen(url, "rb+");
    FILE *fp_out = fopen(out, "wb+");

    int cnt = 0;

    unsigned char *sample = (unsigned char *) malloc(4);

    while (!feof(fp_in)) {

        fread(sample, 1, 4, fp_in);

        if (cnt % 2 != 0) {
            //L
            fwrite(sample, 1, 2, fp_out);
            //R
            fwrite(sample + 2, 1, 2, fp_out);
        }
        cnt++;
    }
    printf("Sample Cnt:%d\n", cnt);

    free(sample);
    fclose(fp_in);
    fclose(fp_out);
    return 0;
}