package com.appdev.schoudhary.jcpInCoroutines

import kotlinx.coroutines.*
import kotlin.concurrent.thread

object ExpensiveObject {
    init {
        var counter = 0
        val threadsCount = 100000
        for (i in 0..threadsCount) {
            thread {
                counter++
            }
        }
    }
}

//@NotThreadSafe
//Lazy initialization idiom
class LazyInitRace {
    private var instance: ExpensiveObject? = null

    fun getExpensiveObject(): ExpensiveObject {
        if (instance == null) {
            instance = ExpensiveObject
        }
        return instance as ExpensiveObject
    }
}

fun main() {
    val race = LazyInitRace()
    suspend fun causeRaceCondition(action: suspend () -> Unit) {
        val k = 5
        coroutineScope {
            launch {
                repeat(k) { action() }
            }
        }
        println("Completed all actions")
    }


    runBlocking {
        withContext(Dispatchers.Default) {
            causeRaceCondition { race.getExpensiveObject() }
        }
        println("Wait completes..")
    }

}