#include<stdio.h>



enum season { spring, summer, autumn,winter};

int main()
{
    // int a = 10;
    // int* ptr;

    // ptr = &a;
    // printf("a = %d \n",a);
    // printf("*ptr = %d \n", *ptr);


    //enum使用
    // enum season sea;
    // sea = summer;
    // for(sea = spring; sea <= winter; sea++) {
    //      printf("sea  =  %d \n",sea);
    // }


    //指针,内存地址
    int test = 100;
    int* p;
    p = &test;
    printf("p = %p, *p = %d \n ",p,*p);
    if(p) {
        printf("p is not null \n");
    } 
    else {
        printf("p is null \n"); 
    }

    p = NULL;
    printf("p = %p \n ",p);
    if(p) {
        printf("p is not null \n");
    } 
    else {
        printf("p is null \n");
    }

}