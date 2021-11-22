package com.hugo.evaluation.model.internal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacesMap

class MapPermision(var mapPrecenter: InterfacesMap.MapPrecenter, var activity: FragmentActivity) :
    InterfacesMap.MapModeloPermision {
    //se crea la instacia para pedir el permos de la camara
    private var PERMISION_MAP = 1101

    //se solicitan los permosos para la ubicacion
    override fun getPermision() {
        if (checkPermision()) {
            if (isLocationEnable()) {
                //en caso de existir se informa que todo es correcto
                mapPrecenter.confirmPermision(true)
            } else {
                //en caso de rechazar los permosos se encia un mensaje al usuario
                mapPrecenter.showError("Activa tu servicio de Ubicacion por Favor")
            }

        } else {
            //ya que se caselaron los primeros permosis se ocupa un nuevo metodo
            requestPermission()
        }
    }

    private fun checkPermision(): Boolean {
        //validamos que los permisis existan
        if (ActivityCompat
                .checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            //en de estar aceptados retornamos true
            return true

        }
        //en caso de no retornamos false
        return false
    }

    //se solicitan los permosos
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISION_MAP
        )

    }

    //valdamos que los permosos existan y retornamos el boleando correspodiente a tu estatus
    private fun isLocationEnable(): Boolean {
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}