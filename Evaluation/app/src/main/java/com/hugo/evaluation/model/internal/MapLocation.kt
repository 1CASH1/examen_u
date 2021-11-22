package com.hugo.evaluation.model.internal

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.*
import com.hugo.evaluation.interfaces.InterfacesMap

class MapLocation(var mapPrecenter: InterfacesMap.MapPrecenter, var activity: FragmentActivity): InterfacesMap.MapModeloLocation {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    @SuppressLint("MissingPermission")
    override fun getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task->
            var location: Location? = task.result
            if (location == null){
                getNetworkLocation()
            }else{
                mapPrecenter.showLocatio(location)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun getNetworkLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()

        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location = p0.lastLocation
            mapPrecenter.showLocatio(lastLocation)
           // ContextCompat.startForegroundService(activity,
            //    Intent(activity, LocationService::class.java))
        }
    }
}