//
// Created by wei tao on 2021/9/9.
//
#include <jni.h>
#include <string>
#include "simdjson.h"
#include "LogUtils.h"
using namespace simdjson;

extern "C" JNIEXPORT void JNICALL
Java_com_tao_practice_SimdJson_testSimdJson(JNIEnv *env, jobject thiz,jstring path) {
    const char * jsonPath = env->GetStringUTFChars(path,NULL);
    LOGCATD("testSimdJson beging: %s ", jsonPath);
    ondemand::parser parser;
//    padded_string json = padded_string::load("twitter.json");
    padded_string json = padded_string::load(jsonPath);
    ondemand::document tweets = parser.iterate(json);
    //std::cout << uint64_t(tweets["search_metadata"]["count"]) << " results." << std::endl;
    LOGCATD("testSimdJson: %d  results",uint64_t(tweets["search_metadata"]["count"]));
}




