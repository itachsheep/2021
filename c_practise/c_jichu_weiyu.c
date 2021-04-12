#include<stdio.h>
#include<string.h>

struct Data1
{
    unsigned int widthValid;
    unsigned int heightValid;
};

struct Data2
{
    unsigned int widthValid:1;
    unsigned int heightValid:1;
};




int main()
{
    struct Data1 data1;
    struct Data2 data2;
    printf("data1 size = %ld \n", sizeof(data1));
    printf("data2 size = %ld \n ",sizeof(data2));
    return 0;
}