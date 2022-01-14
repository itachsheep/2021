//
// Created by wei tao on 2022/1/13.
//

#include "base_c.h"

void Box::setWidth(int w) {
    //this->width = w;
    width = w;
}

void test_multi_dimensional_array() {
    int arr[2][3] = {
            {1, 2, 3},
            {4, 5, 6}
    };
    LogD( "%s arr[1][1] = %d", arr[1][1],__FILE_NAME__);

    int num = 10;
    int *b = &num;

    int sr[2] = {1, 2};
    int mr[3] = {5, 6, 8};
    /// 首先a[2] 代表是个数组，类型为 int*
    /// 所以a[0] 元素是 int * ，也就是一个int类型指针，所以，要赋值一个指针
    int *a[2] = {sr, mr};
    LogD( "%s a[0] pointer = %d", a[0],__FILE_NAME__);
    LogD( "%s a[0] value = %d", *a[0],__FILE_NAME__);
}
