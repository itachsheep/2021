#include<iostream>
using namespace std;

int main()
{
    int m = 5, n = 10;
    int** p = new int *[m]; 
    for(int i = 0 ; i < m; i++) 
    {
        p[i] = new int[n];
        for(int j = 0; j < n; j++)
        {
            p[i][j] = (i + 1) * (j + 1) + j + 1;
        }
    }  

    for(int i = 0; i < m ; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cout << " " << p[i][j];  
        }
        cout << endl;
    }
    
    for(int i = 0; i < m; i++)
    {
        delete [] p[i];
    }
    delete [] p;
    
    return 0;
}