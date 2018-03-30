package com.navirice.android.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.services.GeocodingService
import com.navirice.android.services.LocationService
import com.navirice.android.services.realTimeDataServices.RealTimeTransportService
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
    private var mSourceEditText: EditText? = null
    private var mDestinationEditText: EditText? = null
    private var mServerIPEditText: EditText? = null
    private var mServerPortEditText: EditText? = null
    private var mConnectToServerButton: Button? = null
    private var mStartNavigationButton: Button? = null

    // Services
    private var mLocationService: LocationService? = null
    private var mGeocodingService: GeocodingService? = null

    // Subjects
    private var mSourceSubject: Subject<Location>? = null

    // Observables
    private var mClickStartNavigationObservable: Observable<Any>? = null
    private var mClickConnectToServerObservable: Observable<Any>? = null
    private var mDestinationObservable: Observable<Location>? = null

    private var mConnectedString: String? = null
    private var mConnectString: String? = null
    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findAndInitViews()
        initServices()

        initObservables()

        Observables.combineLatest(mSourceSubject!!, mDestinationObservable!!)
                .subscribe(this::startNavigation)

        mSourceSubject!!
                .subscribe(this::updateSourceCityAndState)

        getCurrentLocation()


        mConnectedString = getString(R.string.connected)
        mConnectString = getString(R.string.connect)

        mToast = Toast.makeText(this, getString(R.string.fail_to_connect), Toast.LENGTH_SHORT)

        initClickToServerHandler()

        if(RealTimeTransportService.isConnected()) {
            handleServerEvents()

            mConnectToServerButton!!.text = mConnectedString
            mConnectToServerButton!!.isEnabled = false
            mStartNavigationButton!!.isEnabled = true
        }
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

    private fun handleServerEvents() {
        RealTimeTransportService.onConnected(this, {
            Log.d("RealTimeTransport", "onConnected")
            mConnectToServerButton!!.text = mConnectedString
            mStartNavigationButton!!.isEnabled = true
        })

        RealTimeTransportService.onDisconnected(this, {
            Log.d("RealTimeTransport", "onDisconnected")
            mConnectToServerButton!!.text = mConnectString
            mConnectToServerButton!!.isEnabled = true
            mStartNavigationButton!!.isEnabled = false
        })

        RealTimeTransportService.onReceiveResponse(this, { response ->
            Log.d("RealTimeTransport", response.toString())
        })

        RealTimeTransportService.onServerNotFound(this, {
            Log.d("RealTimeTransport", "onServerNotFound")
            mConnectToServerButton!!.text = mConnectString
            mConnectToServerButton!!.isEnabled = true
            mToast!!.show()
        })
    }

    private fun connectToServer(): (_: Any) -> Unit {
        return { _ ->
            val serverIP = mServerIPEditText!!.text.toString()
            val serverPort = mServerPortEditText!!.text.toString().toInt()

            mConnectToServerButton!!.isEnabled = false
            mConnectToServerButton!!.text = getString(R.string.connecting)

            handleServerEvents()

            RealTimeTransportService.start(this, serverIP, serverPort)
        }

    }

    private fun initClickToServerHandler() {
        mClickConnectToServerObservable!!.subscribe(connectToServer())
    }

    private fun initObservables() {
        mClickStartNavigationObservable = RxView.clicks(mStartNavigationButton!!)
        mClickConnectToServerObservable = RxView.clicks(mConnectToServerButton!!)
        mDestinationObservable = mClickStartNavigationObservable!!
                .map(this::getDestination)
        mSourceSubject = BehaviorSubject.create()
    }

    private fun getDestination(any: Any): Location {
        return mGeocodingService!!.getLocation(this, mDestinationEditText!!.text.toString())
    }

    private fun initServices() {
        mLocationService = LocationService()
        mGeocodingService = GeocodingService()
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