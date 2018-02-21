package com.navirice.android.activities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.mapbox.api.directions.v5.models.LegStep
import com.mapbox.geojson.Point
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.services.NavigationService


/**
 * @author Harry Liu
 * @version Feb 18, 2018
 */

class NavigationActivity : AppCompatActivity(), OnMapReadyCallback {
    var mapView: MapView? = null
    var steps: List<LegStep>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val sourceLatitude = intent.getDoubleExtra(SOURCE_LATITUDE, 0.0)
        val sourceLongitude = intent.getDoubleExtra(SOURCE_LONGITUDE, 0.0)
        val destinationLatitude = intent.getDoubleExtra(DESTINATION_LATITUDE, 0.0)
        val destinationLongitude = intent.getDoubleExtra(DESTINATION_LONGITUDE, 0.0)

        val origin = Point.fromLngLat(sourceLongitude, sourceLatitude)
        val destination = Point.fromLngLat(destinationLongitude, destinationLatitude)

        mapView = findViewById(R.id.map_view_routes)
        mapView!!.onCreate(savedInstanceState)

        val navigationService = NavigationService(this)
        navigationService.addProgressChangeListener { instruction, iconID, stepIndex, distanceRemaining, durationRemaining ->
            Log.d("addProgressChange", "$instruction $iconID $stepIndex $distanceRemaining $durationRemaining")
        }
        navigationService.startNavigation(origin, destination) { directionRoute ->
            steps = directionRoute.legs()!![0].steps()
            mapView!!.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        try {
            val locations = steps!!.map { step ->
                val location = step.maneuver().location()
                LatLng(location.latitude(), location.longitude())
            }

            googleMap!!.isMyLocationEnabled = true

            googleMap.addPolyline(PolylineOptions()
                    .addAll(locations))


            val origin = locations[0]
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 9f))

            Log.d("onMapReady", "Ready!")


        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        mapView!!.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView!!.onLowMemory()
        super.onLowMemory()
    }


    companion object {
        private const val SOURCE_LATITUDE = "source_latitude"
        private const val SOURCE_LONGITUDE = "source_longitude"
        private const val DESTINATION_LATITUDE = "destination_latitude"
        private const val DESTINATION_LONGITUDE = "destination_longitude"

        fun newIntent(context: Context, sourceLocation: Location, destinationLocation: Location): Intent {
            val intent = Intent(context, NavigationActivity::class.java)

            intent.putExtra(SOURCE_LATITUDE, sourceLocation.latitude)
            intent.putExtra(SOURCE_LONGITUDE, sourceLocation.longitude)

            intent.putExtra(DESTINATION_LATITUDE, destinationLocation.latitude)
            intent.putExtra(DESTINATION_LONGITUDE, destinationLocation.longitude)

            return intent
        }
    }
}
