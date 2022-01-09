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

int simplest_pcm16le_cut_single_channel(char *source, char *out, char *out_stat, int start_num, int dur_num) {
    FILE *fp_in = fopen(source, "rb+");
    FILE *fp_out = fopen(out, "wb+");
    FILE *fp_out_stat = fopen(out_stat, "wb+");

    unsigned char *buff = (unsigned char *) malloc(2);
    int cnt = 0;
    while (!feof(fp_in)) {
        fread(buff, 1, 2, fp_in);
        if (cnt >= start_num && cnt <= (start_num + dur_num)) {
            fwrite(buff, 1, 2, fp_out);
            short sample_num = buff[1];
            //unsigned char: 0 ~ 255, 小端模式，高位在低位
            sample_num = sample_num * 256;
            sample_num = sample_num + buff[0];
            //printf("[%d, %d]    ", buff[1],buff[0]);
            fprintf(fp_out_stat, "%6d", sample_num);
            //cout << " " << sample_num << ", ";
            if (cnt % 10 == 0) {
                //cout << endl;
                printf("\n ");
                fprintf(fp_out_stat, "\n", sample_num);
            }
        }
        cnt++;
    }
    free(buff);
    fclose(fp_in);
    fclose(fp_out);
    fclose(fp_out_stat);
    return 0;
}