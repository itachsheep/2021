package com.tao.module_base

import android.app.Application
import com.hao.library.extensions.notNullSingleValue

open class BaseApplication: Application() {
    companion object {
        var instance by notNullSingleValue<BaseApplication>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}