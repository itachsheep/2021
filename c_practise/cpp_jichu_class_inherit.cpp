#include<iostream>
using namespace std;

class Shape 
{
    public:
        void setWidth(int w)
        {
            width = w;
        }

        void setHeight(int h)
        {
            height = h;
        }

    protected:
        int width;
        int height;
};

class PainCost
{
    public:
        int getCost(int area)
        {
            return area * 70;
        }
};

class Rectangle: public Shape, public PainCost
{
    public:
        int getArea()
        {
            return width * height;
        }
};

int main()
{

    //1,一个派生类继承了所有的基类方法，但下列情况除外：
    // (1)基类的构造函数、析构函数和拷贝构造函数。
    // (2)基类的重载运算符。
    // (3)基类的友元函数。


    //多继承
    Rectangle rect;
    rect.setWidth(5);
    rect.setHeight(10);

    cout << "rect area: " << rect.getArea() << endl;
    cout << "rect cost: " << rect.getCost(rect.getArea()) << endl;



}