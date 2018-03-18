package com.navirice.android.services.realTimeDataServices


import com.navirice.android.tasks.InputStreamTask
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

/**
 * @author Yang Liu
 * @version Feb 23, 2018
 */

class StreamingService {
    private var mClient: Socket? = null
    private var mOutputStream: DataOutputStream? = null

    private var mOnServerNotFound: (() -> Unit)? = null
    private var mOnReceiveData: ((data: ByteArray) -> Unit)? = null
    private var mOnDisconnect: (() -> Unit)? = null
    private var mOnConnected: (() -> Unit)? = null


    fun handlerEvents(onServerNotFound: () -> Unit = {},
                      onDisconnect: () -> Unit = {},
                      onConnected: () -> Unit = {},
                      onReceiveData: (data: ByteArray) -> Unit = {}) {
        this.mOnServerNotFound = onServerNotFound
        this.mOnDisconnect = onDisconnect
        this.mOnConnected = onConnected
        this.mOnReceiveData = onReceiveData
    }

    fun send(data: ByteArray) {
        mOutputStream!!.write(data)
    }

    fun connect(ipAddress: String, port: Int) {
        try {
            mClient = Socket(ipAddress, port)

            InputStreamTask().execute(mClient, mOnReceiveData!!, mOnDisconnect!!)
            mOutputStream = DataOutputStream(mClient!!.getOutputStream())
        } catch (e: IOException) {
            mOnServerNotFound!!()
            return
        }

        mOnConnected!!()
    }
}
