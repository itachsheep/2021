#include<iostream>
#include<pthread.h>
using namespace std;


#define NUM_THREAD 5

void* sayHello(void* args)
{
    //int tid = *((int *) args);
    //int tid = 0;
    cout << "sayHello tid: " << endl;
    return 0;
};

void* printHello(void* mtid)
{
    int tid = *((int *)mtid);
    cout << "printHello tid = " << tid << endl;
    pthread_exit(NULL);

}

int main()
{
    /* pthread_t tids[NUM_THREAD];

    for (int i = 0; i < NUM_THREAD; i++)
    {
        int ret = pthread_create(&tids[i],NULL,sayHello,NULL);
        if(ret != 0)
        {
            cout << "ptread_create error: " << ret << endl;
        }
    }
    
    //在这里，pthread_exit 用于显式地退出一个线程。通常情况下，pthread_exit() 函数是在线程完成工作后无需继续存在时被调用。
    //如果 main() 是在它所创建的线程之前结束，并通过 pthread_exit() 退出，那么其他线程将继续执行。否则，它们将在 main() 结束时自动被终止。
    
    pthread_exit(NULL); */




    pthread_t tid[NUM_THREAD];
    int indexs[NUM_THREAD];
    int rc;

    for (int i = 0; i < NUM_THREAD; i++)
    {
        indexs[i] = i+1;
        rc = pthread_create(&tid[i],NULL,printHello,(void*)&indexs[i]);
        if(rc != 0)
        {
            cout << "pthread_create fail: " << rc << endl;
            exit(-1);
        }
    }
    pthread_exit(NULL);
}