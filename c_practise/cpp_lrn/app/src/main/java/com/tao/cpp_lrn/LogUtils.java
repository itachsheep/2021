/**
 * @ClassName: LogUtils
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.cpp_lrn;

import android.util.Log;

public class LogUtils {
    private static final String TAG = "MyAV.";

    public static void d(String tag, String msg) {
        Log.d(TAG + tag,msg);
    }
}
