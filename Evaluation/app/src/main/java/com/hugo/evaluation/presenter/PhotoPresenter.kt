package com.hugo.evaluation.presenter

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interactor.PhotoInteractor
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes

class PhotoPresenter(var viewPhoto: InterfacePhoto.PhotoView, activity: FragmentActivity): InterfacePhoto.PhotoPresenter  {
    private var photoInteractor: InterfacePhoto.PhotoInteractor = PhotoInteractor(this,activity)
    override fun getPhoto(images: MutableList<Imagenes>) {
        photoInteractor.getPhoto(images)
    }

    override fun showPhoto(images: MutableList<Imagenes>) {
        viewPhoto.showPhoto(images)
    }

    override fun getPermisionGallery() {
        photoInteractor.getPermisionGallery()
    }

    override fun getPermisionCamera() {
        photoInteractor.getPermisionCamera()
    }

    override fun confirmPermisionGallery(permision: Boolean) {
        viewPhoto.confirmPermisionGallery(permision)
    }

    override fun confirmPermisionCamera(permision: Boolean) {
        viewPhoto.confirmPermisionCamera(permision)
    }

    override fun photo(images: MutableList<Imagenes>) {
        photoInteractor.photo(images)
    }

    override fun showError(messenger: String) {
        viewPhoto.showError(messenger)
    }
}