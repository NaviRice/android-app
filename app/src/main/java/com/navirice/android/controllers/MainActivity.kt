package com.navirice.android.controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import com.navirice.android.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

/**
 * @author Yang Liu
 * @version Oct 26, 2017
 */
class MainActivity : AppCompatActivity() {

    private var sourceObservable: Observable<CharSequence>? = null
    private var destinationObservable: Observable<CharSequence>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textFieldSource: EditText = findViewById(R.id.text_field_source)
        val textFieldDestination: EditText = findViewById(R.id.text_field_destination)

        sourceObservable = RxTextView.afterTextChangeEvents(textFieldSource)
                .observeOn(AndroidSchedulers.mainThread())
                .map { event -> event.view().text }
        destinationObservable = RxTextView.afterTextChangeEvents(textFieldDestination)
                .observeOn(AndroidSchedulers.mainThread())
                .map { event -> event.view().text }
        Observables.combineLatest(sourceObservable!!, destinationObservable!!)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { input -> println("${input.first} || ${input.second} ") }

    }
}
