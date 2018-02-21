package com.navirice.android.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.services.DataChannelService
import com.navirice.android.services.GeocodingService
import com.navirice.android.services.LocationService
import com.navirice.android.tasks.DataChannelTask
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject


/**
 * @author Yang Liu
 * @version Oct 26, 2017
 */
class MainActivity : AppCompatActivity() {

    // Views
    var mSourceEditText: EditText? = null
    var mDestinationEditText: EditText? = null
    var mServerIPEditText: EditText? = null
    var mServerPortEditText: EditText? = null
    var mConnectToServerButton: Button? = null
    var mStartNavigationButton: Button? = null

    // Services
    var mLocationService: LocationService? = null
    var mGeocodingService: GeocodingService? = null
    var mDataChannelService: DataChannelService? = null

    // Subjects
    var mSourceSubject: Subject<Location>? = null

    // Observables
    var clickStartNavigationObservable: Observable<Any>? = null
    var clickConnectToServerObservable: Observable<Any>? = null
    var destinationObservable: Observable<Location>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findAndInitViews()
        initServices()

        initObservables()

        Observables.combineLatest(mSourceSubject!!, destinationObservable!!)
                .subscribe(this::startNavigation)

        mSourceSubject!!
                .subscribe(this::updateSourceCityAndState)

        getCurrentLocation()

        initClickToServerHandler()
    }

    private fun getCurrentLocation() {
        if (!mLocationService!!.checkPermission(this))
            mLocationService!!.requestPermission(this)
        else
            mLocationService!!.getLastLocation(this).subscribe { location ->
                mSourceSubject!!.onNext(location)
            }


    }

    private fun startNavigation(locations: Pair<Location, Location>) {
        val intent = NavigationActivity.newIntent(this, locations.first, locations.second)
        startActivity(intent)
    }

    private fun updateSourceCityAndState(location: Location) {
        mSourceEditText!!.setText(
                mGeocodingService!!.getCityAndState(this, location),
                TextView.BufferType.EDITABLE)
    }

    private fun connectToServer(connectedString: String, connectString: String, toast: Toast): (_: Any) -> Unit {
        return { _ ->
            val serverIP = mServerIPEditText!!.text.toString()
            val serverPort = mServerPortEditText!!.text.toString()

            mConnectToServerButton!!.isEnabled = false
            mConnectToServerButton!!.text = getString(R.string.connecting)

            DataChannelTask(connectedString, connectString).execute(
                    serverIP,
                    serverPort,
                    mDataChannelService,
                    mConnectToServerButton,
                    mStartNavigationButton,
                    toast
            )
        }

    }

    private fun initClickToServerHandler() {
        val connectedString = getString(R.string.connected)
        val connectString = getString(R.string.connect)
        val toast = Toast.makeText(this, getString(R.string.fail_to_connect), Toast.LENGTH_SHORT)

        clickConnectToServerObservable!!.subscribe(connectToServer(connectedString, connectString, toast))
    }

    private fun initObservables() {
        clickStartNavigationObservable = RxView.clicks(mStartNavigationButton!!)
        clickConnectToServerObservable = RxView.clicks(mConnectToServerButton!!)
        destinationObservable = clickStartNavigationObservable!!
                .map(this::getDestination)
        mSourceSubject = BehaviorSubject.create()
    }

    private fun getDestination(any: Any): Location {
        return mGeocodingService!!.getLocation(this, mDestinationEditText!!.text.toString())
    }

    private fun initServices() {
        mLocationService = LocationService()
        mGeocodingService = GeocodingService()
        mDataChannelService = DataChannelService()
    }

    private fun findAndInitViews() {
        mSourceEditText = findViewById(R.id.text_field_source)
        mDestinationEditText = findViewById(R.id.text_field_destination)
        mServerIPEditText = findViewById(R.id.text_field_server_ip)
        mServerPortEditText = findViewById(R.id.text_field_server_port)
        mConnectToServerButton = findViewById(R.id.connect_button)
        mStartNavigationButton = findViewById(R.id.start_button)

        mStartNavigationButton!!.isEnabled = false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == mLocationService!!.LOCATION_PERMISSION_REQUEST_CODE) {
            mLocationService!!.getLastLocation(this)
                    .subscribe { location ->
                        mSourceSubject!!.onNext(location)
                    }
        }
    }
}