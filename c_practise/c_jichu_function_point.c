#include<stdio.h>

int max(int x,int y) 
{
    return x > y ? x :y;
}

int main()
{
    int (* p)(int,int);
    p = &max;
    int max = p(100,150);
    printf("max = %d \n", max);
    return 0;
}
