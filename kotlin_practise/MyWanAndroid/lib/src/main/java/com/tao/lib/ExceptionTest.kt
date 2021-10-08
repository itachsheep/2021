package com.tao.lib

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException

fun testExceptionTransport() {
    runBlocking {
        val handler = CoroutineExceptionHandler() { _, exception ->
            log("CoroutineExceptionHandler got $exception")
        }
        val job = GlobalScope.launch(handler) {
            log("throw exception in launch")
            throw IndexOutOfBoundsException("launch")
        }

        val deferred = GlobalScope.async(handler) {
            log("throw exception in async")
            throw ArithmeticException("async")
        }
        job.join()
        deferred.await()
        log("join failed job")


    }
}

class ExceptionTest {
}