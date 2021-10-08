package com.tao.lib

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

sealed class CounterMsg
object IncCounter: CounterMsg()
class GetCounter(val response: CompletableDeferred<Int> ): CounterMsg()

fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0

    for (msg in channel) {
//        log("actor work in")
        when( msg ) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun testActor() {
    runBlocking {
        val counterActor = counterActor()
        withContext(Dispatchers.Default) {
            massiveRun {
                counterActor.send(IncCounter)
            }
        }
        val response = CompletableDeferred<Int>()
        counterActor.send(GetCounter(response))
        log("counter = ${response.await()}, cost =  $time")
        counterActor.close()
    }
}


fun testMutex() {
    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock {
                    counter++
                }
            }
        }
        log("counter = $counter")
    }
}

val mutex = Mutex()

val counterContext = newSingleThreadContext("CounterContext")

//@Volatile
var counter = 0
//var counter = AtomicInteger()

fun testMassiveRun2() {
    runBlocking {
        withContext(counterContext) {
            massiveRun {
                counter++
            }
        }
        log("counter = $counter")
    }
}

fun testMassiveRun() {
    runBlocking {
        withContext(Dispatchers.Default) {
            massiveRun {
//                counter.incrementAndGet()
                withContext(counterContext) {
                    counter++
                }
            }
        }
        log("counter = $counter")
    }
}

var time = 0L
suspend fun massiveRun(action:suspend () -> Unit) {
    val n = 100
    val k = 1000
    time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
//                    log("launch")
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    log("cost time: $time")
}

class MultiCoroutine {
}