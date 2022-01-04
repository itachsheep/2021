cmake_minimum_required(VERSION 3.21)
project(test)

set(CMAKE_CXX_STANDARD 11)

add_executable(test main.cpp global.cpp common.h hello.c simplest_mediadata_raw.cpp)
