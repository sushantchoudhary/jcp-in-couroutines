package com.appdev.schoudhary.jcpInKotlin

import java.util.*

class ListHelper<E> {
    private val list = Collections.synchronizedList(ArrayList<E>())

    fun putIfAbsent(x: E): Boolean {
        val absent = !list.contains(x)
        if(absent) list.add(x)
        return absent
    }
}

//@ThreadSafe
internal class GoodListHelper<E> {
    private var list = Collections.synchronizedList(ArrayList<E>())
    fun putIfAbsent(x: E): Boolean {
        synchronized(list) {
            val absent = !list.contains(x)
            if (absent) list.add(x)
            return absent
        }
    }
}

//@ThreadSafe
//class ImprovedList<T> (private val list: MutableList<T>) : List<T> {
//    @Synchronized
//    fun putIfAbsent(x: T): Boolean {
//        val contains = list.contains(x)
//        if (!contains) list.add(x)
//        return !contains
//    }
//    override fun isEmpty(): Boolean {
//        return list.isEmpty()
//    }
//
//    override val size: Int
//        get() = TODO("Not yet implemented")
//}