package com.appdev.schoudhary.jcpInKotlin

import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class SafeDelegatingVehicleTracker  constructor(points: Map<String, ImmutablePoint>){
    private val locations:  ConcurrentMap<String, ImmutablePoint> = ConcurrentHashMap(points)
    private val unModifiableMap: Map<String, ImmutablePoint> = Collections.unmodifiableMap(points)

    fun getLocations(): Map <String, ImmutablePoint> {
        return unModifiableMap
    }

    fun getLocation(id: String): ImmutablePoint {
        return locations.getValue(id)
    }

    fun setLocations(id: String, x: Int, y: Int) {
        if(locations.replace(id, ImmutablePoint(x, y)) == null) {
            throw  IllegalArgumentException("Invalid vehicle name:$id")
        }
    }
 }

//@Immutable
class ImmutablePoint constructor(pointX: Int, pointY: Int) {
    val x:Int = pointX
    val y:Int  = pointY
}