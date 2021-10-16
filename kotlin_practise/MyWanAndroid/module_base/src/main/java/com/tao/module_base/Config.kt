package com.tao.module_base

import com.hao.library.http.HttpManager
import com.tao.module_base.user.User
import com.tao.module_base.user.UserDb

object Config {
    private const val KEY_USERNAME = "loginUserName"
    private const val KEY_TOKEN = "token_pass"

    var user: User? = null
    val username: String?
        get() {
            return user?.userName
        }

    var isLogin: Boolean = false
        get() {
            return field && null != username
        }

    fun init() {
        var username: String ?= null
        var token: String ?=  null

        HttpManager.COOKIE_CACHE.forEach {
            if (it.name == KEY_USERNAME) {
                val value = it.value
                if ("\"\"" != value) {
                    username = value
                }

            } else if (it.name == KEY_TOKEN) {
                val value = it.value
                if ("\"\"" != value) {
                    token = value
                }
            }
        }

        if (null != username && null != token) {
            val user = UserDb.instance().userDao().query(username!!)
            if(user != null) {
                this.user = user
                isLogin = true
            }
        }
    }
}