/**
 * @ClassName: FFMediaPlayer
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mjnindk;

public class FFMediaPlayer {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("life-tip");
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native static String stringFromJNI();
    public native static String getFFmpegVersion();
}
