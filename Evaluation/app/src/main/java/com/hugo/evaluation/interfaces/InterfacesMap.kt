package com.hugo.evaluation.interfaces

import android.location.Location

interface InterfacesMap {
    interface MapView{
        fun getLocation()
        fun showLocatio(location: Location)
    }
    interface MapPrecenter{
        fun getLocation()
        fun showLocatio(location: Location)
    }
    interface MapInteractor{
        fun getLocation()
    }
    interface MapModelo{
        fun getLocation()
        fun getNetworkLocation()
    }
}