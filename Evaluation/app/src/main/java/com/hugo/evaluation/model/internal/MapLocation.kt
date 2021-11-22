package com.hugo.evaluation.model.internal

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.*
import com.hugo.evaluation.interfaces.InterfacesMap
//Se crea el presenter del mapa para traer la locacion
class MapLocation(var mapPrecenter: InterfacesMap.MapPrecenter, var activity: FragmentActivity) :
    InterfacesMap.MapModeloLocation {
    //Se crea las variables locales que nos ayudaran a implementar los metodos para obtener la ubicacion
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    //Se crea el metodo para otener la ubicacion y se suprime su recomendacion
    @SuppressLint("MissingPermission")
    override fun getLocation() {
        //Se intacian las varianles locales
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            //se asignal la respuesta de la ubicacion
            var location: Location? = task.result
            ///Evalua si no tiene nada
            if (location == null) {
                //en caso de que este sin nada se solicita la ultima ubicacion
                getNetworkLocation()
            } else {
                //en caso de optiener una ubicacion actual se actualiza la vista
                mapPrecenter.showLocatio(location)
            }
        }
    }

    //se solicita la ultima referencia  ubicacion
    @SuppressLint("MissingPermission")
    override fun getNetworkLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()

        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            //se solicita la ultima ubicacion y se asigna
            var lastLocation: Location = p0.lastLocation
            //se atualiza la vista
            mapPrecenter.showLocatio(lastLocation)
        }
    }
}