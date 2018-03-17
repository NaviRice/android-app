package com.navirice.android.services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.navirice.android.contracts.ActionContract
import com.navirice.android.contracts.DataChannelServiceContract
import com.navirice.android.tasks.DataChannelTask


/**
 * @author Yang Liu
 * @version Feb 5, 2018
 */

class DataChannelService : Service() {
    var mStreamingService: StreamingService? = null
    private var mLocalBroadcastManager: LocalBroadcastManager? = null

    override fun onCreate() {

        mStreamingService = StreamingService()

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this)
        mLocalBroadcastManager!!.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val data = intent.getByteArrayExtra(DataChannelServiceContract.DATA)
                mStreamingService!!.send(data)
            }
        }, IntentFilter(ActionContract.SEND_DATA))

        mStreamingService!!.handlerEvents(
                this::serverNotFound,
                this::disconnected,
                this::connected,
                this::receiveData
        )
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val serverIP = intent.getStringExtra(DataChannelServiceContract.SERVER_IP)
        val serverPort = intent.getIntExtra(DataChannelServiceContract.SERVER_PORT, 8000)

        DataChannelTask().execute(mStreamingService!!, serverIP, serverPort)

        return START_STICKY
    }

    private fun connected() {
        Log.d("DataChannelService", "CONNECTED")
        val connectedIntent = Intent(ActionContract.CONNECTED)
        mLocalBroadcastManager!!.sendBroadcast(connectedIntent)
    }

    private fun serverNotFound() {
        Log.d("DataChannelService", "SERVER_NOT_FOUND")
        val serverNotFoundIntent = Intent(ActionContract.SERVER_NOT_FOUND)
        mLocalBroadcastManager!! .sendBroadcast(serverNotFoundIntent)
    }

    private fun receiveData(data: ByteArray) {
        Log.d("DataChannelService", "RECEIVE_DATA")
        val receiveDataIntent = Intent(ActionContract.RECEIVE_DATA)
        receiveDataIntent.putExtra(DataChannelServiceContract.DATA, data)
        mLocalBroadcastManager!!.sendBroadcast(receiveDataIntent)
    }

    private fun disconnected() {
        Log.d("DataChannelService", "DISCONNECTED")
        val disconnectedFromServerIntent = Intent(ActionContract.DISCONNECTED)
        mLocalBroadcastManager!!.sendBroadcast(disconnectedFromServerIntent)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}