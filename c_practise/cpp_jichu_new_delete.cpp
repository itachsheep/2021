#include<iostream>
using namespace std;


/* int main()
{
    double *pValue = NULL;
    cout << "p = " << pValue << endl;
    if(!pValue) 
    {
        cout << "p is null" << endl;
    }

    pValue = new double;
    //malloc() 函数在 C 语言中就出现了，在 C++ 中仍然存在，但建议尽量不要使用 malloc() 函数。new 与 malloc() 函数相比，其主要的优点是，new 不只是分配了内存，它还创建了对象。
    cout << "p = " << pValue << endl;

    delete pValue;

    return 0;
} */

int main()
{
    char ch[5] = {'c','d','r'};
    char* ptr = new char[20];
    delete [] ptr;


    //二维数组
    int** array;
    int m = 5,n = 10;
    // 假定数组第一维长度为 m， 第二维长度为 n
    // 动态分配空间
    array = new int*[m];
    for(int i = 0; i < m; i++)
    {
        array[i] = new int[n];
    }
    //释放
    for(int i = 0; i < m; i++)
    {
        delete [] array[i];
    }
    delete [] array;

    return 0;
}