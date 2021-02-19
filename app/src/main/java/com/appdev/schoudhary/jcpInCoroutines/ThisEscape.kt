package com.appdev.schoudhary.jcpInCoroutines

class ThisEscape() {
    inner class ThisEscape(source: EventSource) {
        init {
            source.registerListener(object : EventListener {
                override fun onEvent(e: Event?) {
                    doSomething(e)
                }
            })
        }
    }
}

fun doSomething(e: Event?) {}

interface EventSource {
    fun registerListener(e: EventListener?)
}

interface EventListener {
    fun onEvent(e: Event?)
}

interface Event