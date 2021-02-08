package com.appdev.schoudhary.jcpInCoroutines

import kotlinx.coroutines.*


class UnsafeSequence {
    private var value = 0

    fun getNext(): Int {
        return value++
    }
}

fun main() {
    val seq = UnsafeSequence()

    suspend fun sendDataToMultipleThreads(action: suspend () -> Unit) {
        val n = 100
        val k = 1000
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
        println("Completed ${n * k} actions")
    }

    runBlocking {
        withContext(Dispatchers.Default) {
            sendDataToMultipleThreads { seq.getNext() }
        }
        println("Sequence = ${seq.getNext()}")
    }

    //result: Completed 100000 actions
    //Sequence = 50798
}