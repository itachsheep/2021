package com.tao.gradlebuilddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val yunListener = object :YunListener{
            override fun onData() {

            }
        }

        setYunListener(object :YunListener{
            override fun onData() {
                Log.d(tag,"onData...")
            }
        })

        setYunListener(listener = object :YunListener{
            override fun onData() {

            }
        })
    }

    private fun setYunListener(listener: YunListener) {
        listener.onData()
    }
}