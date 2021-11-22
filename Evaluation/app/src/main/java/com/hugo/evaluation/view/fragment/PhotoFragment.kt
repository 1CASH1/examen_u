package com.hugo.evaluation.view.fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentPhotoBinding
import com.hugo.evaluation.interfaces.InterfacePhoto
import com.hugo.evaluation.presenter.PhotoPresenter
import com.hugo.evaluation.view.adapter.AdapterGaleria
import com.hugo.evaluation.view.adapter.Imagenes
import java.io.ByteArrayOutputStream
import java.util.HashMap

class PhotoFragment : Fragment(R.layout.fragment_photo), InterfacePhoto.PhotoView {
    private lateinit var binding: FragmentPhotoBinding
    private lateinit var photoPresenter: PhotoPresenter

    private lateinit var adapterGaleria: AdapterGaleria
    private lateinit var urls: MutableList<Imagenes>

    var PICK_IMAGE_MULTIPLE = 1
    private val cameraRequest = 1888

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoBinding.bind(view)
        photoPresenter = PhotoPresenter(this, requireActivity())

        urls = mutableListOf()
        adapterGaleria = AdapterGaleria(this.context,urls)
        binding.gvGaleria.adapter = adapterGaleria



        binding.btGaleria.setOnClickListener{getPermisionGallery()}
        binding.btCamera.setOnClickListener{getPermisionCamera()}
        binding.btSubir.setOnClickListener{ photo(urls)    }
    }

    companion object{
        @JvmStatic
        fun newInstance() = PhotoFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {

            if (requestCode === PICK_IMAGE_MULTIPLE && resultCode === Activity.RESULT_OK && null != android.R.attr.data) {
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                if (data!!.getClipData() != null) {
                    val count = data!!.clipData!!.itemCount
                    if (count>0) {
                        for (i in 0 until count) {
                            urls.add(Imagenes(data.clipData!!.getItemAt(i).uri))
                        }
                    }
                } else{
                    val imageUri = data?.data
                    urls.add(Imagenes(imageUri))
                }
                getPhoto(urls)
            } else if (requestCode == cameraRequest) {
                val photo: Bitmap = data?.extras?.get("data") as Bitmap
                urls.add(Imagenes(getImageUri(photo)))
                getPhoto(urls)
            }else {
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

    override fun getPhoto(images: MutableList<Imagenes>) {
        photoPresenter.getPhoto(urls)
    }

    override fun showPhoto(images: MutableList<Imagenes>) {
        for (item in images){
            Log.e("PUEBB",item.uri.toString() )
        }
        urls = if(images.size>0) {
            images
        }else mutableListOf()
        adapterGaleria.notifyDataSetChanged()
    }

    override fun getPermisionGallery() {
        photoPresenter.getPermisionGallery()
    }

    override fun getPermisionCamera() {
        photoPresenter.getPermisionCamera()
    }

    override fun confirmPermisionCamera(permision: Boolean) {
        if(permision){
            selectPhotoCamara()
        }
    }

    //Método que ingresa a la galería de nuestro dispositivo
    private fun selectPhotoCamara() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)

    }

    override fun confirmPermisionGallery(permision: Boolean) {
        if(permision){
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

    override fun showError(messenger: String) {
        Toast.makeText(this.context, messenger, Toast.LENGTH_LONG).show()
    }

    override fun photo(images: MutableList<Imagenes>) {
        photoPresenter.photo(images)
    }
}