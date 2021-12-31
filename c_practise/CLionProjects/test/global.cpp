//
// Created by wei tao on 2021/12/30.
//
#include <iostream>


using namespace std;

//全局变量
int globe  = 100;

void func() {
    static int num = 0;
    cout << "num = " << num++ << endl;
}


