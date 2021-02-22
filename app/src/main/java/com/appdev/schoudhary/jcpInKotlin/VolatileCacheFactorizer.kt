package com.appdev.schoudhary.jcpInKotlin

import java.math.BigInteger
import java.util.*

//class VolatileCachedFactorizer : GenericServlet(), Servlet
// {
    @Volatile
    private var cache = OneValueCache(BigInteger.ZERO, arrayOf(BigInteger.ZERO))
//    fun service(req: ServletRequest?, resp: ServletResponse?) {
//        val i: BigInteger = extractFromRequest(req)
//        var factors: Array<BigInteger?>? = cache.getFactors(i)
//        if (factors == null) {
//            factors = factor(i)
//            cache = OneValueCache(i, factors)
//        }
//        encodeIntoResponse(resp, factors)
//    }

//    private fun encodeIntoResponse(resp: ServletResponse?, factors: Array<BigInteger?>?) {}
//    private fun extractFromRequest(req: ServletRequest?): BigInteger {
//        return Big/**/Integer("7")
//    }

    private fun factor(i: BigInteger?): Array<BigInteger?> {
        return arrayOf<BigInteger?>(i)
    }
//}
