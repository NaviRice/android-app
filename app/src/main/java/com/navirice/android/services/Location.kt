package com.navirice.android.services

/**
 * @author Yang Liu
 * @version Dec 14, 2017
 */

class Location(long: Double, lat: Double) {


    val long = long
    val lat = lat

    override fun toString(): String {
        return "[long: $long, lat: $lat]"
    }
}