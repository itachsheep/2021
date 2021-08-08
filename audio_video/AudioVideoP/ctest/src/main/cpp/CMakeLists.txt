# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.
project("ctest")


include_directories(
        ${CMAKE_SOURCE_DIR}/test
#        ${CMAKE_SOURCE_DIR}/mp3_encoder
)

file(GLOB src_test
        ${CMAKE_SOURCE_DIR}/test/*.cpp
        test/Test.cpp
        )


add_library(ctest
            SHARED
            ${src_test})

set(log-lib
    android
    log)

target_link_libraries(
        ctest
        android
        log)
