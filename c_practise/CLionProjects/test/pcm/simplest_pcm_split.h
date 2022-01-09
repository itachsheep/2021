//
// Created by wei tao on 2022/1/7.
//

#ifndef TEST_SIMPLEST_PCM_SPLIT_H
#define TEST_SIMPLEST_PCM_SPLIT_H

int simplest_pcm16le_split(char *url, char *left, char *right);
int simplest_pcm16le_doublespeed(char *url, char *out);
int simplest_pcm16le_cut_single_channel(char *source,char *out, char *out_stat, int start_num, int dur_num);


#endif //TEST_SIMPLEST_PCM_SPLIT_H
