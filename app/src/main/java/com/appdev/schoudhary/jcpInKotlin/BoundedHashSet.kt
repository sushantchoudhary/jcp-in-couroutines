package com.appdev.schoudhary.jcpInKotlin

import java.util.*
import java.util.concurrent.Semaphore
import kotlin.collections.HashSet

class BoundedHashSet<T>(bound: Int) {
    private val set: MutableSet<T> = Collections.synchronizedSet(HashSet<T>())
    private val sem: Semaphore = Semaphore(bound)

    @Throws(InterruptedException::class)
    fun add(o: T): Boolean {
        sem.acquire()
        var wasAdded = false
        return try {
            wasAdded = set.add(o)
            wasAdded
        } finally {
            if (!wasAdded) sem.release()
        }
    }

    fun remove(o: Any?): Boolean {
        val wasRemoved = set.remove(o)
        if (wasRemoved) sem.release()
        return wasRemoved
    }

}