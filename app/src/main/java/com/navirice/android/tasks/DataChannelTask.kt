package com.navirice.android.tasks

import android.os.AsyncTask
import com.navirice.android.services.StreamingService

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
class DataChannelTask : AsyncTask<Any, Unit, Unit>() {

    override fun doInBackground(vararg params: Any?) {

        val streamingClient = params[0] as StreamingService
        val serverIP = params[1].toString()
        val serverPort = params[2].toString().toInt()

        streamingClient.connect(serverIP, serverPort)
    }
}