package com.navirice.android.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerMode
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.telemetry.location.LocationEngine
import com.mapbox.services.android.telemetry.location.LocationEngineListener
import com.mapbox.services.android.telemetry.location.LocationEnginePriority
import com.mapbox.services.android.telemetry.location.LostLocationEngine
import com.navirice.android.BuildConfig
import com.navirice.android.R
import com.navirice.android.models.Location
import com.navirice.android.models.Step
import com.navirice.android.services.NavigationService
import com.navirice.android.services.realTimeDataServices.StepService


/**
 * @author Harry Liu
 * @version Feb 18, 2018
 */

class NavigationActivity : AppCompatActivity(), LocationEngineListener {
    private var mNavigationService: NavigationService? = null
    private var mMapView: MapView? = null
    private var map: MapboxMap? = null
    private var locationPlugin: LocationLayerPlugin? = null
    private var locationEngine: LocationEngine? = null
    private var origin: Location? = null
    private var destination: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val sourceLatitude = intent.getDoubleExtra(SOURCE_LATITUDE, 0.0)
        val sourceLongitude = intent.getDoubleExtra(SOURCE_LONGITUDE, 0.0)
        val destinationLatitude = intent.getDoubleExtra(DESTINATION_LATITUDE, 0.0)
        val destinationLongitude = intent.getDoubleExtra(DESTINATION_LONGITUDE, 0.0)


        origin = Location(sourceLongitude, sourceLatitude)
        destination = LatLng(destinationLatitude, destinationLongitude)

        Mapbox.getInstance(this, BuildConfig.MAP_BOX_API_ACCESS_TOKEN)
        mMapView = findViewById(R.id.map_view)
        mMapView!!.onCreate(savedInstanceState)

        mMapView!!.getMapAsync { mapboxMap ->
            map = mapboxMap
            mapboxMap.addMarker(MarkerOptions().position(destination))

            initializeLocationEngine()
            enableLocationPlugin()

            val originPoint = Point.fromLngLat(sourceLongitude, sourceLatitude)
            val destinationPoint = Point.fromLngLat(destinationLongitude, destinationLatitude)

            mNavigationService = NavigationService(this, locationEngine!!)
            mNavigationService!!.addProgressChangeListener { name, instruction, iconID, location, distanceRemaining, durationRemaining ->

                val step = Step(name, instruction, iconID, location)
                StepService.updateCurrentStep(this, step)
            }
            mNavigationService!!.startNavigation(originPoint, destinationPoint, { currentRoute ->
                val navigationMapRoute = NavigationMapRoute(null, mMapView!!, map!!, R.style.NavigationMapRoute)
                navigationMapRoute.addRoute(currentRoute)
            })
        }
    }

    private fun enableLocationPlugin() {
        try {
            locationPlugin = LocationLayerPlugin(mMapView!!, map!!, locationEngine!!)
            locationPlugin!!.setLocationLayerEnabled(LocationLayerMode.TRACKING)
        } catch (e: SecurityException) {

        }
    }

    private fun initializeLocationEngine() {
        locationEngine = LostLocationEngine(this)
        locationEngine!!.addLocationEngineListener(this)
        locationEngine!!.priority = LocationEnginePriority.HIGH_ACCURACY
        locationEngine!!.activate()
    }

    private fun setCameraPosition(location: android.location.Location) {
        map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude), 16.0))
    }

    override fun onStart() {
        super.onStart()
        mMapView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        mMapView!!.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView!!.onSaveInstanceState(outState)
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

    override fun onLocationChanged(location: android.location.Location?) {
        setCameraPosition(location!!)
    }

    override fun onConnected() {
        try {
            locationEngine!!.requestLocationUpdates()
        } catch (e: SecurityException) {

        }
    }
}
