package com.hugo.evaluation.interactor

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacesMap
import com.hugo.evaluation.model.internal.MapLocation
import com.hugo.evaluation.model.internal.MapPermision

class MapInteractor(var mapPrecenter: InterfacesMap.MapPrecenter, activity: FragmentActivity): InterfacesMap.MapInteractor {

    val mapModel:InterfacesMap.MapModeloLocation = MapLocation(mapPrecenter,activity)
    val mapPermision:InterfacesMap.MapModeloPermision = MapPermision(mapPrecenter,activity)

    override fun getLocation() {
        mapModel.getLocation()
    }

    override fun getPermision() {
        mapPermision.getPermision()
    }
}