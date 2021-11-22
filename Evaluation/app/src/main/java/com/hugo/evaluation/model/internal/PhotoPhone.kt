package com.hugo.evaluation.model.internal

import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes
//Se crea un na calse que simule la solcitud de informacion de manera externa
class PhotoPhone(var presenterPhoto: InterfacePhoto.PhotoPresenter) : InterfacePhoto.PhotoModel {
    //Se obtienen las imagenes
    override fun getPhoto(images: MutableList<Imagenes>) {
        //al no contar con una estrucutra de persistencia de informacion
        presenterPhoto.showPhoto(images)
    }

}