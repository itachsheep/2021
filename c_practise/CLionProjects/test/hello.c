//
// Created by wei tao on 2022/1/4.
//
#include <stdio.h>

int printHello() {
    printf("hello world !!\n");
    return 0;
}

int printSize() {
    printf("\n\n 整数类型 \n");

    //char 1 字节
    printf("char 存储大小: %lu \n", sizeof(char));
    printf("unsinged char 存储大小: %lu \n", sizeof(unsigned char));

    //short 2 字节
    printf("short 存储大小: %lu \n", sizeof(short));
    printf("unsinged short 存储大小: %lu \n", sizeof(unsigned short));

    //int 4 字节
    printf("int 存储大小: %lu \n", sizeof(int));
    printf("unsinged int 存储大小: %lu \n", sizeof(unsigned int));


    //long 4/8 字节
    printf("long 存储大小: %lu \n", sizeof(long));
    printf("unsinged long 存储大小: %lu \n", sizeof(unsigned long));
}