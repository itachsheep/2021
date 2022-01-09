//
// Created by wei tao on 2022/1/9.
//

#include "jichu_c_cpp.h"

int test_calloc_malloc() {
    //malloc调用形式为(类型*)malloc(size)：在内存的动态存储区中分配一块长度为“size”字节的连续区域，返回该区域的首地址。
    //calloc调用形式为(类型*)calloc(n，size)：在内存的动态存储区中分配n块长度为“size”字节的连续区域，返回首地址。
    //realloc调用形式为(类型*)realloc(ptr，size)：将ptr内存大小增大到size。

    //用malloc只分配空间不初始化，也就是依然保留着这段内存里的数据
    int *ip_a;
    ip_a = (int *) malloc(sizeof(int) * 5);
    ip_a[0] = 100;
    cout << "ip_a[0] = " << ip_a[0] << endl;

    //而calloc则进行了初始化，calloc分配的空间全部初始化为0，这样就避免了可能的一些数据错误
    int *ip_b;
    ip_b = (int *) calloc(5, sizeof(int));
    ip_b[0] = 300;
    printf("ip_b[0] = %d", ip_b[0]);

    free(ip_a);
    free(ip_b);
    return 0;
}

int test_sprintf() {
    char s[40];
    char *ss;
    sprintf(s,"%s%d%c","test",100,'$');
    printf("s = %s \n",s);

    sprintf(ss,"%s%d%c","niubi",666,'#');
    printf("ss = %s \n",ss);
    return 0;
}
