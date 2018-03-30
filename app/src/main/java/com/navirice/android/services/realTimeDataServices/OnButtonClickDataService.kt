package com.navirice.android.services.realTimeDataServices

import android.content.Context
import android.util.Log
import navirice.proto.RequestHeaderOuterClass

/**
 * @author Harry Liu
 * @version Mar 30, 2018
 */

object OnButtonClickDataService {
    fun onButtonClick(context: Context) {
        val requestHeaderBuilder = RequestHeaderOuterClass.RequestHeader.newBuilder()
        requestHeaderBuilder.type = RequestHeaderOuterClass.RequestHeader.Type.ON_BUTTON_CLICK

        requestHeaderBuilder.length = 0
        val requestHeader = requestHeaderBuilder.build()

        Log.d(this.javaClass.simpleName, requestHeader.toString())

        RealTimeTransportService.send(context, requestHeader, ByteArray(0))
    }
}
