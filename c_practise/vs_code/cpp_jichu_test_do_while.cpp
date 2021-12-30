#include<iostream>
using namespace std;

int main()
{
    int i = 0;
    do
    {
        i++;
        if(i == 1) {
            cout << "i = 1, break" << endl;
            break;
        }
        cout << "i = " << i << " ------ "<< endl;
    } while (false);
    

}