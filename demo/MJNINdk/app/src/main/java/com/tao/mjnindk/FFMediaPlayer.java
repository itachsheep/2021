/**
 * @ClassName: FFMediaPlayer
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mjnindk;

import android.view.Surface;

public class FFMediaPlayer {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("life-tip");
    }

    private EventCallback eventCallback;
    private Long nativePlayerHandler = 0L;


    public void addEventCallback(EventCallback callback) {
        this.eventCallback = callback;
    }

    public void init(String url, int videoRenderType, Surface surface) {
        nativePlayerHandler = native_Init(url,videoRenderType,surface);
    }

    public interface EventCallback {
        void onPlayerEvent(int msgType, float msgValue);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native static String stringFromJNI();
    public native static String getFFmpegVersion();
    /**
     * player init , play ,stop ... control
     */
    private native long native_Init(String url,int renderType,Object surface);
}
