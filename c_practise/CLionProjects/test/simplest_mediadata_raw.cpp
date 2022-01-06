//
// Created by wei tao on 2022/1/4.
//
#include <stdio.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

string CUR_DIR = "/Users/weitao/f/2021/c_practise/CLionProjects/test/";
const string OUTPUT_YUV_SUFFIX = "_output_256x256_yuv420p.yuv";

int simplest_yuv420_split(char *url, int w, int h, int num) {
    cout << "start" << endl;
    FILE *fp = fopen(url, "rb+");

    cout << "start 11" << endl;
    FILE *fp1 = fopen("/Users/weitao/f/2021/c_practise/CLionProjects/test/output_420_y.y", "wb+");
    FILE *fp2 = fopen("/Users/weitao/f/2021/c_practise/CLionProjects/test/output_420_u.y", "wb+");
    FILE *fp3 = fopen("/Users/weitao/f/2021/c_practise/CLionProjects/test/output_420_v.y", "wb+");
    cout << "start 22" << endl;
    unsigned char *pic = (unsigned char *) (malloc(w * h * 3 / 2));

    for (int i = 0; i < num; ++i) {
        cout << "start 222" << endl;
        //函数每次从stream中最多读取 nitems 个单元，每个单元大小为size个字节，
        // 将读取的数据放到buffer；文件流的位置指针后移 size * count 字节。
        fread(pic, 1, w * h * 3 / 2, fp);
        cout << "start 33" << endl;
        fwrite(pic, 1, w * h, fp1);
        fwrite(pic + w * h, 1, w * h / 4, fp2);
        cout << "start 44" << endl;
        fwrite(pic + w * h * 5 / 4, 1, w * h / 4, fp3);
    }

    cout << "start 55" << endl;
    free(pic);
    fclose(fp);
    fclose(fp1);
    fclose(fp2);
    fclose(fp3);
    return 0;
}


int simplest_yuv420_gray(char *url, int w, int h, int num) {

    FILE *fp = fopen(url, "rb+");
    char *p = (char *) (CUR_DIR + "gray_3" + OUTPUT_YUV_SUFFIX).c_str();
    FILE *fp_output = fopen(p, "wb+");

    //如果视频帧的宽和高分别为w和h，那么一帧YUV420P像素数据一共占用w*h*3/2 Byte的数据。
    // 其中前w*h Byte存储Y，接着的w*h*1/4 Byte存储U，最后w*h*1/4 Byte存储V
    unsigned char *pic = (unsigned char * )malloc( w * h * 3 / 2);

    for (int i = 0; i < num; ++i) {
        fread(pic, 1 , w * h * 3 / 2, fp);

        //gray
        //从pic + w * h 指针位置开始，往后的n个字节用 128 替换并且返回，pic + w * h指针
        memset(pic + w * h, 0, w * h / 4);
        //如果想把YUV格式像素数据变成灰度图像，只需要将U、V分量设置成128即可。这是因为U、V是图像中的经过偏置处理的色度分量。
        // 色度分量在偏置处理前的取值范围是-128至127，这时候的无色对应的是“0”值。
        // 经过偏置后色度分量取值变成了0至255，因而此时的无色对应的就是128了。

        fwrite(pic,1, w * h * 3 / 2, fp_output);
    }

    free(pic);
    fclose(fp);
    fclose(fp_output);
    return 0;
}

void simplest_yuv_420_border(char *url, int w, int h,int border, int num) {
    unsigned int yuvSize = w * h * 3 / 2;

    FILE *fp_in = fopen(url, "rb+");
    char *p = (char *) (CUR_DIR + "border" + OUTPUT_YUV_SUFFIX).c_str();
    FILE *fp_out = fopen(p, "wb+");

    unsigned char *pic = (unsigned char *) malloc( yuvSize);
    for (int i = 0; i < num; ++i) {
        fread(pic,1, yuvSize, fp_in);
        for (int j = 0; j < h; ++j) {
            for (int k = 0; k < w; ++k) {
                if(k < border || k > w - border || j < border || j > h - border) {
                    pic[j * w + k] = 255;
                }
            }
        }
        fwrite(pic, 1, yuvSize, fp_out);
    }
    free(pic);
    fclose(fp_in);
    fclose(fp_out);

}

void copyFile(char *url) {
    cout << "url = " << url << endl;
    FILE *in = fopen(url,"rb+");
    FILE *out = fopen((char *)(CUR_DIR + "copy" +OUTPUT_YUV_SUFFIX).c_str(),"wb+");

    const int buffSize = 2048;
    unsigned char buff[buffSize];
    int readCount = 0;
    while (!feof(in)) {
        readCount = fread(buff,sizeof(unsigned char),buffSize,in);
        cout << "count = " << readCount << endl;
        fwrite(buff,sizeof(unsigned char),readCount,out);
    }
    fclose(in);
    fclose(out);
}