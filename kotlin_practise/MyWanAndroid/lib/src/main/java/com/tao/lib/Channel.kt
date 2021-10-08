package com.tao.lib

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


fun testPipe() {
    runBlocking {
        val numbers = produceNumbers()
        val square = produceSquare(numbers)
        repeat(5) {
            log("receive : ${square.receive()}")
        }
        log("done !!!")
        coroutineContext.cancelChildren()
    }
}

fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
    var x = 1
    while (true) {
        log("produce number ---- $x ")
        send(x++)
    }
}

fun CoroutineScope.produceSquare(numbers: ReceiveChannel<Int> ): ReceiveChannel<Int> = produce {
    for (x in numbers) {
        log("produce square $x")
        send(x * x)
    }
}

fun testProduceChannel() {
    runBlocking {
        val numbers = produceNumbers()
        val receiveChannel = produceSquare(numbers)
        receiveChannel.consumeEach { it ->
            log("receive $it")
        }
    }
}

fun testChannel() {
    val channel = Channel<Int>()
    runBlocking {
        launch {
            for (k in 1..5) {
                log("send $k")
                channel.send(k * k)
                delay(100L)
            }
            channel.close()
        }

        launch {
            repeat(5) {
                val receive = channel.receive()
                log("receive $receive")
            }
        }
    }

}
