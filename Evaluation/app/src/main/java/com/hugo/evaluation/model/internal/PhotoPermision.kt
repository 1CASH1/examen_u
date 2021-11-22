package com.hugo.evaluation.model.internal

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacePhoto

class PhotoPermision(var presenterPhoto: InterfacePhoto.PhotoPresenter,val activity: FragmentActivity): InterfacePhoto.PhotoModeloPermision {
    private var PERMISION_GALLERY = 1105
    private var PERMISION_CAMERA = 1111
    override fun getPermisionGallery() {
        if(checkPermisionGallery()){
            presenterPhoto.confirmPermisionGallery(true)
        }else{
            requestPermissionGallery()
        }
    }

    private fun checkPermisionGallery():Boolean{
        if(ActivityCompat
                .checkSelfPermission(activity,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED ){
            return true
        }
        return false
    }

    private fun requestPermissionGallery(){
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISION_GALLERY
        )
    }
    override fun getPermisionCamera() {
        if(checkPermisionCamera()){
             presenterPhoto.confirmPermisionCamera(true)
        }else{
            requestPermissionCamera()
        }
    }

    private fun checkPermisionCamera():Boolean{
        if(ActivityCompat
                .checkSelfPermission(activity,android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED ){
            return true
        }
        return false
    }

    private fun requestPermissionCamera(){
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            PERMISION_CAMERA
        )
    }



}