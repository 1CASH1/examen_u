package com.hugo.evaluation.interfaces

import android.location.Location
//Se crea una interfas global
interface InterfacesMap {
    //Se crea la interfas para la vista que llevara la view
    interface MapView {
        fun getLocation()
        fun showLocatio(location: Location)
        fun getPermision()
        fun confirmPermision(permision: Boolean)
        fun showError(messenger: String)
    }

    //se creal los metodos para el presente por medio de una intefaz
    interface MapPrecenter {
        fun getLocation()
        fun showLocatio(location: Location)
        fun getPermision()
        fun confirmPermision(permision: Boolean)
        fun showError(messenger: String)
    }

    //Se creal los metodos que se van a heredar en el interactor
    interface MapInteractor {
        fun getLocation()
        fun getPermision()
        //fun getService()
    }

    //se crean los metodos para heredar en en el modelo que traera la ubicacion
    interface MapModeloLocation {
        fun getLocation()
        fun getNetworkLocation()
    }

    ////Se crea el modelo encargado de solicitar los perosos para el mapa
    interface MapModeloPermision {
        fun getPermision()
    }
}