//
// Created by wei tao on 2022/1/4.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int simplest_yuv420_split(char *url, int w, int h ,int num) {
    FILE *fp = fopen(url,"rb+");

    FILE *fp1 = fopen("output_420_y.y","wb+");
    FILE *fp2 = fopen("output_420_u.y","wb+");
    FILE *fp3 = fopen("output_420_v.y","wb+");

    unsigned char *pic = (unsigned char *)(malloc(w * h * 3 / 2));

    for (int i = 0; i < num; ++i) {
        //函数每次从stream中最多读取count个单元，每个单元大小为size个字节，
        // 将读取的数据放到buffer；文件流的位置指针后移 size * count 字节。
        fread(pic,1, w * h * 3 / 2, fp);

        fwrite(pic,1,w * h,fp1);
        fwrite(pic + w * h, 1, w * h / 4, fp2);
        fwrite(pic + w * h * 5 / 4,1, w * h / 4, fp3);
    }

    free(pic);
    fclose(fp);
    fclose(fp1);
    fclose(fp2);
    fclose(fp3);
    return 0;
}