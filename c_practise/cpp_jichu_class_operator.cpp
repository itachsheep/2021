#include<iostream>
using namespace std;


class Rect 
{
    public:
        Rect();
        Rect(int w, int h);
        void setWidth(int w)
        {
            width = w;
        };

        void setHeight(int h)
        {
            height = h;
        };

        int getArea()
        {
            return width * height;
        }

        Rect operator + (const Rect& rect);

    protected:
        int width;
        int height;
};

Rect::Rect() 
{
    cout << "Rect Constructor !!" << endl;
};

Rect::Rect(int w, int h) 
{
    cout << "Rect Constructor with param!!" << endl;
    this->width = w;
    this->height = h;
};

Rect Rect::operator+(const Rect& rect)
{
    Rect dst;
    dst.width = this->width + rect.width;
    dst.height = this->height + rect.height;
    return dst;
};

int main()
{
    Rect rect1 = Rect(10,20);
    Rect rect2 = Rect(100,200);

    cout << "rect1 area: " << rect1.getArea() << endl;
    cout << "rect2 area: " << rect2.getArea() << endl;

    Rect rect3;
    rect3 = rect1 + rect2;
    cout << "rect3 area: " << rect3.getArea() << endl;
}