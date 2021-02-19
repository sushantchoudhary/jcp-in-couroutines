package com.appdev.schoudhary.jcpInCoroutines

class NoVisibility {
    var ready = false
    var number = 0


    inner class ReaderThread: Thread() {
        override fun run() {
           while (!ready) {
               yield()
           }
            println(number)
        }
    }
}

fun main() {
    val noVisibility = NoVisibility()
    noVisibility.ReaderThread().start()
    noVisibility.number = 42
    noVisibility.ready = true
}