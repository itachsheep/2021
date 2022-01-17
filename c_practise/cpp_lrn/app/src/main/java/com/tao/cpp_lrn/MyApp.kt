/**
 * @ClassName:      MyApp.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2022/1/17 11:08 PM
 */
package com.tao.cpp_lrn

import android.app.Application
import android.content.Context

class MyApp :Application(){
    val tag = "MyApp"
    companion object {
        lateinit var globalCtx: Context

        fun getGlobalContext(): Context {
            return globalCtx
        }
    }

    override fun onCreate() {
        LogUtils.d(tag,"onCreate")
        super.onCreate()
        globalCtx = this
    }
}