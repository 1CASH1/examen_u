package com.hugo.evaluation.interfaces

import com.hugo.evaluation.model.api.JsonTMDB
import com.hugo.evaluation.view.adapter.Imagenes
import retrofit2.Retrofit

interface InterfacePhoto {
    interface PhotoView{
        fun getPhoto()
        fun showPhoto(images: MutableList<Imagenes>)
        fun getPermisionGallery()
        fun getPermisionCamera()
        fun confirmPermisionCamera(permision: Boolean)
        fun confirmPermisionGallery(permision: Boolean)
        fun showError(messenger: String)
    }
    interface PhotoPresenter{
        fun getPhoto()
        fun showPhoto(images: MutableList<Imagenes>)
        fun getPermisionGallery()
        fun getPermisionCamera()
        fun confirmPermisionGallery(permision: Boolean)
        fun confirmPermisionCamera(permision: Boolean)
        fun showError(messenger: String)
    }
    interface PhotoInteractor{
        fun getPhoto()
        fun getPermisionGallery()
        fun getPermisionCamera()
    }
    interface PhotoModel{
        fun getPhoto()
    }
    interface PhotoUploadModel{
        fun photo(images: MutableList<Imagenes>)
//        fun photoUpload(item: Imagenes)
    }
    interface PhotoModeloPermision{
        fun getPermisionGallery()
        fun getPermisionCamera()
    }

}