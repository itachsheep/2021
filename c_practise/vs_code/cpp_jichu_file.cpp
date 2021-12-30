#include<iostream>
#include<fstream>
//该数据类型通常表示文件流，且同时具有 ofstream 和 ifstream 两种功能，
//这意味着它可以创建文件，向文件写入信息，从文件读取信息。

using namespace std;

int main()
{
    char data[100];
    ofstream outfile;
    //ios::out 打开文件用于写入。 ios::trunc 如果该文件已经存在，其内容将在打开文件之前被截断，即把文件长度设为 0。
    outfile.open("out_file.txt",ios::out | ios::trunc);

    cout << "输入你要写到文件的内容：" << endl;
    cin.getline(data,100);
    outfile << data << endl;//输入到文件


    cout << "继续，输入你要写到文件的内容：" << endl;
    cin >> data;
    cin.ignore();
    outfile << data << endl;//输入到文件

    //关闭文件
    outfile.close();


    //读文件
    ifstream infile;
    infile.open("test.txt",ios::in);
    cout << "Read file test.txt : " << endl;
    
    infile.getline(data,100);
    cout << data << endl;
    
    infile.getline(data,100);
    cout << data << endl;
    
    infile.getline(data,100);
    cout << data << endl;

    infile.getline(data,100);
    cout << data << endl;
    infile.close();

    return 0;
}