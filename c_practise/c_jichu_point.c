#include<stdio.h>



enum season { spring, summer, autumn,winter};

int main()
{
    // int a = 10;
    // int* ptr;

    // ptr = &a;
    // printf("a = %d \n",a);
    // printf("*ptr = %d \n", *ptr);

    enum season sea;
    sea = summer;

    for(sea = spring; sea <= winter; sea++) {
         printf("sea  =  %d \n",sea);
    }

}