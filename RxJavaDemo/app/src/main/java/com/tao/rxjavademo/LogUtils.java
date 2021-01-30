/**
 * @ClassName: LogUtils
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.rxjavademo;

import android.util.Log;

public class LogUtils {
    private static String TAG = "RxJavaDemo.";
    public static void d(String tag,String msg) {
        Log.d(TAG + tag,msg);
    }
}
