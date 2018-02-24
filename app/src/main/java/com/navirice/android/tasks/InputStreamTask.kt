package com.navirice.android.tasks

import android.os.AsyncTask
import java.io.DataInputStream
import java.net.Socket
import java.util.*

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
class InputStreamTask : AsyncTask<Any, Any, Unit>() {
    override fun doInBackground(vararg params: Any?) {
        val client = params[0] as Socket
        val onReceiveData = params[1] as (data: ByteArray) -> Unit
        val onDisconnect = params[2] as () -> Unit

        val dataInputStream = DataInputStream(client.getInputStream())

        while (true) {
            val buffer = ByteArray(1024, { _ -> 0 })

            val length = dataInputStream.read(buffer)

            if (length < 0) {
                onDisconnect()
                break
            }
            val newBuffer = Arrays.copyOf(buffer, length)
            onReceiveData(newBuffer)
        }
    }
}