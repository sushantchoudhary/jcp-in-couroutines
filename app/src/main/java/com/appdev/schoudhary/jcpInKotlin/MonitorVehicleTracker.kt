package com.appdev.schoudhary.jcpInKotlin

import android.graphics.Point
import androidx.annotation.GuardedBy
import java.util.*

class MonitorVehicleTracker  constructor(locations: Map<String, MutablePoint>) {
    @GuardedBy("this")
    private val locations: Map<String, MutablePoint>

    init {
        this.locations = deepCopy(locations)
    }

    @Synchronized
    fun getLocations(): Map<String, MutablePoint> {
        return deepCopy(locations)
    }

    @Synchronized
    fun getLocation(id: String): MutablePoint? {
        val point = locations[id]
        return if(point == null) null else MutablePoint(point)
    }

    @Synchronized
    fun setLocation(id: String, x:Int, y:Int) {
        val loc = locations[id] ?: throw IllegalArgumentException("No such id:$id")
        loc.x = x
        loc.y = y
    }

    private fun deepCopy(m: Map<String, MutablePoint>): Map<String, MutablePoint> {
        val result = hashMapOf<String, MutablePoint>()

        for(key in m.keys) {
            result[key] = MutablePoint(m.getValue(key))
        }
        return Collections.unmodifiableMap(result)
    }

}

//@NotThreadSafe
class MutablePoint constructor(point: MutablePoint) {
    var x:Int = point.x
    var y:Int = point.y
}