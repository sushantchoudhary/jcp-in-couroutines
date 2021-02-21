package com.appdev.schoudhary.jcpInKotlin

import java.util.Vector

//@Immutable
class ThreeStooges {
    var stooges = hashSetOf<String>()

    init {
        stooges.add("Moe")
        stooges.add("Larry")
        stooges.add("Curly")
    }

    fun isStooge(name: String): Boolean {
        return  stooges.contains(name)
    }

    fun getStooges(): String {
        val stooges = Vector<String>()
        stooges.add("Moe")
        stooges.add("Larry")
        stooges.add("Curly")
        return stooges.toString()
    }
}