package com.hugo.evaluation.interactor

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.model.api.PhotoUpload
import com.hugo.evaluation.model.internal.PhotoPermision
import com.hugo.evaluation.model.internal.PhotoPhone
import com.hugo.evaluation.view.adapter.Imagenes
import javax.inject.Singleton

//Se crea el interactor para poder conectarse con el modelo
class PhotoInteractor(presenterPhoto: InterfacePhoto.PhotoPresenter, activity: FragmentActivity) :
    InterfacePhoto.PhotoInteractor {
    //Se crean las variables globales que accederan a los diferentes modelos
    @Singleton
    private val photoModel: InterfacePhoto.PhotoModel = PhotoPhone(presenterPhoto)
    @Singleton
    private val photoPermission: InterfacePhoto.PhotoModeloPermision =
        PhotoPermision(presenterPhoto, activity)
    @Singleton
    private val photoUpload: InterfacePhoto.PhotoUploadModel = PhotoUpload(presenterPhoto)

    //Se envian las fotografias tomadas
    override fun getPhoto(images: MutableList<Imagenes>) {
        photoModel.getPhoto(images)
    }

    //Se optienen los permisis para la galeria de imagenes
    override fun getPermisionGallery() {
        photoPermission.getPermisionGallery()
    }

    //Se solicitan los permisos de la camara
    override fun getPermisionCamera() {
        photoPermission.getPermisionCamera()
    }

    override fun photo(images: MutableList<Imagenes>) {
        photoUpload.photo(images)
    }

}