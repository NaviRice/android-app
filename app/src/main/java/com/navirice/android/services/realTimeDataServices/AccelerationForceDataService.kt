package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import com.navirice.android.models.Location
import navirice.proto.AccelerationForceOuterClass
import navirice.proto.LocationOuterClass
import navirice.proto.RequestHeaderOuterClass

/**
 * @author Harry Liu
 * @version Mar 20, 2018
 */
object AccelerationForceDataService {

    fun updateAccelerationForce(context: Context, x: Float, y: Float, z: Float) {
        val requestHeaderBuilder = RequestHeaderOuterClass.RequestHeader.newBuilder()
        requestHeaderBuilder.type = RequestHeaderOuterClass.RequestHeader.Type.CURRENT_ACCELERATION_FORCE

        val accelerationForceBuilder = AccelerationForceOuterClass.AccelerationForce.newBuilder()

        accelerationForceBuilder.x = x
        accelerationForceBuilder.y = y
        accelerationForceBuilder.z = z

        val accelerationForceData = accelerationForceBuilder.build()

        requestHeaderBuilder.length = accelerationForceData.serializedSize
        val requestHeader = requestHeaderBuilder.build()

        Log.d(this.javaClass.simpleName, requestHeader.toString())
        Log.d(this.javaClass.simpleName, accelerationForceData.toString())

        RealTimeTransportService.send(context, requestHeader, accelerationForceData.toByteArray())
    }
}