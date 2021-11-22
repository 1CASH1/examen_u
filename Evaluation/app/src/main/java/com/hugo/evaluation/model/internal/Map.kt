//package com.hugo.evaluation.model.internal
//
//import android.annotation.SuppressLint
//import com.google.android.gms.maps.CameraUpdate
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.model.LatLng
//
//class Map: OnMapReadyCallback {
//    private lateinit var mMap: GoogleMap
//
//    @SuppressLint("MissingPermission")
//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        mMap!!.isMyLocationEnabled = true;
//
//    }
//
//    fun putLocation(){
//        var cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18F)
//        mMap!!.moveCamera(cameraUpdate)
//        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
//
//    }
//
//}