package com.tao.lib

import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun testWithTimeOut() {
    runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) {
                println("i sleeping ...")
                delay(500L)
            }
            "Done"
        }
        println("$result")
    }
}

fun testCancelJob2() {
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            try {
                var i = 0
                var nextPrintTime = startTime
                while (isActive) { // 一个执行计算的循环，只是为了占用 CPU
                    // 每秒打印消息两次
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            } finally {
                println("job finally release ")
            }

        }

        delay(1000L)
        println("i must kill you !!")
        job.cancelAndJoin()
        println("i am free , exit")
    }
}

fun testCancelJob() {
    runBlocking {
        val job = launch {
            repeat(1000) {
                println("wait a moment ...")
                delay(500L)
            }
        }

        delay(3000L)
        println("i must kill you !!")
        job.cancel()
        job.join()
        println("i am free , exit")
    }
}

fun testGlobalScope() {
    runBlocking {
        val job = GlobalScope.launch {
            repeat(1000) {
                println("I sleeping ...")
                delay(500L)
            }
        }
        job.start()
        delay(2000L)
    }
}

fun testLargeCoroutine() {
    runBlocking {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }
}

suspend fun printWaiting() {
    delay(200L)
    println("runBlocking wait 200ms")
}

fun testCoroutineScope() {
    runBlocking {
        println("runBlocking start")
        launch {
            printWaiting()
        }

        coroutineScope {
            launch {
                delay(500L)
                println("coroutineScope wait 500ms")
            }
            delay(100L)
            println("coroutineScope end")
        }

        println("runBlocking end")
    }
}


fun testJob() {
    println("${Thread.currentThread().name} start")
    runBlocking {//作为用来启动顶层主协程的适配器
        val job = GlobalScope.launch {
            // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("${Thread.currentThread().name} - world!")
        }

        println("${Thread.currentThread().name} - hello")
        job.join()
    }
    println("#end#")

}

fun testFirstCoroutine() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
    thread { // 在后台启动一个新的协程并继续
//        delay(1000L)
        Thread.sleep(1000L)//非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("world!")
    }
    println("hello")
//    Thread.sleep(2000L)//阻塞主线程 2 秒钟来保证 JVM 存活
    runBlocking {
        //调用了 runBlocking 的主线程会一直 阻塞
        // 直到 runBlocking 内部的协程执行完毕。
        delay(2000L)
    }
    println("## end ##")
}