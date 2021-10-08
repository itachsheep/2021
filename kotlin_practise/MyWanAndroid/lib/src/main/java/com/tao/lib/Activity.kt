package com.tao.lib

import kotlinx.coroutines.*

class Activity {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun onCreate() {
        repeat(10) { i ->
            coroutineScope.launch {
                log("coroutine $i start")
                delay((i + 1) * 100L)
                log("coroutine $i is done")
            }
        }
    }

    fun onDestroy() {
        coroutineScope.cancel()
    }
}