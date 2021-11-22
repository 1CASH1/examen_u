package com.hugo.evaluation.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentPhotoBinding
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.presenter.PhotoPresenter
import com.hugo.evaluation.view.adapter.AdapterGaleria
import com.hugo.evaluation.view.adapter.Imagenes
import dagger.Provides
import java.io.ByteArrayOutputStream
import javax.inject.Singleton

class PhotoFragment : Fragment(R.layout.fragment_photo), InterfacePhoto.PhotoView {
    //variables globales del presenter y el bindin
    @Singleton
    private lateinit var binding: FragmentPhotoBinding
    @Singleton
    private lateinit var photoPresenter: PhotoPresenter

    //variables globales para el adaptador y urls
    private lateinit var adapterGaleria: AdapterGaleria
    private lateinit var urls: MutableList<Imagenes>

    //variables gobales para permosos
    var PICK_IMAGE_MULTIPLE = 1
    private val cameraRequest = 1888

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoBinding.bind(view)
        photoPresenter = PhotoPresenter(this, requireActivity())

        urls = mutableListOf()
        adapterGaleria = AdapterGaleria(this.context, urls)
        binding.gvGaleria.adapter = adapterGaleria
        //botones para solcitar llas imagenes para galerias, camara y subir las fotos
        binding.btGaleria.setOnClickListener { getPermisionGallery() }
        binding.btCamera.setOnClickListener { getPermisionCamera() }
        binding.btSubir.setOnClickListener { photo(urls) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotoFragment()
    }

    //obener los resultados de las imagenes de la camara y la galeria
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {

            if (requestCode === PICK_IMAGE_MULTIPLE && resultCode === Activity.RESULT_OK && null != android.R.attr.data) {

                if (data!!.getClipData() != null) {
                    val count = data!!.clipData!!.itemCount
                    if (count > 0) {
                        for (i in 0 until count) {
                            urls.add(Imagenes(data.clipData!!.getItemAt(i).uri))
                        }
                    }
                } else {
                    val imageUri = data?.data
                    urls.add(Imagenes(imageUri))
                }
                getPhoto(urls)
            } else if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                urls.add(Imagenes(getImageUri(photo)))
                getPhoto(urls)
            } else {
                Toast.makeText(
                    this.context, "You haven't picked Image",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_LONG)
                .show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //almacenar la imagen de manera temporal para despues subirla
    private fun getImageUri(bitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val rnds = (1000..100000).random()
        val path = MediaStore.Images.Media.insertImage(
            this.context?.contentResolver,
            bitmap,
            rnds.toString(),
            null
        )
        return Uri.parse(path)
    }

    //opttener las fotos a subir
    override fun getPhoto(images: MutableList<Imagenes>) {
        photoPresenter.getPhoto(urls)
    }

    //mostrar las fotos a subir
    override fun showPhoto(images: MutableList<Imagenes>) {
        for (item in images) {
            Log.e("PUEBB", item.uri.toString())
        }
        urls = if (images.size > 0) {
            images
        } else mutableListOf()
        adapterGaleria.notifyDataSetChanged()
    }

    //solcitar permosos de galeria
    override fun getPermisionGallery() {
        photoPresenter.getPermisionGallery()
    }

    //solcitar camara
    override fun getPermisionCamera() {
        photoPresenter.getPermisionCamera()
    }

    //solcitar permosos de camara
    override fun confirmPermisionCamera(permision: Boolean) {
        if (permision) {
            selectPhotoCamara()
        }
    }

    //Método que ingresa a la galería de nuestro dispositivo
    private fun selectPhotoCamara() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)

    }

    //confirmar permosos de galeria
    override fun confirmPermisionGallery(permision: Boolean) {
        if (permision) {
            selectPhotoGallery()
        }
    }
    //Método que ingresa a la galería de nuestro dispositivo
    private fun selectPhotoGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    //Mostrar errores
    override fun showError(messenger: String) {
        Toast.makeText(this.context, messenger, Toast.LENGTH_LONG).show()
    }

    //enviar imagenes
    override fun photo(images: MutableList<Imagenes>) {
        photoPresenter.photo(images)
    }
}