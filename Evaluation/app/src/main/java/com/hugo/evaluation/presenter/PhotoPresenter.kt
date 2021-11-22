package com.hugo.evaluation.presenter

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interactor.PhotoInteractor
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes
//presenter que es el intermediario entre la vista y el interactor
class PhotoPresenter(var viewPhoto: InterfacePhoto.PhotoView, activity: FragmentActivity) :
    InterfacePhoto.PhotoPresenter {
    //variable globlar del interactor para solcitar al modelo
    private var photoInteractor: InterfacePhoto.PhotoInteractor = PhotoInteractor(this, activity)

    //otener fotografia
    override fun getPhoto(images: MutableList<Imagenes>) {
        //solcitar al interactor la fotografia
        photoInteractor.getPhoto(images)
    }

    //mostrar la imagen
    override fun showPhoto(images: MutableList<Imagenes>) {
        //se manda a mostrar las imagenes a la vista
        viewPhoto.showPhoto(images)
    }

    //metodo par optener los permosos
    override fun getPermisionGallery() {
        //metodo para optener la galeria
        photoInteractor.getPermisionGallery()
    }

    //metodo para obtener los permosos de la camara
    override fun getPermisionCamera() {
        photoInteractor.getPermisionCamera()
    }

    //metodo para obtener los permosos de la galera
    override fun confirmPermisionGallery(permision: Boolean) {
        viewPhoto.confirmPermisionGallery(permision)
    }

    //metodo para optnener los permos de la camara
    override fun confirmPermisionCamera(permision: Boolean) {
        viewPhoto.confirmPermisionCamera(permision)
    }

    //solcitar las images para mostrar
    override fun photo(images: MutableList<Imagenes>) {
        photoInteractor.photo(images)
    }

    //mostrar errores
    override fun showError(messenger: String) {
        viewPhoto.showError(messenger)
    }
}