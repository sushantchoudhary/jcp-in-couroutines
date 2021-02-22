package com.appdev.schoudhary.jcpInKotlin

import java.util.concurrent.ExecutionException
import java.util.concurrent.FutureTask

class Preloader {
    private val future = FutureTask<ProductInfo> {
        loadProductInfo()
    }

    fun start() = Thread(future).start()

    @Throws(DataLoadException::class, InterruptedException::class)
    fun get():ProductInfo {
        try {
            return future.get()
        } catch (e: ExecutionException) {
            val cause = e.cause
            if(cause is DataLoadException) {
                throw cause
            } else throw  LaunderThrowable.launderThrowable(cause)
        }
    }

    @Throws(DataLoadException::class)
    private fun loadProductInfo(): ProductInfo? {
        return null
    }

    class DataLoadException: Exception()
}

interface ProductInfo

object LaunderThrowable {
    fun launderThrowable(t: Throwable?): RuntimeException {
        return when (t) {
            is RuntimeException -> t
            is Error -> throw (t as Error?)!!
            else -> throw IllegalStateException(
                "Not unchecked",
                t
            )
        }
    }
}