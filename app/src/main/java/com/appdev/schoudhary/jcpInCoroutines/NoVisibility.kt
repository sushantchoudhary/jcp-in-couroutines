package com.appdev.schoudhary.jcpInCoroutines

import kotlin.properties.Delegates

class NoVisibility {
    var ready by Delegates.notNull<Boolean>()
    var number by Delegates.notNull<Int>()


    inner class ReaderThread: Thread() {
        override fun run() {
           while (!ready) {
               yield()
           }
        }
    }
}

fun main() {
    val noVisibility = NoVisibility()
    noVisibility.ReaderThread().start()
    noVisibility.number = 42
    noVisibility.ready = true

}