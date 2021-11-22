package com.hugo.evaluation.interfaces

import com.hugo.evaluation.view.adapter.Imagenes

interface InterfacePhoto {
    interface PhotoView {
        fun getPhoto(images: MutableList<Imagenes>)
        fun showPhoto(images: MutableList<Imagenes>)
        fun getPermisionGallery()
        fun getPermisionCamera()
        fun confirmPermisionCamera(permision: Boolean)
        fun confirmPermisionGallery(permision: Boolean)
        fun showError(messenger: String)
        fun photo(images: MutableList<Imagenes>)
    }

    interface PhotoPresenter {
        fun getPhoto(images: MutableList<Imagenes>)
        fun showPhoto(images: MutableList<Imagenes>)
        fun getPermisionGallery()
        fun getPermisionCamera()
        fun confirmPermisionGallery(permision: Boolean)
        fun confirmPermisionCamera(permision: Boolean)
        fun photo(images: MutableList<Imagenes>)
        fun showError(messenger: String)
    }

    interface PhotoInteractor {
        fun getPhoto(images: MutableList<Imagenes>)
        fun getPermisionGallery()
        fun getPermisionCamera()
        fun photo(images: MutableList<Imagenes>)
    }

    interface PhotoModel {
        fun getPhoto(images: MutableList<Imagenes>)
    }

    interface PhotoUploadModel {
        fun photo(images: MutableList<Imagenes>)
        fun photoUpload(item: Imagenes)
    }

    interface PhotoModeloPermision {
        fun getPermisionGallery()
        fun getPermisionCamera()
    }

}