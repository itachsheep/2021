#include<stdio.h>
#include<string.h>

union Data
{
    int i;
    float f;
    char str[20];
};


int main()
{
    union Data data;
    data.i = 200;
    printf("data.i = %d \n ", data.i);
    data.f = 220.5;
    strcpy(data.str," c programing");

    printf("data size = %ld \n", sizeof(data));
    printf("data.i = %d \n ", data.i);
    printf("data.f = %f \n ", data.f);
    printf("data.str = %s \n", data.str);
    return 0;
}