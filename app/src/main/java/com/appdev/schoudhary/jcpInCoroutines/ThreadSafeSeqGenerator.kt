package com.appdev.schoudhary.jcpInCoroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SafeSequence {
    private val mutex = Mutex()
    private var value = 0

    suspend fun getNext(): Int {
        mutex.withLock {
            return value++
        }
    }
}

//SLOW COMPUTATION
//class ThreadConfinedSafeSequence {
//    @OptIn(ObsoleteCoroutinesApi::class)
//    val currentContext = newSingleThreadContext("SafeSequence")
//    var value = 0
//
//    suspend fun increment() {
//        withContext(currentContext) {
//             value++
//        }
//    }
//}

fun main() {
    val seq = SafeSequence()

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
    //Sequence = 100000
}