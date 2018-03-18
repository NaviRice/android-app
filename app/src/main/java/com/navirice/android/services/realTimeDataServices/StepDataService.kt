package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import com.navirice.android.models.Step
import navirice.proto.RequestOuterClass
import navirice.proto.StepOuterClass

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
object StepDataService {

    fun updateCurrentStep(context: Context, step: Step) {
        val requestBuilder = RequestOuterClass.Request.newBuilder()
        requestBuilder.type = RequestOuterClass.Request.Type.CURRENT_STEP

        val stepBuilder = StepOuterClass.Step.newBuilder()

        stepBuilder.latitude = step.location.latitude
        stepBuilder.longitude = step.location.longitude
        stepBuilder.description = step.instruction
        stepBuilder.icon = step.icon

        val stepData = stepBuilder.build()

        requestBuilder.length = stepData.serializedSize
        requestBuilder.body = stepData.toByteString()

        val request = requestBuilder.build()

        Log.d(this.javaClass.simpleName, request.toString())

        RealTimeTransportService.send(context, request)
    }
}