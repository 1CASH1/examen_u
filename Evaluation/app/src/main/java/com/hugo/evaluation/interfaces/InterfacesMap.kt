package com.hugo.evaluation.interfaces

import android.location.Location

interface InterfacesMap {
    interface MapView{
        fun getLocation()
        fun showLocatio(location: Location)
        fun getPermision()
        fun confirmPermision(permision: Boolean)
        fun showError(messenger: String)
    }
    interface MapPrecenter{
        fun getLocation()
        fun showLocatio(location: Location)
        fun getPermision()
        fun confirmPermision(permision: Boolean)
        fun showError(messenger: String)
    }
    interface MapInteractor{
        fun getLocation()
        fun getPermision()
        //fun getService()
    }
    interface MapModeloLocation{
        fun getLocation()
        fun getNetworkLocation()
    }
    interface MapModeloPermision{
        fun getPermision()
    }
//    interface MapModeloService
}