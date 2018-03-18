package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import com.navirice.android.models.Step
import navirice.proto.RequestHeaderOuterClass
import navirice.proto.StepOuterClass

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
object StepDataService {

    fun updateCurrentStep(context: Context, step: Step) {
        val requestHeaderBuilder = RequestHeaderOuterClass.RequestHeader.newBuilder()
        requestHeaderBuilder.type = RequestHeaderOuterClass.RequestHeader.Type.CURRENT_STEP

        val stepBuilder = StepOuterClass.Step.newBuilder()

        stepBuilder.latitude = step.location.latitude
        stepBuilder.longitude = step.location.longitude
        stepBuilder.description = step.instruction
        stepBuilder.icon = step.icon

        val stepData = stepBuilder.build()

        requestHeaderBuilder.length = stepData.serializedSize
        val requestHeader = requestHeaderBuilder.build()

        Log.d(this.javaClass.simpleName, requestHeader.toString())
        Log.d(this.javaClass.simpleName, stepData.toString())

        RealTimeTransportService.send(context, requestHeader, stepData.toByteArray())
    }
}