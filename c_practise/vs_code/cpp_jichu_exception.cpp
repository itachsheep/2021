#include<iostream>
using namespace std;


double division(int a, int b)
{
    if(b == 0)
    {
        throw "division by zero exception";
    }
    return a / b;
}

/* 到底默认是public继承还是private继承，取决于子类而不是基类。
我的意思是，struct可以继承class，同样class也可以继承struct，那么默认的继承访问权限是看子类到底是用的struct还是class。如下：
struct A{}；
class B : A{}; //private继承
struct C : B{}；  //public继承 */
struct MyException: public exception
{
    const char *what() const throw()
    {
        return "myException c++";
    }
};


int main()
{
    double res = 0;
    try
    {
        res = division(20,0);
    }
    catch(const char* msg)
    {
        cout << "exception: " << msg << endl;
    }
    
    // cout << " a / b = " << division(20,0) << endl;

    try
    {
        throw MyException();
    }
    catch(const std::exception& e)
    {
        cout << " -- " << e.what() << endl;
    }
    
}