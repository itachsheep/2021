#include<iostream>
using namespace std;

class Shape {
   protected:
      int width, height;
   public:
      Shape( int a=0, int b=0)
      {
         width = a;
         height = b;
      }
      virtual int getArea() = 0;
};

class Rect: public Shape
{
    public:
        Rect( int a=0, int b=0)
        {
            width = a;
            height = b;
        }
        int getArea()
        {
            return width * height;
        }
};

int main()
{
    //抽象函数：
    //C++ 接口是使用抽象类来实现的，抽象类与数据抽象互不混淆，数据抽象是一个把实现细节与相关的数据分离开的概念。
    //如果类中至少有一个函数被声明为纯虚函数，则这个类就是抽象类。纯虚函数是通过在声明中使用 "= 0" 来指定的，如下所示：virtual double getVolume() = 0;

    Rect rect(5,10);
    cout << "rect area: " << rect.getArea() << endl;

}