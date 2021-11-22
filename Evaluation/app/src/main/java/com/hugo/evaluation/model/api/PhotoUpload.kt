package com.hugo.evaluation.model.api

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.view.adapter.Imagenes

//se crea la clase encargada de subir la informacion la cual recibe al presenter para despues
//llamar a la vista
class PhotoUpload(var presenterPhoto: InterfacePhoto.PhotoPresenter) :
    InterfacePhoto.PhotoUploadModel {
    //se crea una variable global que contrandra las url de las imagenes optienidas
    private lateinit var urls: MutableList<Imagenes>

    //se crea una vaiable globlar para instanciar al firebase
    private var storageReference: StorageReference? = null

    //se crea una variable de tipo base de datos para que contenga al firebase
    private val database = Firebase.database

    override fun photo(images: MutableList<Imagenes>) {
        //se asignan las urls a la variable globar para se modificadas en otro metodo
        urls = images
        //se crea la referencia de firebase
        storageReference = FirebaseStorage.getInstance().reference
        //se recorre el arreglo
        for (item in urls) {
            //se procede a solicitar el meotodo q subira las fotos
            photoUpload(item)
        }
    }

    // Funcion para subir la imagen
    override fun photoUpload(item: Imagenes) {
        //Se optiene la uri de la iamgen
        val FileUri = item.uri
        //Se crea la referencia de almacenamiento
        val Folder: StorageReference =
            FirebaseStorage.getInstance().getReference().child("User")
        //Se crea la ruta de y nombre de alchivo a guardar
        val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
        //SE procede a realizar el amacenamiento de la imagen s
        file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
            file_name.getDownloadUrl().addOnSuccessListener { uri ->
                //si se concreto la subida se procede a eliminar el item
                urls.remove(item)
                //Se actualiza la vista
                presenterPhoto.showPhoto(urls)
            }
        }
    }
}