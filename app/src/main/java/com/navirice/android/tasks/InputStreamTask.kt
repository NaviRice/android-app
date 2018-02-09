package com.navirice.android.tasks

import android.os.AsyncTask
import com.navirice.android.services.DataChannelService
import navirice.proto.ResponseOuterClass
import java.io.DataInputStream
import java.util.*

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
class InputStreamTask : AsyncTask<Any, Any, Unit>() {
    override fun doInBackground(vararg params: Any?) {
        val dataChannelService = params[0] as DataChannelService
        val onReceiveData = params[1] as (response: ResponseOuterClass.Response) -> Unit
        val onDisconnect = params[2] as () -> Unit

        val dataInputStream = DataInputStream(
                dataChannelService.client!!.getInputStream()
        )

        while (true) {
            val buffer = ByteArray(1024, { _ -> 0 })

            val length = dataInputStream.read(buffer)

            if(length < 0) {
                onDisconnect()
                break
            }
            val newBuffer = Arrays.copyOf(buffer, length)

            val response = ResponseOuterClass.Response.parseFrom(newBuffer)
            onReceiveData(response)
        }
    }
}