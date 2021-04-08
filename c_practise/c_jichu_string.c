#include<stdio.h>
#include<string.h>
struct Node
{
    char name[100];
    int value;
    //int time;
    //struct Node *next;
} head = {"c program", 10001};


int main()
{   
    // char site[6] = {'R', 'U', 'N', 'O', 'O', 'B'};
    // char site2[7] = {'R', 'U', 'N', 'O', 'O', 'B','\0'};
    // printf("site = %s \n",site);
    // printf("site2 = %s \n",site2);

    struct Node list[10];
    struct Node p;
    struct Node ttNode;

    struct Node *nodePtr = &ttNode;

    strcpy(p.name,"c++ program");
    p.value = 10002;

    strcpy(nodePtr->name,"node program");
    nodePtr->value = 10003;

    printf("node name: %s, value: %d \n",head.name,head.value);
    printf("node p: %s, value: %d \n",p.name,p.value);
    printf("node nodePtr: %s, value: %d \n",nodePtr->name,nodePtr->value);

    return 0;
}