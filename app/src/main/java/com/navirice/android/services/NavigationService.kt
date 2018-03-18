package com.navirice.android.services

import android.content.Context
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.mapbox.services.android.telemetry.location.LocationEngine
import com.navirice.android.BuildConfig
import com.navirice.android.Utilities
import com.navirice.android.models.Location
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Harry Liu
 * @version Feb 19, 2018
 */
class NavigationService {
    private var navigation: MapboxNavigation? = null

    constructor(context: Context, locationEngine: LocationEngine) {
        navigation = MapboxNavigation(context, BuildConfig.MAP_BOX_API_ACCESS_TOKEN)
        navigation!!.locationEngine = locationEngine
    }

    fun addProgressChangeListener(onCurrentStep: (name: String,
                                                  instruction: String,
                                                  iconID: String,
                                                  location: Location,
                                                  distanceRemaining: Double,
                                                  durationRemaining: Double) -> Unit) {

        navigation!!.addProgressChangeListener { _, routeProgress ->
            val currentProgress = routeProgress!!.currentLegProgress()
            val upComingStep = currentProgress.upComingStep()

            if (upComingStep != null) {

                val maneuver = upComingStep.maneuver()
                val iconID = Utilities.getIconIdentifier(maneuver)

                var name = upComingStep.name()
                name = if(name != null) name else ""
                val point = maneuver.location()
                val location = Location(point.longitude(), point.latitude())
                var instruction = maneuver.instruction()
                instruction = if(instruction != null) instruction else ""

                onCurrentStep(name, instruction, iconID, location, currentProgress.distanceRemaining(), currentProgress.durationRemaining())
            }
        }
    }

    fun startNavigation(origin: Point, destination: Point, onSelectedRoute: (directionRoute: DirectionsRoute) -> Unit) {
        NavigationRoute.builder()
                .accessToken(BuildConfig.MAP_BOX_API_ACCESS_TOKEN)
                .profile("walking")
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(object : Callback<DirectionsResponse> {
                    override fun onResponse(call: Call<DirectionsResponse>?, response: Response<DirectionsResponse>?) {
                        val directionRoute = response!!.body()!!.routes()[0]
                        onSelectedRoute(directionRoute)
                        navigation!!.startNavigation(directionRoute)
                    }

                    override fun onFailure(call: Call<DirectionsResponse>?, t: Throwable?) {
                    }
                })
    }
}