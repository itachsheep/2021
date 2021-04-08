#include<stdio.h>
#include<stdlib.h>


int max(int x,int y) 
{
    return x > y ? x :y;
}


void generate_10_random(int *array, int size, int(*getNextValue)(void))
{
    for (int i = 0; i < size; i++)
    {
        array[i] = getNextValue();
    }
}

int generate_random(void) {
    return rand();
}


int main()
{
    //函数指针
    // int (* p)(int,int);
    // p = &max;
    // int max = p(100,150);
    // printf("max = %d \n", max);

    //函数指针回调函数
    int array[10];
    generate_10_random(array,10,generate_random);
    for (int i = 0; i < 10; i++)
    {
        printf(" %d " , array[i]);
    }
    printf("\n");

    return 0;
}
