package com.hugo.evaluation.interactor

import androidx.fragment.app.FragmentActivity
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.model.api.PhotoUpload
import com.hugo.evaluation.model.internal.PhotoPermision
import com.hugo.evaluation.model.internal.PhotoPhone
import com.hugo.evaluation.view.adapter.Imagenes

class PhotoInteractor( presenterPhoto: InterfacePhoto.PhotoPresenter,activity: FragmentActivity): InterfacePhoto.PhotoInteractor{
    private val photoModel: InterfacePhoto.PhotoModel = PhotoPhone(presenterPhoto)
    private val photoPermission: InterfacePhoto.PhotoModeloPermision = PhotoPermision(presenterPhoto,activity )
    private val photoUpload: InterfacePhoto.PhotoUploadModel = PhotoUpload(presenterPhoto )

    override fun getPhoto(images: MutableList<Imagenes>) {
        photoModel.getPhoto(images)
    }

    override fun getPermisionGallery() {
        photoPermission.getPermisionGallery()
    }

    override fun getPermisionCamera() {
        photoPermission.getPermisionCamera()
    }

    override fun photo(images: MutableList<Imagenes>) {
        photoUpload.photo(images)
    }

}