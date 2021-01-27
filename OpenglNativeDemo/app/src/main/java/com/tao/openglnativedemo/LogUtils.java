/**
 * @ClassName: LogUtils
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.openglnativedemo;

import android.util.Log;

public class LogUtils {
    private static String TAG = "OpenglNativeDemo.";
    public static void d(String tag,String msg) {
        Log.d(TAG + tag,msg);
    }
}
