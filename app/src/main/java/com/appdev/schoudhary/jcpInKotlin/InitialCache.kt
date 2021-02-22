package com.appdev.schoudhary.jcpInKotlin

import androidx.annotation.GuardedBy
import java.math.BigInteger
import java.util.concurrent.ConcurrentHashMap

class ExpensiveFunction: Computable<String, BigInteger> {
    override fun compute(arg: String): BigInteger {
        //looong wait
        return BigInteger(arg)
    }

}

class Memoizer1<A, V>(private val c: Computable<A, V>) : Computable<A, V> {
    @GuardedBy("this")
    private val cache = hashMapOf<A, V>()

    @Synchronized
    override fun compute(arg: A): V {
        val result = cache.getValue(arg)
        if(result == null) {
            val result = c.compute(arg)
            cache[arg] = result
        }
        return  result
    }

}

class Memoizer2<A, V>(private val c: Computable<A, V>) : Computable<A, V> {
    private val cache: MutableMap<A, V> = ConcurrentHashMap<A, V>()

    @Throws(InterruptedException::class)
    override fun compute(arg: A): V {
        var result = cache.getValue(arg)
        if (result == null) {
            result = c.compute(arg)
            cache[arg] = result
        }
        return result
    }
}

interface Computable<A, V> {
    @Throws(InterruptedException::class)
     fun compute(arg: A): V
}