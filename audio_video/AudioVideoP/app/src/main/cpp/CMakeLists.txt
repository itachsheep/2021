# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.
project("audiovideop")

#set(lib1-mp3encoder mp3_encoder)
set(lib2-main-test main-test)
#set(lib3-ffmpeg-decoder ffmpeg_decoder)
set(jnilibs ${CMAKE_SOURCE_DIR}/../jniLibs)

include_directories(
        ${CMAKE_SOURCE_DIR}/3rdparty/include
        ${CMAKE_SOURCE_DIR}/utils
#        ${CMAKE_SOURCE_DIR}/mp3_encoder
        ${CMAKE_SOURCE_DIR}/ffmpeg_decoder
#        ${CMAKE_SOURCE_DIR}/test/simdjson
)

## 增加外部依赖链接库
link_directories(
#    ${jnilibs}/${CMAKE_ANDROID_ARCH_ABI}
    ${jnilibs}/armeabi-v7a
#    ${jnilibs}/arm64-v8a
)

set(3rdparty_mp3lame
        mp3lame)

#set(3rdparty_ffmpeg_decoder
#        avcodec
#        avfilter
#        avformat
#        avutil
#        fdk-aac
#        postproc
#        swresample
#        swscale
#        vo-aacenc
#        z
#        x264)


file(GLOB src_mp3_encoder
#        ${CMAKE_SOURCE_DIR}/mp3_encoder/*.cpp
        )

file(GLOB src_test
        ${CMAKE_SOURCE_DIR}/test/*.cpp
#        ${CMAKE_SOURCE_DIR}/simdjson/simdjson.cpp
#        ${CMAKE_SOURCE_DIR}/simdjson/testSimdJson.cpp
        )

#file(GLOB src_ffmpeg_decoder
#        ${CMAKE_SOURCE_DIR}/ffmpeg_decoder/*.cpp
#        )

#add_library(${lib1-mp3encoder}
#            SHARED
#            ${src_mp3_encoder})

add_library( # Sets the name of the library.
             ${lib2-main-test}

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
            ${src_test})

#add_library(${lib3-ffmpeg-decoder}
#        SHARED
#        ${src_ffmpeg_decoder})



set(log-lib
    android
    log)

target_link_libraries( # Specifies the target library.
        ${lib2-main-test}
        android
        log)

#target_link_libraries( # Specifies the target library.
#        ${lib1-mp3encoder}
#        ${3rdparty_mp3lame}
#        android
#        log)

#target_link_libraries(
#        ${lib3-ffmpeg-decoder}
##        ${3rdparty_ffmpeg_decoder}
#        android
#        log)