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
import com.navirice.android.models.Step
import com.navirice.android.services.GeocodingService
import com.navirice.android.services.LocationService
import com.navirice.android.services.RealTimeTransportService
import com.navirice.android.services.StepService
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.*


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
    private var clickStartNavigationObservable: Observable<Any>? = null
    private var clickConnectToServerObservable: Observable<Any>? = null
    private var destinationObservable: Observable<Location>? = null

    val random = Random()

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.test_nav_item -> {

                val step = Step("", "Instruction", "Icon", Location(random.nextDouble(), random.nextDouble()))
                StepService.updateCurrentStep(this, step)
//                startNavigation(Pair(Location(-71.806651, 42.274869), Location(-71.807196, 42.275899)))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
            val serverPort = mServerPortEditText!!.text.toString().toInt()

            mConnectToServerButton!!.isEnabled = false
            mConnectToServerButton!!.text = getString(R.string.connecting)

            RealTimeTransportService.onConnected(this, {
                Log.d("RealTimeTransport", "onConnected")
                mConnectToServerButton!!.text = connectedString
                mStartNavigationButton!!.isEnabled = true
            })

            RealTimeTransportService.onDisconnected(this, {
                Log.d("RealTimeTransport", "onDisconnected")
                mConnectToServerButton!!.text = connectString
                mConnectToServerButton!!.isEnabled = true
                mStartNavigationButton!!.isEnabled = false
            })

            RealTimeTransportService.onReceiveResponse(this, { response ->
                Log.d("RealTimeTransport", response.toString())
            })

            RealTimeTransportService.onServerNotFound(this, {
                Log.d("RealTimeTransport", "onServerNotFound")
                mConnectToServerButton!!.text = connectString
                mConnectToServerButton!!.isEnabled = true
                toast.show()
            })

            RealTimeTransportService.start(this, serverIP, serverPort)
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