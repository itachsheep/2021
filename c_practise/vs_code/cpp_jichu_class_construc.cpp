#include<iostream>
using namespace std;

class Line
{
    public:
        static int count;
        int width;
        Line();
        Line(int widt);
        ~Line();
        void setCount(int a) 
        {
            count = 1;
        }
        int compare(Line line);
    private:
         
    
};

Line::Line() 
{
    count++;
    cout << "Line constructor: "<< endl;
}

Line::Line(int wid)
{
    count++;
    width = wid;
    cout << "Line constructor: " << width << endl;
}

Line::~Line()
{
    cout << "~Line " << endl;
}

int Line::compare(Line line)
{
    return this->width > line.width;
}

int Line::count = 0;

int main()
{
    Line line1;
    Line line2(200);

    cout << "---  just do ----" << endl;

    Line line3(300);
    cout << "line2 - line3: " << line2.compare(line3) << endl;
    cout << "line3 - line2: " << line3.compare(line2) << endl;

    Line *ptr;
    ptr = &line2;
    cout << "ptr: " << ptr->width << endl;

    cout << "count: " << Line::count << endl;

    return 0;
}