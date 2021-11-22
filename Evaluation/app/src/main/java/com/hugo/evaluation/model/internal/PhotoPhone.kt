package com.hugo.evaluation.model.internal

import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes

class PhotoPhone(var presenterPhoto: InterfacePhoto.PhotoPresenter):InterfacePhoto.PhotoModel {
    override fun getPhoto(images: MutableList<Imagenes>) {
        presenterPhoto.showPhoto(images)
    }

}