package com.navirice.android.services

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * @author Yang Liu
 * @version Dec 14, 2017
 */

object GeocodingService {

    private const val BASE_URL = "https://api.mapbox.com/geocoding/v5"
    private const val MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiaGFycnlsaXUyMyIsImEiOiJjamI2NjkxYWE4bmx3MnducTVxYzVyeTlvIn0.WT0ezGYdIRNkKgywZOjxNA"

    init {
        println("Initialize GeocodingService")
    }

    fun getLatAndLong(locationName: String): Subject<Location> {
        val url = "$BASE_URL/mapbox.places/$locationName.json?country=us&access_token=$MAPBOX_ACCESS_TOKEN"
        println(url)
        val locationSubject: Subject<Location> = BehaviorSubject.create()
        url.httpGet().responseJson { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    println(result.get())
                }
                is Result.Success -> {
                    val json = result.get()
                    val jsonObj = json.obj()
                    val locations = jsonObj.getJSONArray("features")
                    if (locations.length() > 0) {
                        val locationObj = locations.getJSONObject(0)
                        val center = locationObj.getJSONArray("center")
                        val location = Location(center[0] as Double, center[1] as Double)
                        locationSubject.onNext(location)
                    }
                }
            }
        }
        return locationSubject
    }
}