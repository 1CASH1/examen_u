package com.hugo.evaluation.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import javax.inject.Singleton

//Se solicitan los permisos de internet para evaluar su se puede ralizar la actividades
@Singleton
class CheckNetwork(var context: Context) {
    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //se toma el valor del connectivity manager para verificar que se tenga red
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                //en caso de que la app que menor a 22 el sdk
                return try {
                    if (connectivityManager.activeNetworkInfo == null) {
                        false
                    } else {
                        connectivityManager.activeNetworkInfo?.isConnected!!
                    }
                } catch (e: Exception) {
                    false
                }
            }
        //Se evalua el objeto que verifica si se cuenta o no con el servicio de internet.
        if (capabilities != null) {
            //Se retorna un verdadero si existe conexon
            //
            //Se evalua el tipo de coneccion a la q se tiene acceso, no es necesario ya
            //que cone que el obejto sea diferente de nullo es suficiente
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        //En caso de no existir conexion se regresa un falso
        return false
    }
}