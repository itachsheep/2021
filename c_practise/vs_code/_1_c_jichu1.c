#include<stdio.h>

int count;

extern void write_extern();

//需要执行：
//1， gcc _1_c_jichu1.c _2_support.c 
//2， ./a.out

int main() 
{
    count = 15;
    write_extern();
}