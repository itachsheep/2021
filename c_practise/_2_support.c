#include<stdio.h>

extern int count;

void write_extern(void)
{
    printf("write_extern count = %d \n",count);
}