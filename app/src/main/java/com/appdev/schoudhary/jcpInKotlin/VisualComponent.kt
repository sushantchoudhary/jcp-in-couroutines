package com.appdev.schoudhary.jcpInKotlin

import android.text.method.KeyListener
import java.util.concurrent.CopyOnWriteArrayList

class VisualComponent {
    private val keyListeners: MutableList<KeyListener> = CopyOnWriteArrayList()
    private val mouseListeners: MutableList<MouseListener> = CopyOnWriteArrayList()
    fun addKeyListener(listener: KeyListener) {
        keyListeners.add(listener)
    }

    fun addMouseListener(listener: MouseListener) {
        mouseListeners.add(listener)
    }

    fun removeKeyListener(listener: KeyListener) {
        keyListeners.remove(listener)
    }

    fun removeMouseListener(listener: MouseListener) {
        mouseListeners.remove(listener)
    }
}

interface MouseListener