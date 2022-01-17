package com.tao.cpp_lrn

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Surface
import android.widget.Toast

object AudioPlay {

    const val tag = "AudioPlay"
    external fun testFriend()
    external fun printUser(user: User)

    external fun nativePlayPcm(pcmPath: String)
    external fun nativeStopPcm()

    external fun nativePlayVideo(videoPath: String, surface: Surface)
    external fun nativeOnDestroy();


    /**
     * JNI 调用
     *
     * @param message
     */
    fun showMessage(message: String) {
        LogUtils.d(tag, message)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                MyApp.getGlobalContext(),
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}