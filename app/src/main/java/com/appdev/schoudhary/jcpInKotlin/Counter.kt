package com.appdev.schoudhary.jcpInKotlin

import androidx.annotation.GuardedBy

class Counter {
    @GuardedBy("this")
    private var value = 0L

    @Synchronized
    fun getValue(): Long {
        return value
    }

    @Synchronized
    fun increment(): Long {
        if (value == Long.MAX_VALUE) {
            throw IllegalStateException("counter overflow")
        }
        return ++value
    }
}