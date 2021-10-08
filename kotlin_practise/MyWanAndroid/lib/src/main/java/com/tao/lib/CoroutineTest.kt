package com.tao.lib

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.Exception
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun testCombineName() {
    runBlocking {
        launch (Dispatchers.Default + CoroutineName("Haha")){
            log(" start")
        }
    }
}

fun testCoroutineName() {
    runBlocking(CoroutineName("Blocking")) {
        log("runBlocking start")
        val res1 = async(CoroutineName("Calculate")){
            log("res1 start")
            delay(500L)
            100
        }

        val res2 = async (CoroutineName("Sum")) {
            log("res2 start")
            delay(400L)
            200
        }

        log("sum = ${res1.await() + res2.await()}")
    }
}

fun testFatherCoroutine() {
    runBlocking {
        log("runBlocking start")
        val request = launch {
            log(" this is request")
            repeat(3) { i ->
                launch {
                    delay((i + 1) * 100L)
                    log("coroutine $i is done")
                }
            }
            log(" request is done")
        }
        request.join()
        log("runBlocking is done")
    }
}

fun testSubCoroutine() {
    runBlocking {
        log("runBlocking start")
        val request = launch {
            log(" this is request ")
            GlobalScope.launch {
                log("job1: run in GlobalScope")
                delay(1000)
                log("job1: i wont affect by cancel")
            }

            launch {
                delay(100L)
                log("job2: i am child of the request coroutine")
                delay(1000L)
                log("job2: i will affect by cancel !!")
            }
        }

        delay(500L)
        request.cancelAndJoin()
        delay(1200L)
        log("runBlocking end")
    }
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun testPrintJob() {
    runBlocking {
        println("job is : ${coroutineContext[Job]} , job: $Job")
    }
}

fun testSwitchCtxOnThread() {
    newSingleThreadContext("Thread1").use { thread1 ->
        newSingleThreadContext("Thread2").use { thread2 ->
            runBlocking(thread1) {
                log("run in thread1")
                withContext(thread2) {
                    log("run in thread2")
                }
                log("back to thread1")
            }
        }
    }
}

fun testLog() {
    log("test log begin")
    runBlocking {
        val a = async {
            log("I'm computing a piece of the answer")
            6
        }
        val b = async {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }
}

fun testLaunch() {
    runBlocking {
        launch { // 运行在父协程的上下文中，即 runBlocking 主协程
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // 将会获取默认调度器
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}

fun testFailedException() {
    runBlocking {
        try {
            println("(${Thread.currentThread().name}): runBlocking")
            failedConcurrentSum()
        } catch (e: Exception) {
            println("exception: " + e.message)
        }
    }
}

suspend fun failedConcurrentSum(): Int {
    return coroutineScope {
        val one = async<Int> {
            delay(1000L)
            println("(${Thread.currentThread().name}): one")
            42
        }

        val two = async<Int> {
            println("(${Thread.currentThread().name}): two")
            delay(1200L)
            throw ArithmeticException("illegal parameter")
        }
        return@coroutineScope (one.await() + two.await())
    }
}

suspend fun doSomething1(): Int {
    println("(${Thread.currentThread().name}): doSomething1")
    delay(1000L)
    return 22
}

suspend fun doSomething2(): Int {
    println("(${Thread.currentThread().name}): doSomething2 ")
    delay(1000L)
    return 33
}

fun somethingAsync1(): Deferred<Int> {
    return GlobalScope.async { doSomething1() }
}

fun somethingAsync2(): Deferred<Int> {
    return GlobalScope.async { doSomething2() }
}

fun testMeasureTime() {
    // 我们可以在协程外面启动异步执行
    val time = measureTimeMillis {
        val one = somethingAsync1()
        val two = somethingAsync2()
        // 但是等待结果必须调用其它的挂起或者阻塞
        // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
        runBlocking {
            println("answer = ${one.await() + two.await()}")
        }
    }
    println("cost time = $time")

}

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