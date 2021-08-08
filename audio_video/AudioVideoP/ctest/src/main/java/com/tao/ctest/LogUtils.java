/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.ctest;

import android.util.Log;

public class LogUtils {
    private static final String TAG = "CTest.";
    public static void d(String tag,String msg) {
        Log.d(TAG + tag,msg);
    }
}
