package com.hugo.evaluation.model.internal

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacePhoto
//se crea la clase correspondiente para tomar los permisos
class PhotoPermision(
    private var presenterPhoto: InterfacePhoto.PhotoPresenter,
    val activity: FragmentActivity
) : InterfacePhoto.PhotoModeloPermision {
    //Se crean las variables que tendran el numero de identificador de nuestras peticiones para los permisos
    private var PERMISION_GALLERY = 1105
    private var PERMISION_CAMERA = 1111

    //Optener permosos de galeria
    override fun getPermisionGallery() {
        //evaluar que los permosos ya esten activos
        if (checkPermisionGallery()) {
            //en caso de que estos ya esten lsitos se notifica a la vista
            presenterPhoto.confirmPermisionGallery(true)
        } else {
            //en caso contrario se solicitan
            requestPermissionGallery()
        }
    }

    //Veriricar los permosos de galeria
    private fun checkPermisionGallery(): Boolean {
        //Se implemnta la sigueinte comparacion para saber si los permisos estan otorgados
        if (ActivityCompat
                .checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            //En caso de ser afirmativo se regresa un true
            return true
        }
        //En caso de ser negativo un false
        return false
    }

    //se solciitan los permisos
    private fun requestPermissionGallery() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISION_GALLERY
        )
    }

    //funcion para obtener los permisos de la camara
    override fun getPermisionCamera() {
        //se validan los permosos de la camara
        if (checkPermisionCamera()) {
            //en caso de ser correcto se informa a la vista
            presenterPhoto.confirmPermisionCamera(true)
        } else {
            //en caso de no tener permosos se solicitan
            requestPermissionCamera()
        }
    }

    //Se validan los permosos de la camara
    private fun checkPermisionCamera(): Boolean {
        //se realiza una evaluacion de si los permosos se encuentran activos
        if (ActivityCompat
                .checkSelfPermission(activity, android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            //en caso de ser afirmativo se regresa un true
            return true
        }
        //en caso de no contar cn ellos se regresa un falso
        return false
    }

    //se requieren los respectivos permosos para la camara
    private fun requestPermissionCamera() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            PERMISION_CAMERA
        )
    }
}