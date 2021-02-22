package com.appdev.schoudhary.jcpInKotlin

import java.util.concurrent.CountDownLatch

class TestHarness {
     fun timeTasks(nThreads: Int, task: Runnable): Long {
         val startGate = CountDownLatch(1)
         val endGate = CountDownLatch(nThreads)

         for(i in 0 until nThreads) {
            val thread =  Thread {
                 try {
                     startGate.await()
                     try {
                         task.run()
                     } finally {
                         endGate.countDown()
                     }
                 } catch (ignored: InterruptedException) {
                 }
             }
             thread.start()
         }

         val start = System.nanoTime()
         startGate.countDown()
         endGate.await()
         val end = System.nanoTime()
         return end - start
     }


}