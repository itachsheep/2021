package com.tao.lib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun CoroutineScope.fizz(): ReceiveChannel<String> {
    return produce<String> {
        while (true) {
            delay(300L)
            send("fizz");
        }
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while (true) {
        delay(500L)
        send("buzz")
    }
}

suspend fun selectFizzBuzz(
        fizz: ReceiveChannel<String> ,
        buzz: ReceiveChannel<String>) {
    select<Unit> {
        fizz.onReceive {it ->
            log("fizz -> $it")
        }

        buzz.onReceive { it ->
            log("buzz -> $it")
        }
    }
}

fun testSelect() {
    runBlocking {
        val fizz = fizz()
        val buzz = buzz()
        repeat(7) {
            selectFizzBuzz(fizz,buzz)
        }
        coroutineContext.cancelChildren()
    }
}