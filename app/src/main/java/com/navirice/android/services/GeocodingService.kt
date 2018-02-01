package com.navirice.android.services

import android.app.Activity
import android.location.Geocoder
import com.navirice.android.models.Location
import java.util.*

/**
 * @author Yang Liu
 * @version Dec 14, 2017
 */

class GeocodingService {
    init {
        println("Initialize GeocodingService")
    }

    fun getLocation(context: Activity, address: String): Location {
        val geoCoder = Geocoder(context, Locale.getDefault())
        val addresses = geoCoder.getFromLocationName(address, 1)
        val address = addresses[0]
        return Location(address.longitude, address.latitude)
    }

    fun getCityAndState(context: Activity, location: Location): String {
        val geoCoder = Geocoder(context, Locale.getDefault())
        val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        val address = addresses[0]
        return "${address.locality}, ${address.adminArea}"
    }
}