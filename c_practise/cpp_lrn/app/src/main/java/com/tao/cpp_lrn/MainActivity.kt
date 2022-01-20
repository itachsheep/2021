package com.tao.cpp_lrn

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.pm.ConfigurationInfo
import android.content.pm.PackageManager
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Environment
import android.view.Surface
import android.view.SurfaceView
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File

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
        //AudioPlay.printUser(User("taowei","123"))

    }


    fun bt_test_playPcm(view: View) {
        //val path = Environment.getExternalStorageDirectory().absolutePath + "/111/test.pcm"
        val path = filesDir.absolutePath + "/test.pcm"

        LogUtils.d(tag, "pcm file path  = " + path)
        val file = File(path)
        if (!file.exists()) {
            Toast.makeText(this, "pcm文件不存在", Toast.LENGTH_SHORT).show()
            return
        } else {
            LogUtils.d(tag, "pcm file exist: " + file.absolutePath)
        }
        AudioPlay.nativePlayPcm(path)
    }


    fun bt_test_stopPcm(view: View) {
        AudioPlay.nativeStopPcm()
    }

    fun bt_test_playYUV(view: View) {
        val surfaceView: SurfaceView = findViewById(R.id.surface)
        val videoPath = filesDir.absolutePath + "/yuvtest.yuv"
        val thread = Thread {
            AudioPlay.nativePlayVideo(videoPath, surfaceView.holder.surface)
        }
        thread.start()
    }

    fun bt_test_draw_triangle(view: View) {
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        frameLayout.removeAllViews()
        val surfaceView = GLSurfaceView(this)
        if(detectOpenGLES30()) {
            surfaceView.setEGLContextClientVersion(3)
        } else {
            surfaceView.setEGLContextClientVersion(2)
        }
        surfaceView.setRenderer(TriangleRender())
        surfaceView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
        )
        frameLayout.addView(surfaceView,layoutParams)
    }
    private fun detectOpenGLES30(): Boolean {
        val am: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
        val info: ConfigurationInfo = am.deviceConfigurationInfo;

        LogUtils.d(tag,"detectOpenGLES30 reqGlEsVersion = " + info.reqGlEsVersion.toString(16) )
        return (info.reqGlEsVersion >= 0x30000);
    }
}