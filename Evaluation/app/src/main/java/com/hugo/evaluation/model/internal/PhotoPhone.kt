package com.hugo.evaluation.model.internal

import com.hugo.evaluation.interfaces.InterfacePhoto

class PhotoPhone(var presenterPhoto: InterfacePhoto.PhotoPresenter):InterfacePhoto.PhotoModel {
    override fun getPhoto() {
        presenterPhoto.showPhoto(mutableListOf())
    }

}