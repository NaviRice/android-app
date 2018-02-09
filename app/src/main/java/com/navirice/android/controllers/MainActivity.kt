package com.navirice.android.controllers

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.models.Step
import com.navirice.android.services.*
import com.navirice.android.tasks.DataChannelTask
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import java.util.*


/**
 * @author Yang Liu
 * @version Oct 26, 2017
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tts = TextToSpeech(this, TextToSpeech.OnInitListener {})
        tts.language = Locale.US

        val locationService = LocationService()
        val geocodingService = GeocodingService()
        val directionService = DirectionService()

        val textFieldSource = findViewById<EditText>(R.id.text_field_source)

        val textFieldDestination = findViewById<EditText>(R.id.text_field_destination)

        val sourceObservable: BehaviorSubject<Location> = BehaviorSubject.create()

        val startButton: Button = findViewById(R.id.start_button)
        val nextButton = findViewById<Button>(R.id.next_button)

        startButton.isEnabled = false

        val startObservable = RxView.clicks(startButton)

        val destinationObservable = startObservable
                .map { _ ->
                    nextButton.isEnabled = false
                    geocodingService.getLocation(this, textFieldDestination.text.toString())
                }

        var mSteps: List<Step>? = null
        var currentStepIndex = 0

        Observables.combineLatest(sourceObservable, destinationObservable!!)
                .flatMap { input: Pair<Location, Location> ->
                    directionService.getDirections(input.first, input.second)
                }
                .subscribe { steps: List<Step> ->
                    mSteps = steps
                    currentStepIndex = 0
                    nextButton.isEnabled = true
                    Log.d("getDirections", steps.toString())
                    Log.d("getDirections", steps.size.toString())
                }


        val dataChannelService = DataChannelService()
        val stepService = StepService(dataChannelService)

        RxView.clicks(nextButton)
                .subscribe {
                    val currentStep = mSteps!![currentStepIndex]
                    Log.d("Next Step", currentStep.toString())

                    stepService.updateCurrentStep(dataChannelService, currentStep)

                    tts.speak(currentStep.instruction, TextToSpeech.QUEUE_FLUSH, null, null)
                    currentStepIndex++

                    if (currentStepIndex >= mSteps!!.size)
                        nextButton.isEnabled = false
                }

        sourceObservable
                .subscribe { location ->
                    textFieldSource.setText(geocodingService.getCityAndState(this, location), TextView.BufferType.EDITABLE)
                }

        locationService.getLastLocation(this, sourceObservable)

        val textFieldServerIP = findViewById<EditText>(R.id.text_field_server_ip)
        val textFieldServerPort = findViewById<EditText>(R.id.text_field_server_port)

        val connectButton = findViewById<Button>(R.id.connect_button)

        val connectedString = getString(R.string.connected)
        val connectString = getString(R.string.connect)
        val toast = Toast.makeText(this, getString(R.string.fail_to_connect), Toast.LENGTH_SHORT)

        connectButton.setOnClickListener({ _ ->

            val serverIP = textFieldServerIP.text
                    .toString()
            val serverPort = textFieldServerPort.text
                    .toString()

            connectButton.isEnabled = false
            connectButton.text = getString(R.string.connecting)

            DataChannelTask(connectedString, connectString).execute(serverIP, serverPort, dataChannelService, connectButton, startButton, toast)
        })
    }
}