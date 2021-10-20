package com.tao.lrnpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.tao.lrnpro.test.LiveDataTestActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        findViewById<TextView>(R.id.sample_text).text = stringFromJNI()
    }

    fun goto_live_data_test( view: View) {
        startActivity(Intent(baseContext,LiveDataTestActivity::class.java))
    }

    fun switch_thread(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            LogUtils.d("start thread id: ${Thread.currentThread().id} }")
            async {
               LogUtils.d("async thread id: ${Thread.currentThread().id}")
            }

            GlobalScope.launch(Dispatchers.Main) {
                LogUtils.d("switch to main, id: ${Thread.currentThread().id}")
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}