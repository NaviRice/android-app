package com.navirice.android.services

import com.navirice.android.tasks.InputStreamTask
import navirice.proto.ResponseOuterClass.Response
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket


/**
 * @author Yang Liu
 * @version Feb 5, 2018
 */

class DataChannelService {

    var client: Socket? = null
    var outputStream: DataOutputStream? = null

    fun start(serverIP: String, port: Int, onServerNotFound: ()->Unit ,onReceiveData: (response: Response) -> Unit, onDisconnect: ()->Unit): Boolean {
        try {
            client = Socket(serverIP, port)

            InputStreamTask().execute(this, onReceiveData, onDisconnect)
            outputStream = DataOutputStream(client!!.getOutputStream())
            return client!!.isConnected

        } catch (e: IOException) {
            onServerNotFound()
            return false
        }
    }
}