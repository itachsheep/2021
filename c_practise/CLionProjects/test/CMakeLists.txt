cmake_minimum_required(VERSION 3.21)
project(test)

set(CMAKE_CXX_STANDARD 11)
# 指定lib目录
link_directories(${PROJECT_SOURCE_DIR}/lib)
# 指定头文件搜索路径
include_directories(${PROJECT_SOURCE_DIR}/include)

#add_executable(test main.cpp global.cpp common.h hello.c simplest_mediadata_raw.cpp simplest_rgb_to_bmp.c simplest_rgb_to_bmp.h simplest_rgb_to_yuv.cpp simplest_rgb_to_yuv.h pcm/simplest_pcm_split.cpp pcm/simplest_pcm_split.h h264/simplest_h264_parser.cpp h264/simplest_h264_parser.h jichu/jichu_c_cpp.cpp jichu/jichu_c_cpp.h aac/simplest_aac_parser.cpp aac/simplest_aac_parser.h)
add_executable(test
        main.cpp
        global.cpp common.h
        hello.c
        simplest_mediadata_raw.cpp
        simplest_rgb_to_bmp.c simplest_rgb_to_bmp.h
        simplest_rgb_to_yuv.cpp simplest_rgb_to_yuv.h
        pcm/simplest_pcm_split.cpp pcm/simplest_pcm_split.h
        h264/simplest_h264_parser.cpp h264/simplest_h264_parser.h
        jichu/jichu_c_cpp.cpp jichu/jichu_c_cpp.h
        aac/simplest_aac_parser.cpp aac/simplest_aac_parser.h
        flv/simplest_flv_parser.cpp flv/simplest_flv_parser.h
        udp_rtp/simplest_udp_rtp_parser.cpp udp_rtp/simplest_udp_rtp_parser.h
        )
#将库链接到项目中
target_link_libraries(${PROJECT_NAME} libmylib.a)