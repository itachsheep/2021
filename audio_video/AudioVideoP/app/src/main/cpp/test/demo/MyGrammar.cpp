//
// Created by wei tao on 2021/9/11.
//
#include "MyGrammar.h"
#include "LogUtils.h"
TestNewMalloc::TestNewMalloc() {
    LOGCATD("TestNewMalloc called");
}

TestNewMalloc::~TestNewMalloc() {
    LOGCATD("~TestNewMalloc destroy called");
}

void TestNewMalloc::setValue(int val) {
    value = val;
}

void TestNewMalloc::printValue() {
    LOGCATD("printValue: %d", value);
}