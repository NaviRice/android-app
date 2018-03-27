package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import com.navirice.android.models.Location
import navirice.proto.LocationOuterClass
import navirice.proto.RequestHeaderOuterClass

/**
 * @author Harry Liu
 * @version Mar 20, 2018
 */
object LocationDataService {

    fun updateCurrentLocation(context: Context, location: Location) {
        val requestHeaderBuilder = RequestHeaderOuterClass.RequestHeader.newBuilder()
        requestHeaderBuilder.type = RequestHeaderOuterClass.RequestHeader.Type.CURRENT_LOCATION

        val locationBuilder = LocationOuterClass.Location.newBuilder()

        locationBuilder.latitude = location.latitude
        locationBuilder.longitude = location.longitude

        val locationData = locationBuilder.build()

        requestHeaderBuilder.length = locationData.serializedSize
        val requestHeader = requestHeaderBuilder.build()

        Log.d(this.javaClass.simpleName, requestHeader.toString())
        Log.d(this.javaClass.simpleName, locationData.toString())

        RealTimeTransportService.send(context, requestHeader, locationData.toByteArray())
    }
}