/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.utils;

import android.util.Log;

public class LogUtils {
    static final String TAG = "AudioVideoP.";
    public static void d(String tag,String msg) {
        Log.d(TAG + tag, msg);
    }

    public static void e(String tag,String msg) {
        Log.e(TAG + tag, msg);
    }

    public static void w(String tag,String msg) {
        Log.w(TAG + tag, msg);
    }

    public static void i(String tag,String msg) {
        Log.i(TAG + tag, msg);
    }
}
