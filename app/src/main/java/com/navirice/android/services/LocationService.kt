package com.navirice.android.services

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.location.*
import com.navirice.android.models.Location
import io.reactivex.Observable


/**
 * @author Yang Liu
 * @version Dec 14, 2017
 */
class LocationService {
    val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val UPDATE_INTERVAL = 1000L
    private var locationSettingsRequest: LocationSettingsRequest? = null
    private var locationRequest: LocationRequest? = null

    init {
        Log.d(this.javaClass.simpleName, "Initialize LocationService")
        locationRequest = LocationRequest()
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest!!.interval = UPDATE_INTERVAL
        locationRequest!!.fastestInterval = UPDATE_INTERVAL

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest!!)
        locationSettingsRequest = builder.build()

    }

    fun checkPermission(context: Activity): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(context: Activity) {
        ActivityCompat.requestPermissions(context,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
    }

    fun startLocationUpdates(context: Activity): Observable<Location> {
        return Observable.create<Location> { observer ->
            val settingsClient = LocationServices.getSettingsClient(context)
            settingsClient.checkLocationSettings(locationSettingsRequest)

            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

            try {
                fusedLocationProviderClient.requestLocationUpdates(locationRequest!!, object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        val lastLocation = locationResult.lastLocation
                        val location = Location(lastLocation.longitude, lastLocation.latitude)
                        observer.onNext(location)
                    }
                }, Looper.myLooper())
            } catch (e: SecurityException) {
                observer.onError(e)
            }
        }
    }

    fun getLastLocation(context: Activity): Observable<Location> {
        return Observable.create<Location> { observer ->
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            try {
                fusedLocationClient.lastLocation
                        .addOnSuccessListener(context, { androidLocation ->
                            if (androidLocation != null) {
                                val location = Location(androidLocation.longitude, androidLocation.latitude)
                                observer.onNext(location)
                            }
                        })
            } catch (e: SecurityException) {
                observer.onError(e)
            }
        }

    }
}