package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import com.navirice.android.models.Location
import navirice.proto.AccelerationForceOuterClass
import navirice.proto.LocationOuterClass
import navirice.proto.RequestHeaderOuterClass
import navirice.proto.RotationRateOuterClass

/**
 * @author Harry Liu
 * @version Mar 20, 2018
 */
object RotationRateDataService {

    fun updateRotationRate(context: Context, x: Float, y: Float, z: Float) {
        val requestHeaderBuilder = RequestHeaderOuterClass.RequestHeader.newBuilder()
        requestHeaderBuilder.type = RequestHeaderOuterClass.RequestHeader.Type.CURRENT_ROTATION_RATE

        val rotationRateBuilder = RotationRateOuterClass.RotationRate.newBuilder()

        rotationRateBuilder.x = x
        rotationRateBuilder.y = y
        rotationRateBuilder.z = z

        val rotationRateData = rotationRateBuilder.build()

        requestHeaderBuilder.length = rotationRateData.serializedSize
        val requestHeader = requestHeaderBuilder.build()

        Log.d(this.javaClass.simpleName, requestHeader.toString())
        Log.d(this.javaClass.simpleName, rotationRateData.toString())

        RealTimeTransportService.send(context, requestHeader, rotationRateData.toByteArray())
    }
}