package com.hugo.evaluation.presenter

import android.location.Location
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interactor.MapInteractor
import com.hugo.evaluation.interfaces.InterfacesMap
//presente que se encarga en realizar las periciones de la vista
class MapPresenter(var mapView: InterfacesMap.MapView, activity: FragmentActivity) :
    InterfacesMap.MapPrecenter {
    //Se crea un objeto del interacto para que este sea el que defina las solicitudes a realizar entre el modelo y el presenter
    private var mapIterator: InterfacesMap.MapInteractor = MapInteractor(this, activity)

    //metodo para solicitar la ubicacion
    override fun getLocation() {
        //se realiza dicha solciitud al interactorr
        mapIterator.getLocation()
    }

    //metodo para mostrar la ubicacion
    override fun showLocatio(location: Location) {
        //se solcicita mostrar la informacion al la vista
        mapView.showLocatio(location)
    }

    //metodo para solcitar los permosos
    override fun getPermision() {
        //se solcitan ls permosos al interactor
        mapIterator.getPermision()
    }

    //metodo para ver el estado de los permosos
    override fun confirmPermision(permision: Boolean) {
        //se manda el estatus a la vista
        mapView.confirmPermision(permision)
    }

    ///metodo para mostrar el error
    override fun showError(messenger: String) {
        //se manda a mostrar los errores a la vista
        mapView.showError(messenger)
    }

}