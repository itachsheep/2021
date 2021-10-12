package com.tao.wan

import com.hao.library.utils.AppUtils
import com.tao.module_base.BaseApplication
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta

class App: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if(AppUtils.isMainProcess(instance, android.os.Process.myPid())) {

            initBugly()
        }
    }

    private fun initBugly() {
        Beta.autoInit = false
        Bugly.init(this,"50bf0502bf", true)
    }
}