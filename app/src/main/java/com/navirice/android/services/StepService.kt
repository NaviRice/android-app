package com.navirice.android.services

import android.util.Log
import com.google.protobuf.ByteString
import com.navirice.android.models.Step
import kotlinx.serialization.json.JSON
import navirice.proto.RequestOuterClass

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
class StepService(dataChannelService: DataChannelService) {

    private val BASE_URL = "/steps"

    fun updateCurrentStep(dataChannelService: DataChannelService, step: Step) {
        val requestBuilder = RequestOuterClass.Request.newBuilder()
        requestBuilder.command = RequestOuterClass.Request.Command.UPDATE
        requestBuilder.resource = "$BASE_URL/current"
        requestBuilder.options = ""

        val body = JSON.stringify(step).toByteArray()
        requestBuilder.body = ByteString.copyFrom(body)

        val request = requestBuilder.build()

        Log.d(this.javaClass.simpleName, request.toString())

        dataChannelService.outputStream!!
                .write(request.toByteArray())
    }
}