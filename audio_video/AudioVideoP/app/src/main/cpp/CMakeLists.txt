# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.
project("audiovideop")

include_directories(
        ${CMAKE_SOURCE_DIR}/mp3_encoder/include
)

file(GLOB src_mp3_encoder
        ${CMAKE_SOURCE_DIR}/mp3_encoder/*.cpp
        mp3_encoder/AudioEncoder.cpp)

file(GLOB src_test
        ${CMAKE_SOURCE_DIR}/test/*.cpp)

add_library(mp3_encoder
            SHARED
            ${src_mp3_encoder})

add_library( # Sets the name of the library.
             main-test

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
            ${src_test})



find_library( # Sets the name of the path variable.
              log-lib
              log )

target_link_libraries( # Specifies the target library.
                        main-test
                       ${log-lib} )

target_link_libraries( # Specifies the target library.
        mp3_encoder
        ${log-lib} )