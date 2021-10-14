/**
 * @ClassName:     ApiTestActivity
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/13 8:50 AM
 */
package com.tao.wan

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hao.library.utils.CoroutineUtils
import com.tao.module_base.LogUtils
import com.tao.module_base.user.User
import com.tao.module_base.user.UserDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ApiTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.api_test_activity)
    }

    fun on_insert(view: View) {
        LogUtils.d("on_insert")
        val user = User(101,
                "taowei",
                "token-101",
                "icon-101",
                "itachsheep@163.com",
                "extension-101")

        runOnSingleThread {
            UserDb.instance().userDao().insert(user)
        }

    }

    fun on_query(view: View) {
        runOnSingleThread {
            val user = UserDb.instance().userDao().query("taowei")
            LogUtils.d("on_query: $user")
        }
    }

    fun on_delete(view: View) {
        runOnSingleThread {
            val user = User(101,
                    "taowei",
                    "token-101",
                    "icon-101",
                    "itachsheep@163.com",
                    "extension-101")
            LogUtils.d("on_delete")
            UserDb.instance().userDao().delete(user)
        }
    }

    fun runOnSingleThread(run: () -> Unit): Unit {
        CoroutineUtils.io {
            LogUtils.d("runOnSingleThread##22")
            run()
        }
        /*runBlocking {
            launch (Dispatchers.Default) {
                LogUtils.d("runOnSingleThread")
                run()
            }
        }*/
    }

}