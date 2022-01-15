package com.tao.cpp_lrn

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val REQUEST_OK = 10001
    private val tag = "MainActivity"
    private val request_permission = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    fun checkPermission(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission!!)
                != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        LogUtils.d(tag, "checkPermission ok")
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        LogUtils.d(
            tag,
            "onRequestPermissionsResult: $requestCode"
        )
        if (requestCode != REQUEST_OK) {
            Toast.makeText(this, "权限申请失败", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermission(request_permission)) {
            ActivityCompat.requestPermissions(
                this,
                request_permission,
                REQUEST_OK
            )
        }
        // Example of a call to a native method
        findViewById<TextView>(R.id.sample_text).text = stringFromJNI()



    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("audio_video")
        }
    }

    fun bt_test_friend(view: View) {
        AudioPlay.testFriend()
    }

    fun bt_test_playPcm(view: View) {
        /*val path:String  = "file:///android_asset/test.pcm"
        val file = File(path)
        if(file.exists()) {
            LogUtils.d(tag,"bt_test_playPcm file ok ")
        } else {
            LogUtils.d(tag,"bt_test_playPcm no exist")
        }*/


       // AudioPlay.nativePlayPcm("")
    }



    fun bt_test_stopPcm(view: View) {
        AudioPlay.nativeStopPcm()
    }
}