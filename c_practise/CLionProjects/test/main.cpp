#include <iostream>
#include "common.h"

#include "hello.c"
#include "simplest_rgb_to_bmp.c"
#include "simplest_rgb_to_yuv.h"
#include "pcm/simplest_pcm_split.h"
#include "jichu/jichu_c_cpp.h"
#include "h264/simplest_h264_parser.h"
#include "aac/simplest_aac_parser.h"
#include "flv/simplest_flv_parser.h"

using namespace std;

extern int globe;

extern void func();

extern string CUR_RES_DIR;
extern string TARGET_RES_DIR;

extern int simplest_yuv420_split(char *url, int w, int h, int num);

extern int simplest_yuv420_gray(char *url, int w, int h, int num);

extern void copyFile(char *url);

extern void simplest_yuv_420_border(char *url, int w, int h, int border, int num);

extern int simplest_rgb24_split(char *url, int w, int h, int num);

void test() {
    std::cout << "Hello, World!" << std::endl;
    std::cout << "Hello, World11111!" << std::endl;
    std::cout << "Hello, World1122!" << std::endl;

    cout << "短斤少两发的快递"
            "拉德斯基发了多少积分"
            "看极乐世界地方" << endl;
    cout << globe << endl;

    func();
    func();
}

/**
 *  extern "C"是连接申明(linkagedeclaration),被extern"C"修饰的变量和函数是按照C语言方式编译和连接的。
 *  作为一种面向对象的语言，C++支持函数重载，而过程式语言C则不支持。
 *  函数被C++编译后在符号库中的名字与C语言的不同。
 *  例如，假设某个函数的原型为：
 *      void foo( int x, int y);
 *  该函数被C编译器编译后在符号库中的名字为_foo，而C++编译器则会产生像_foo_int_int之类的名字。
 *  C++就是靠这种机制来实现函数重载的。
 *  所以，可以用一句话概括extern “C”这个声明的真实目的:解决名字匹配问题，实现C++与C的混合编程。
 *
 */
extern "C" void test_c() {
    cout << "test_c" << endl;
}

void test_pi() {
    cout << PI << endl;
    float a = PI * 1.0f;
    cout << a << endl;
}

void test_simplest_yuv420_split() {
    string path = "/Users/weitao/f/2021/c_practise/CLionProjects/test/lena_256x256_yuv420p.yuv";
    char *p = (char *) path.data();
    int res = simplest_yuv420_split(p, 256, 256, 1);
    cout << "res = " << res << endl;
}

void test_simplest_yuv420_gray() {
    char *source = (char *) (CUR_RES_DIR + "lena_256x256_yuv420p.yuv").c_str();
    simplest_yuv420_gray(source, 256, 256, 1);
}

void test_copyFile() {
    char *source_yuv = (char *) (CUR_RES_DIR + "lena_256x256_yuv420p.yuv").c_str();
    copyFile(source_yuv);
}

void test_simplest_yuv_420_border() {
    char *source_yuv = (char *) (CUR_RES_DIR + "lena_256x256_yuv420p.yuv").c_str();
    simplest_yuv_420_border(source_yuv, 256, 256, 20, 1);
}

void test_simplest_rgb24_split() {
    char *source_yuv = (char *) (CUR_RES_DIR + "cie1931_500x500.rgb").c_str();
    simplest_rgb24_split(source_yuv, 500, 500, 1);
}

void test_simplest_rgb24_to_bmp() {
    char *source = (char *) (CUR_RES_DIR + "lena_256x256_rgb24.rgb").c_str();
//    char *out = (char *)(TARGET_RES_DIR + "output_lena.bmp").c_str();
    simplest_rgb24_to_bmp(source, 256, 256);
}

void test_pass_param() {
    string s1 = CUR_RES_DIR + "111";
    string s2 = CUR_RES_DIR + "222";
    string s3 = CUR_RES_DIR + "333";
    char *str1 = (char *) s1.c_str();
    char *str2 = (char *) s2.c_str();
    char *str3 = (char *) s3.c_str();

//    test_pass_param("111","222","333");
    test_pass_param(str1, str2, str3);
}

void test_simplest_rgb24_to_yuv420() {
    string source = CUR_RES_DIR + "lena_256x256_rgb24.rgb";
    string out = TARGET_RES_DIR + "output_256x256_yuv420p.yuv";
    char *in = (char *) source.c_str();
    char *output = (char *) out.c_str();
    simplest_rgb24_to_yuv420(in, 256, 256, 1, output);
}

void test_simplest_pcm16le_split() {
    string source = CUR_RES_DIR + "NocturneNo2inEflat_44.1k_s16le.pcm";
    string left = TARGET_RES_DIR + "output_l.pcm";
    string right = TARGET_RES_DIR + "output_r.pcm";
    simplest_pcm16le_split((char *) source.c_str(), (char *) left.c_str(), (char *) right.c_str());
}

void test_simplest_pcm16le_doublespeed() {
    string source = CUR_RES_DIR + "NocturneNo2inEflat_44.1k_s16le.pcm";
    string out = TARGET_RES_DIR + "output_double_speed.pcm";
    simplest_pcm16le_doublespeed((char *) source.c_str(), (char *) out.c_str());
}

void test_simplest_pcm16le_cut_single_channel() {
    string source = CUR_RES_DIR + "NocturneNo2inEflat_44.1k_s16le.pcm";
    string out = TARGET_RES_DIR + "output_cut_single.pcm";
    string out_stat_txt = TARGET_RES_DIR + "output_stat.txt";
    simplest_pcm16le_cut_single_channel((char *) source.c_str(),
                                        (char *) out.c_str(),
                                        (char *) out_stat_txt.c_str(), 0, 10240);
}

void test_simplest_h264_parser() {
    string source = CUR_RES_DIR + "sintel.h264";
    simplest_h264_parser((char *) source.c_str());
}

void test_test_pointer_param() {
    int num = 1;
    int *a = &num;
    cout << "a = " << *a << endl;
    test_pointer_param(a);
    cout << "a = " << *a << endl;
}

void test_simplest_aac_parse() {
    string source = CUR_RES_DIR + "nocturne.aac";
    simplest_aac_parse((char *) source.c_str());
}

void test_simplest_flv_parser() {
    string source = CUR_RES_DIR + "cuc_ieschool.flv";
    string out_audio = TARGET_RES_DIR + "output_audio.mp3";
    string out_video = TARGET_RES_DIR + "output_video.flv";
    simplest_flv_parser((char *) source.c_str(), (char *) out_audio.c_str(),
                        (char *) out_video.c_str());
}

int main() {
    test_simplest_flv_parser();
    return 0;
}





