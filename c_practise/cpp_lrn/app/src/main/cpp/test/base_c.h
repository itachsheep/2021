//
// Created by wei tao on 2022/1/13.
//

#ifndef CPP_LRN_BASE_C_H
#define CPP_LRN_BASE_C_H
#include "my_log.h"
class Box {
private:
    int width;
public:
    void setWidth(int w);

    friend void printWith(Box box) {
        LogD("printWidth box width = %d ",box.width);
    }
};
#endif //CPP_LRN_BASE_C_H
