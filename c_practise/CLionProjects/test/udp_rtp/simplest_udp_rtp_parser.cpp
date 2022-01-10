//
// Created by wei tao on 2022/1/10.
//

#include "simplest_udp_rtp_parser.h"

/**
 * MPEG-TS封装格式数据打包为RTP/UDP协议然后发送出去的流程如下图所示。
 * 图中首先每7个MPEG-TS Packet打包为一个RTP，然后每个RTP再打包为一个UDP。
 * 其中打包RTP的方法就是在MPEG-TS数据前面加上RTP Header，
 * 而打包RTP的方法就是在RTP数据前面加上UDP Header。
 *
 * 
 *
*/