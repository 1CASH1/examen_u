package com.hugo.evaluation.model.internal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacesMap

class MapPermision(var mapPrecenter: InterfacesMap.MapPrecenter, var activity: FragmentActivity): InterfacesMap.MapModeloPermision {
    private var PERMISION_MAP = 1101

    override fun getPermision() {
        if(checkPermision()){
            if (isLocationEnable()){
                mapPrecenter.confirmPermision(true)
            }else{
                mapPrecenter.showError("Activa tu servicio de Ubicacion por Favor")
            }

        }else{
            requestPermission()
        }
    }

    private fun checkPermision():Boolean{
        if(ActivityCompat
                .checkSelfPermission(activity,android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            return true

        }
        return false
    }

    private fun requestPermission(){
         ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISION_MAP
        )

    }



    private fun isLocationEnable():Boolean{
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

}