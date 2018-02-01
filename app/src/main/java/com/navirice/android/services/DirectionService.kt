package com.navirice.android.services

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.navirice.android.models.Location
import com.navirice.android.models.Step
import io.reactivex.Observable

/**
 * @author Yang Liu
 * @version Dec 14, 2017
 */

class DirectionService {
    private val BASE_URL = "https://api.mapbox.com/directions/v5"
    val MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiaGFycnlsaXUyMyIsImEiOiJjamI2NjkxYWE4bmx3MnducTVxYzVyeTlvIn0.WT0ezGYdIRNkKgywZOjxNA"

    init {
        println("Initialize DirectionService")
    }

    fun getDirections(source: Location, destination: Location): Observable<List<Step>> {
        val url = "$BASE_URL/mapbox/cycling/${source.longitude},${source.latitude};${destination.longitude},${destination.latitude}?steps=true&access_token=$MAPBOX_ACCESS_TOKEN"
        return Observable.create<List<Step>> { subscriber ->
            url.httpGet().responseJson { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        println(result.get())
                    }
                    is Result.Success -> {
                        val json = result.get()
                        val jsonObj = json.obj()
                        val routes = jsonObj.getJSONArray("routes")

                        if (routes.length() > 0) {
                            val routeObj = routes.getJSONObject(0)
                            val legs = routeObj.getJSONArray("legs")
                            val leg = legs.getJSONObject(0)
                            val stepsJson = leg.getJSONArray("steps")

                            val steps = (0 until stepsJson.length())
                                    .map { stepsJson.getJSONObject(it) }
                                    .map { stepObj ->
                                        val name = stepObj.getString("name")
                                        val maneuver = stepObj.getJSONObject("maneuver")
                                        val type = maneuver.getString("type")
                                        val iconPieces = mutableListOf<String>()
                                        iconPieces.add(type)
                                        if (maneuver.has("modifier")) {
                                            val modifier = maneuver.getString("modifier")
                                            modifier.split(" ").forEach({ m ->
                                                iconPieces.add(m)
                                            })
                                        }
                                        val icon = iconPieces.joinToString("_")
                                        val instruction = maneuver.getString("instruction")
                                        Step(name, instruction, icon)
                                    }
                            Log.d("getDirections", "Now")
                            subscriber.onNext(steps)
                            subscriber.onComplete()
                        }
                    }
                }
            }
        }

    }
}
