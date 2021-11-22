package com.hugo.evaluation.presenter

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interactor.MapInteractor
import com.hugo.evaluation.interfaces.InterfacesMap

class MapPresenter(var mapView: InterfacesMap.MapView, activity: FragmentActivity): InterfacesMap.MapPrecenter {
    private var mapIterator: InterfacesMap.MapInteractor = MapInteractor(this, activity)

    override fun getLocation() {
        mapIterator.getLocation()
    }

    override fun showLocatio(location: Location) {
        mapView.showLocatio(location)
    }

    override fun getPermision() {
        mapIterator.getPermision()
    }

    override fun confirmPermision(permision: Boolean) {
        mapView.confirmPermision(permision)
    }

    override fun showError(messenger: String) {
        mapView.showError(messenger)
    }

}