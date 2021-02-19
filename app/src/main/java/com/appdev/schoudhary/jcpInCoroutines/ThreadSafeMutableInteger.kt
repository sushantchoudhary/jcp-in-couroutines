package com.appdev.schoudhary.jcpInCoroutines

import androidx.annotation.GuardedBy

//@ThreadSafe
class ThreadSafeMutableInteger {
    @GuardedBy("this")
    private var value = 0

    @Synchronized
    fun  getValue() = value

    @Synchronized
    fun setValue(newValue: Int) {
        value = newValue
    }
}

//@NotThreadSafe
class MutableInteger {
     private var value = 0

    fun  getValue() = value

    fun setValue(newValue: Int) {
        value = newValue
    }
}

