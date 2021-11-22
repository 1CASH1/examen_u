package com.hugo.evaluation.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentMapBinding
import com.hugo.evaluation.interfaces.InterfacesMap
import com.hugo.evaluation.presenter.MapPresenter
import com.hugo.evaluation.view.service.LocationService
import javax.inject.Singleton

//const val channelId = "canal_notificacion"
//const val Channelname = "com.hugo.evaluation"
class MapFragment : Fragment(R.layout.fragment_map), InterfacesMap.MapView, OnMapReadyCallback,
    LocationListener {

    //variables de tipo global
    @Singleton
    private lateinit var mapPresenter: InterfacesMap.MapPrecenter
    @Singleton
    private lateinit var binding: FragmentMapBinding
    @Singleton
    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //inicializando variable de bindin, presnter y el mapa
        binding = FragmentMapBinding.bind(view)
        mapPresenter = MapPresenter(this, requireActivity())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        //optieniendo el contexto para que se muestre el mapa
        mapFragment.getMapAsync(this)
        //boton de busquedas
        binding.btBuscar.setOnClickListener {
            getPermision()
        }
    }

    //carga e inicializacion del mapa
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mMap = googleMap
            mMap!!.isMyLocationEnabled = true;

        } catch (e: Exception) {

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }

    //Cambio de unicacion
    override fun onLocationChanged(location: Location) {
        Log.d(
            "TAG",
            String.format(
                "Nueva ubicación: (%s, %s)",
                location.latitude,
                location.longitude
            )
        )
    }

    //optner ubicacion
    override fun getLocation() {
        mapPresenter.getLocation()
    }

    //actuilizar mapa
    override fun showLocatio(location: Location) {
        var cameraUpdate: CameraUpdate =
            CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 18F)
        mMap!!.moveCamera(cameraUpdate)
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    //solcitud de permosos
    override fun getPermision() {
        mapPresenter.getPermision()
    }

    //confirmacion de permosos
    override fun confirmPermision(permision: Boolean) {
        if (permision) {
            getLocation()
            ContextCompat.startForegroundService(
                requireActivity(),
                Intent(requireActivity(), LocationService::class.java)
            )
        }
    }

    //mostrar errores
    override fun showError(messenger: String) {
        Toast.makeText(this.context, messenger, Toast.LENGTH_LONG).show()
    }
}