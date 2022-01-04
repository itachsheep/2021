#include <iostream>
#include "common.h"

#include "hello.c"
using namespace std;


extern int globe;
extern void func();
extern int simplest_yuv420_split(char *url, int w, int h ,int num);
void test() {
    std::cout << "Hello, World!" << std::endl;
    std::cout << "Hello, World11111!" << std::endl;
    std::cout << "Hello, World1122!" << std::endl;

    cout << "短斤少两发的快递"
            "拉德斯基发了多少积分"
            "看极乐世界地方" << endl;
    cout << globe << endl;

    func();
    func();
}

/**
 *  extern "C"是连接申明(linkagedeclaration),被extern"C"修饰的变量和函数是按照C语言方式编译和连接的。
 *  作为一种面向对象的语言，C++支持函数重载，而过程式语言C则不支持。
 *  函数被C++编译后在符号库中的名字与C语言的不同。
 *  例如，假设某个函数的原型为：
 *      void foo( int x, int y);
 *  该函数被C编译器编译后在符号库中的名字为_foo，而C++编译器则会产生像_foo_int_int之类的名字。
 *  C++就是靠这种机制来实现函数重载的。
 *  所以，可以用一句话概括extern “C”这个声明的真实目的:解决名字匹配问题，实现C++与C的混合编程。
 *
 */
extern "C" void test_c() {
    cout << "test_c" << endl;
}

void test_pi() {
    cout << PI << endl;
    float a = PI * 1.0f;
    cout << a << endl;
}


/*void test_p() {
    char *p = "world";
    cout << *p << endl;
    p[1] = 'A';
    cout << *p << endl;
}*/

int main() {
    //test_p();
    //printSize();
    string path = "/Users/weitao/f/2021/c_practise/CLionProjects/test/lena_256x256_yuv420p.yuv";
    char *p = (char * )path.data();
    int res = simplest_yuv420_split(p,256,256,1);
    cout << "res = " << res << endl;
    return 0;
}





