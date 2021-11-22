package com.hugo.evaluation.interactor

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacesMap
import com.hugo.evaluation.model.internal.MapLocation
import com.hugo.evaluation.model.internal.MapPermision
import javax.inject.Singleton

//Se crea el inspector para poder la localidad y permisos para el funcionamiento del fracment
class MapInteractor(mapPrecenter: InterfacesMap.MapPrecenter, val activity: FragmentActivity) :
    InterfacesMap.MapInteractor {
    //Variables globales para poder acceder a los molelos de los permisos y ubicacion
    @Singleton
    private val mapModel: InterfacesMap.MapModeloLocation = MapLocation(mapPrecenter, activity)
    @Singleton
    private val mapPermision: InterfacesMap.MapModeloPermision =
        MapPermision(mapPrecenter, activity)

    //Metodo Herdado para traer la informacion de la ubicacion
    override fun getLocation() {
        mapModel.getLocation()
    }

    //Valores para traer los permosos
    override fun getPermision() {
        mapPermision.getPermision()
    }

}