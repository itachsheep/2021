/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.ffmpegDecoder;

public class FFmpegDecoder {
    public native int init(String mp3FilePath, String pcmFilePath);

    public native void decode();

    public native void destroy();
}
