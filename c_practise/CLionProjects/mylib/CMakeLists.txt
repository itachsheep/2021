cmake_minimum_required(VERSION 3.21)
project(mylib)

set(CMAKE_CXX_STANDARD 11)

add_library(mylib
        library.cpp library.h
        senior_play/play_video.cpp senior_play/play_video.h)
