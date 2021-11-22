package com.hugo.evaluation.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.location.*
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
import com.hugo.evaluation.view.service.LocationService
import com.hugo.evaluation.presenter.MapPresenter
import java.util.*

//const val channelId = "canal_notificacion"
//const val Channelname = "com.hugo.evaluation"
class MapFragment : Fragment(R.layout.fragment_map), InterfacesMap.MapView , OnMapReadyCallback, LocationListener {

    private lateinit var mapPresenter: InterfacesMap.MapPrecenter
    private lateinit var binding: FragmentMapBinding
    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        mapPresenter = MapPresenter(this,requireActivity())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.btBuscar.setOnClickListener{
            getPermision()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mMap = googleMap
            mMap!!.isMyLocationEnabled = true;

        }catch (e: Exception){

        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = MapFragment()
    }

    private fun getCityName(lat:Double,long: Double):String{
        var cityName = ""
        var geoCoder = Geocoder(this.context, Locale.getDefault())
        var adress = geoCoder.getFromLocation(lat,long,1)
        cityName = adress.get(0).locality
        return cityName
    }

    private fun getcountryName(lat:Double,long: Double):String{
        var countryName = ""
        var geoCoder = Geocoder(this.context, Locale.getDefault())
        var adress = geoCoder.getFromLocation(lat,long,1)
        countryName = adress.get(0).countryName
        return countryName

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        Log.d("TAG", String.format("Nueva ubicación: (%s, %s)",location?.getLatitude(), location?.getLongitude()));
    }

    override fun getLocation() {
        mapPresenter.getLocation()
    }

    override fun showLocatio(location:Location) {
        var cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18F)
        mMap!!.moveCamera(cameraUpdate)
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun getPermision() {
        mapPresenter.getPermision()
    }

    override fun confirmPermision(permision: Boolean) {
        if(permision){
            getLocation()
            ContextCompat.startForegroundService(requireActivity(),
                Intent(requireActivity(), LocationService::class.java)
            )
        }
    }

    override fun showError(messenger: String) {
        Toast.makeText(this.context,messenger, Toast.LENGTH_LONG).show()
    }

//    override fun getService() {
//        mapPresenter.getPermision()
//    }
//
//    override fun showPushNotification() {
//        Toast.makeText(this.context,"yes", Toast.LENGTH_LONG).show()
//    }
}