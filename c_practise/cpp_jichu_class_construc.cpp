#include<iostream>
using namespace std;

class Line
{
    public:
        int width;
        Line();
        Line(int widt);
        ~Line();
        void setCount(int a) 
        {
            count = 1;
        }
    private:
         static int count ;
    
};

Line::Line() 
{
    cout << "Line constructor: "<< endl;
}

Line::Line(int wid)
{
    width = wid;
    cout << "Line constructor: " << width << endl;
}

Line::~Line()
{
    cout << "~Line " << endl;
}

int main()
{
    Line line1;
    Line line2(200);

    cout << "---  just do ----" << endl;
    return 0;
}