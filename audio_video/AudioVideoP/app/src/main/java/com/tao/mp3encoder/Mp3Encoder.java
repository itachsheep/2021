/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mp3encoder;

public class Mp3Encoder {
    public native void encode();
    public native void destroy();
    public native int init(String pcmPath, String mp3Path, int channels,int bitRate,int sampleRate);
}
