package com.hugo.evaluation.interactor

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.model.internal.PhotoPermision
import com.hugo.evaluation.model.internal.PhotoPhone

class PhotoInteractor(var presenterPhoto: InterfacePhoto.PhotoPresenter,activity: FragmentActivity): InterfacePhoto.PhotoInteractor{
    private val photoModel: InterfacePhoto.PhotoModel = PhotoPhone(presenterPhoto)
    private val photoPermission: InterfacePhoto.PhotoModeloPermision = PhotoPermision(presenterPhoto,activity )

    override fun getPhoto() {
        photoModel.getPhoto()
    }

    override fun getPermisionGallery() {
        photoPermission.getPermisionGallery()
    }

    override fun getPermisionCamera() {
        photoPermission.getPermisionCamera()
    }

}