package com.navirice.android.tasks

import android.os.AsyncTask
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.navirice.android.services.DataChannelService

/**
 * @author Yang Liu
 * @version Feb 8, 2018
 */
class DataChannelTask : AsyncTask<Any, Any, Triple<Boolean, Button, Button>> {

    enum class Event {
        ON_FAIL_TO_FIND_SERVER,
        ON_DISCONNECT
    }

    var connectedString: String? = null
    var connectString: String? = null

    constructor(connectedString: String, connectString: String) : super() {
        this.connectedString = connectedString
        this.connectString = connectString
    }

    override fun doInBackground(vararg params: Any?): Triple<Boolean, Button, Button> {

        val serverIP = params[0].toString()
        val serverPort = params[1].toString().toInt()
        val dataChannelService = params[2] as DataChannelService
        val connectButton = params[3] as Button
        val startButton = params[4] as Button
        val toast = params[5] as Toast

        val isConnected = dataChannelService.start(serverIP, serverPort, {
            publishProgress(Event.ON_FAIL_TO_FIND_SERVER, toast)
        }, { response ->
            Log.d(this.javaClass.simpleName, response.toString())
        }, {
            publishProgress(Event.ON_DISCONNECT, connectButton, startButton)
        })

        return Triple(isConnected, connectButton, startButton)
    }

    override fun onProgressUpdate(vararg params: Any?) {

        val event = params[0] as Event

        when(event) {
            Event.ON_DISCONNECT -> {
                val connectButton = params[1] as Button
                val startButton = params[2] as Button

                connectButton.text = connectString
                connectButton.isEnabled = true
                startButton.isEnabled = false
            }

            Event.ON_FAIL_TO_FIND_SERVER -> {
                val toast = params[1] as Toast
                toast.show()
            }
        }

    }

    override fun onPostExecute(triple: Triple<Boolean, Button, Button>) {
        val isConnected = triple.first
        val connectButton = triple.second
        val startButton = triple.third

        if (isConnected) {
            connectButton.text = connectedString
            startButton.isEnabled = true
        } else {
            connectButton.text = connectString
            connectButton.isEnabled = true
        }
    }
}