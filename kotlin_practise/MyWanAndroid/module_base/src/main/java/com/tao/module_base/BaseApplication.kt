package com.tao.module_base

import android.app.Application
import com.hao.library.HaoLibraryConfig
import com.hao.library.HttpConfig
import com.hao.library.extensions.notNullSingleValue
import com.hao.library.http.HttpResponseModel
import com.tao.module_base.constant.MyConstant

open class BaseApplication: Application() {
    companion object {
        var instance by notNullSingleValue<BaseApplication>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        HaoLibraryConfig.Builder(this)
                .setHttpConfig(MyHttpConfig())
                .apply()
    }

    class MyHttpConfig : HttpConfig {
        override fun getBaseUrl(): String = MyConstant.BASE_URL

        override fun <T : HttpResponseModel<*>> handleResponse(t: T): Boolean {
           return when(t.getCode()) {
               "-1001" -> {
                   Router.startLogin()
                   true
               }
               else -> false
           }
        }

        override fun isLogin(): Boolean = Config.isLogin

        override fun login() {
            Router.startLogin()
        }

    }
}