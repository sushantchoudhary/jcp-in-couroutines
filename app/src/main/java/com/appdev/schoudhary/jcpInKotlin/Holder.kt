package com.appdev.schoudhary.jcpInKotlin

import java.lang.AssertionError

class Holder constructor(private var n: Int){

    fun assertSanity() {
        if(n!=n) {
            throw AssertionError("this statement is false")
        }
    }
}