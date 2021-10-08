package com.tao.lib

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

suspend fun intToString(value: Int): String {
    delay(500L)
    return "this is ${value * 100}"
}

fun testTransform() {
    runBlocking {
        (1..3).asFlow()
                .transform { value ->
                    emit(value * 10 + 1)
                    emit(value + 10000)
                }
                .collect { value ->
                    log("$value")
                }
    }
}

fun testFlowOperator() {
    runBlocking {
        (1..5).asFlow()
                .map { value -> intToString(value) }
                .collect { res -> log("$res") }
    }
}

fun simple(): Flow<Int> = flow<Int> {
    for (k in 1..3) {
        delay(100L)
        emit(k)
    }
}

fun testCancelFlow() {
    runBlocking {
        withTimeoutOrNull(240L) {
            simple().collect { value ->
                log("flow $value done")
            }
        }
    }
}

//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
fun testFlow() {
    runBlocking {
        launch {
            for (i in 1..3) {
                log("launch $i done")
                delay(100L)
            }
        }
        simple().collect { value ->
            log("flow $value done")
        }
    }
}

class AsyncFlow {
}