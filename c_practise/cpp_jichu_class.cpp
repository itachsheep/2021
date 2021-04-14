#include<iostream>
using namespace std;


class Box 
{
    //私有成员变量或函数在类的外部是不可访问的，甚至是不可查看的。只有类和友元函数可以访问私有成员。
    //默认情况下，类的所有成员都是私有的。
    private:
        string name;
    public:
        double length;
        double width;

        double get(void);
        void set(double len, double wid);

        double getLength(void) {
            return length;
        }

        string getName();
        void setName(string nam);

   protected:
        string title;

};

class SmallBox:Box 
{
    public:
        void setTitle(string titl);
        string getTitle();
};

double Box::get(void)
{
    return length * width;
}

void Box::set(double len1,double wid1)
{
    length = len1;
    width = wid1;
}

string Box::getName()
{
    return name;
}

void Box::setName(string nam)
{
    name = nam;
}

void SmallBox::setTitle(string titl)
{
    title = titl;
}

string SmallBox::getTitle(void)
{
    return title;
}

int main()
{
    Box box1, box2;
    box1.length = 5.0;
    box1.width = 6.0;
    box1.setName("green box");

    box2.length = 10.0;
    box2.width = 12.0;
    box2.setName("red box");

    cout << "box1 = " << box1.getName() << ": " << box1.get() << endl;
    cout << "box2 = " << box2.getName() << ": " << box2.get() << endl;

    box1.set(5.5,6.5);
    cout << "box1 = " << box1.get() << endl;

    SmallBox smallbox;
    smallbox.setTitle("title 1");
    cout << "smallbox = " << smallbox.getTitle() << endl;

}