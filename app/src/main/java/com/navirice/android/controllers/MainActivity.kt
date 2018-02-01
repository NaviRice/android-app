package com.navirice.android.controllers

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.models.Step
import com.navirice.android.services.DirectionService
import com.navirice.android.services.GeocodingService
import com.navirice.android.services.LocationService
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

        val startObservable = RxView.clicks(startButton)

        val destinationObservable = startObservable
                .map { _ ->
                    nextButton.isEnabled = false
                    geocodingService.getLocation(this, textFieldDestination.text.toString())
                }

        var mSteps: List<Step>? = null

        Observables.combineLatest(sourceObservable, destinationObservable!!)
                .flatMap { input: Pair<Location, Location> ->
                    directionService.getDirections(input.first, input.second)
                }
                .subscribe { steps: List<Step> ->
                    mSteps = steps
                    nextButton.isEnabled = true
                    Log.d("getDirections", steps.toString())
                    Log.d("getDirections", steps.size.toString())
                }


        var currentStep = 0
        RxView.clicks(nextButton)
                .subscribe {
                    Log.d("Next Step", mSteps!![currentStep].toString())
                    tts.speak(mSteps!![currentStep].instruction, TextToSpeech.QUEUE_FLUSH, null, null)
                            currentStep++
                    if(currentStep >= mSteps!!.size)
                        nextButton.isEnabled = false
                }

        sourceObservable
                .subscribe { location ->
                    textFieldSource.setText(geocodingService.getCityAndState(this, location), TextView.BufferType.EDITABLE)
                }

        locationService.getLastLocation(this, sourceObservable)
    }
}