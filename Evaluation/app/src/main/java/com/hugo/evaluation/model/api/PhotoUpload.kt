package com.hugo.evaluation.model.api

import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes
import java.util.HashMap
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PhotoUpload(var presenterPhoto: InterfacePhoto.PhotoPresenter): InterfacePhoto.PhotoUploadModel {

    private var storageReference: StorageReference? = null
    private val database = Firebase.database
    private val myRef = database.getReference("user")

    override fun photo(images: MutableList<Imagenes>) {
        storageReference = FirebaseStorage.getInstance().reference
        for (item in images){
            if(item.uri!= null) {
                //photoUpload(item)
            }
        }
    }

//    override fun photoUpload(item: Imagenes) {
//
//        val FileUri = item.uri
//        val Folder: StorageReference =
//            FirebaseStorage.getInstance().getReference().child("User")
//        val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
//        file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
//            file_name.getDownloadUrl().addOnSuccessListener { uri ->
//                val hashMap =
//                    HashMap<String, String>()
//                hashMap["link"] = java.lang.String.valueOf(uri)
//                myRef.setValue(hashMap)
//                Log.d("Mensaje", "Se subió correctamente")
////                urls.remove(item)
////                adapterGaleria.notifyDataSetChanged()
//            }
//        }
//    }
}