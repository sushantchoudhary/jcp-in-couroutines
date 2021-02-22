package com.appdev.schoudhary.jcpInKotlin

import java.io.File
import java.io.FileFilter
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

fun main() {
     val N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    val queue = LinkedBlockingQueue<File>(10)
    val roots = arrayOf<File>()

    val filter = FileFilter { true }

    for(file in roots) {
        Thread(FileCrawler(file, filter, queue)).start()
    }

    for (i in 0 until N_CONSUMERS) Thread(Indexer(queue)).start()

}


class FileCrawler(
    private val root: File,
    private val fileFilter: FileFilter,
    private val fileQueue: BlockingQueue<File>
) : Runnable {


    override fun run() {
        try {
            crawl(root)
        } catch (ex: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    private fun alreadyIndexed(f: File): Boolean {
        return false
    }

    @Throws(InterruptedException::class)
    private fun crawl(root: File) {
        val entries = root.listFiles(fileFilter)
        if (entries != null) {
            for (entry in entries) {
                if (entry.isDirectory) {
                    crawl(entry)
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry)
                }
            }
        }
    }
}

class Indexer constructor(val queue: BlockingQueue<File>) : Runnable {

    override fun run() {
        try {
            while (true) {
                indexFile(queue.take())
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    public fun indexFile(file: File) {

    }


}