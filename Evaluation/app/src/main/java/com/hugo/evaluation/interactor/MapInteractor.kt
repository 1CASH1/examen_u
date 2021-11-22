package com.hugo.evaluation.interactor

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacesMap
import com.hugo.evaluation.model.internal.MapLocation

class MapInteractor(var mapPrecenter: InterfacesMap.MapPrecenter, activity: FragmentActivity): InterfacesMap.MapInteractor {

    val mapModel:InterfacesMap.MapModelo = MapLocation(mapPrecenter,activity)

    override fun getLocation() {
        mapModel.getLocation()
    }
}