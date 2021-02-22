package com.appdev.schoudhary.jcpInKotlin

import androidx.annotation.GuardedBy
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class PublishingVehicleTracker(locations: Map<String, SafePoint>) {
    private val locations: Map<String, SafePoint>
    private val unmodifiableMap: Map<String, SafePoint>
    fun getLocations(): Map<String, SafePoint> {
        return unmodifiableMap
    }

    fun getLocation(id: String): SafePoint? {
        return locations[id]
    }

    fun setLocation(id: String, x: Int, y: Int) {
        require(locations.containsKey(id)) { "invalid vehicle name: $id" }
        locations.getValue(id)[x] = y
    }

    init {
        this.locations = ConcurrentHashMap<String, SafePoint>(locations)
        unmodifiableMap = Collections.unmodifiableMap(this.locations)
    }
}

//@ThreadSafe
class SafePoint() {
    @GuardedBy("this")
    private var x = 0

    @GuardedBy("this")
    private var y = 0

    private constructor(a: IntArray) : this()
    constructor(p: SafePoint) : this(p.get())

    @Synchronized
    fun get(): IntArray {
        return intArrayOf(x, y)
    }

    @Synchronized
    operator fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}