#include<iostream>
#include<ctime>
using namespace std;

int main()
{
    //日期时间
    /* time_t now = time(0);
    cout << "1970 to currnt secs: " << now << endl;
    tm *ltm = localtime(&now);
    cout << 1900 + ltm->tm_year << "/";
    cout << 1 + ltm->tm_mon << "/";
    cout << ltm->tm_mday << " ";
    cout << ltm->tm_hour << ":";
    cout << ltm->tm_min << ":";
    cout << ltm->tm_sec << endl; */

   //标准输入输出
   char name[50];
   cout << "enter your name: " << endl;
   cin >> name;
   cout << "your name is: " << name << endl;

}
