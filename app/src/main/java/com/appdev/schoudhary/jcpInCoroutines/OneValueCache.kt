package com.appdev.schoudhary.jcpInCoroutines

import java.math.BigInteger

class OneValueCache constructor(i: BigInteger, factors: Array<BigInteger>) {
    private var  lastNumber: BigInteger = i
    private var  lastFactors: Array<BigInteger?> = factors.copyOf(factors.size)

    fun getFactors(i: BigInteger): Array<BigInteger?>? {
        return if(lastNumber != i) {
            null
        } else {
            lastFactors.copyOf(lastFactors.size)
        }
    }
}