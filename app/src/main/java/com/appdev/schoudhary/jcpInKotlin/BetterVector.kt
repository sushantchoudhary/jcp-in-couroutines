package com.appdev.schoudhary.jcpInKotlin

import java.util.*

class BetterVector<E> : Vector<E>() {
    @Synchronized
    fun putIfAbsent(x: E): Boolean {
       val absent =  !contains(x)
        if(absent) add(x)
        return absent
    }
}