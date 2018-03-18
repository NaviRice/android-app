package com.navirice.android.services.realTimeDataServices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.navirice.android.contracts.ActionContract
import com.navirice.android.contracts.DataChannelServiceContract
import navirice.proto.RequestOuterClass
import navirice.proto.ResponseOuterClass

/**
 * @author Harry Liu
 * @version Feb 22, 2018
 */

object RealTimeTransportService {
    private var connected = false
    fun start(context: Context, serverIP: String, serverPort: Int) {

        val intent = Intent(context, DataChannelService::class.java)
        intent.putExtra(DataChannelServiceContract.SERVER_IP, serverIP)
        intent.putExtra(DataChannelServiceContract.SERVER_PORT, serverPort)
        context.startService(intent)
    }

    fun onConnected(context: Context, onConnectedHandler: () -> Unit) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                localBroadcastManager.unregisterReceiver(this)
                Log.d("RealTimeTransport", "CONNECTED")
                connected = true
                onConnectedHandler()
            }
        }, IntentFilter(ActionContract.CONNECTED))
    }

    fun onDisconnected(context: Context, onDisconnectedFromServerHandler: () -> Unit) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                localBroadcastManager.unregisterReceiver(this)
                Log.d("RealTimeTransport", "DISCONNECTED")
                connected = false
                onDisconnectedFromServerHandler()
            }
        }, IntentFilter(ActionContract.DISCONNECTED))
    }

    fun onServerNotFound(context: Context, onServerNotFoundHandler: () -> Unit) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("RealTimeTransport", "SERVER_NOT_FOUND")
                onServerNotFoundHandler()
            }
        }, IntentFilter(ActionContract.SERVER_NOT_FOUND))
    }

    fun onReceiveResponse(context: Context, onReceiveResponseHandler: (response: ResponseOuterClass.Response) -> Unit) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        localBroadcastManager.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("RealTimeTransport", "RECEIVE_DATA")

                val data = intent.getByteArrayExtra(DataChannelServiceContract.DATA)
                onReceiveResponseHandler(ResponseOuterClass.Response.parseFrom(data))

            }
        }, IntentFilter(ActionContract.RECEIVE_DATA))
    }

    fun send(context: Context, request: RequestOuterClass.Request) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
        val intent = Intent(ActionContract.SEND_DATA)
        intent.putExtra(DataChannelServiceContract.DATA, request.toByteArray())
        localBroadcastManager.sendBroadcast(intent)
    }

    fun isConnected(): Boolean {
        return connected
    }
}