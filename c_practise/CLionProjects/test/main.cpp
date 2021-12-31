#include <iostream>
using namespace std;


extern int globe;
extern void func();

int main() {
    std::cout << "Hello, World!" << std::endl;
    std::cout << "Hello, World11111!" << std::endl;
    std::cout << "Hello, World1122!" << std::endl;

    cout << "短斤少两发的快递"
            "拉德斯基发了多少积分"
            "看极乐世界地方" <<endl;
    cout << globe <<endl;

    func();
    func();

    return 0;
}


