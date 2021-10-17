/**
 * @ClassName:      MyLifeCycle.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/17 12:46 PM
 */
package com.tao.lrnpro.test

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.tao.lrnpro.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyLifeCycleObserver(val coroutineContext: CoroutineContext,
                          val lifecycle: Lifecycle) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        CoroutineScope(coroutineContext).launch {
            delay(3000)
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                LogUtils.d("开启定位")
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtils.d("关闭定位")
    }
}