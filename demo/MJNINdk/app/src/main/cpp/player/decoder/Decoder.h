//
// Created by wei tao on 2021/4/26.
//

#ifndef MJNINDK_DECODER_H
#define MJNINDK_DECODER_H

/**
 *  typedef故名思意就是类型定义的意思，但是它并不是定义一个新的类型而是给已有的类型起一个别名，
 * 在这一点上与引用的含义类似，引用是变量或对象的别名，而typedef定义的是类型的别名。
 * typedef的作用主要有两点：
 * 1.1  简化复杂的类型声明
        简化复杂的类型声明，或给已有类型起一含义明确的别名；如：
        typedef bool (*FuncPointer)(int, double);
         //声明了一个返回 bool 类型并带有两个(int和double)形参的函数的指针类型FuncPointer
        FuncPointer pFunc; //声明了一个FuncPointer类型的函数指针对象pFunc
 *
 *1.2  定义与平台无关的类型
        定义与平台无关的类型，屏蔽不同平台的类型差异化；如：
        用typedef来定义与平台无关的类型。
        比如定义一个叫 REAL 的浮点类型，在目标平台一上，让它表示最高精度的类型为：
        typedef long double REAL;
        在不支持 long double 的平台二上，改为：
        typedef double REAL;
        在连 double 都不支持的平台三上，改为：
        typedef float REAL;
 *
 *
 * #define则是宏定义，发生在预处理阶段，也就是编译之前，
 * 它只进行简单而机械的字符串替换，而不进行任何检查。
 *
 * typedef用来定义类型的别名，定义与平台无关的数据类型，与struct的结合使用等。
    #define不只是可以为类型取别名，还可以定义常量、变量、编译开关等。

    #define没有作用域的限制，只要是之前预定义过的宏，在以后的程序中都可以使用。
    而typedef有自己的作用域。
 */
typedef void (*MessageCallback)(void*, int, float);//简化复杂的类型声明，定义函数指针
typedef long (*AVSyncCallback)(void*) ;


class Decoder {
    virtual void Start() = 0;
    virtual void Pause() = 0;
    virtual void Stop() = 0;
    virtual float GetDuration() = 0;
    virtual void SeekToPosition(float position) = 0;
    virtual float GetCurrentPosition() = 0;
    virtual void SetMessageCallback(void* context,MessageCallback callback) = 0;
};
#endif //MJNINDK_DECODER_H
