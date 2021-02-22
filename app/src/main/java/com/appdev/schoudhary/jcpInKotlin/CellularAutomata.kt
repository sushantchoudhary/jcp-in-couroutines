package com.appdev.schoudhary.jcpInKotlin

import java.util.concurrent.BrokenBarrierException
import java.util.concurrent.CyclicBarrier

class CellularAutomata(private val mainBoard: Board) {
    private val barrier: CyclicBarrier
    private val workers: Array<Worker?>

    init {
        val count = Runtime.getRuntime().availableProcessors()
        barrier = CyclicBarrier(count) { mainBoard.commitNewValues() }
        workers = arrayOfNulls(size = count)
        for (i in 0 until count) workers[i] = Worker(mainBoard.getSubBoard(count, i))
    }

    private inner class Worker(private val board: Board) : Runnable {
        override fun run() {
            while (!board.hasConverged()) {
                for (x in 0 until board.maxX) for (y in 0 until board.maxY)
                    board.setNewValue(
                        x, y, computeValue(x, y)
                    )
                try {
                    barrier.await()
                } catch (ex: InterruptedException) {
                    return
                } catch (ex: BrokenBarrierException) {
                    return
                }
            }
        }

        private fun computeValue(x: Int, y: Int): Int {
            // Compute the new value that goes in (x,y)
            return 0
        }
    }

    fun start() {
        for (i in workers.indices) Thread(workers[i]).start()
        mainBoard.waitForConvergence()
    }

    interface Board {
        val maxX: Int
        val maxY: Int

        fun getValue(x: Int, y: Int): Int
        fun setNewValue(x: Int, y: Int, value: Int): Int
        fun commitNewValues()
        fun hasConverged(): Boolean
        fun waitForConvergence()
        fun getSubBoard(numPartitions: Int, index: Int): Board
    }
}