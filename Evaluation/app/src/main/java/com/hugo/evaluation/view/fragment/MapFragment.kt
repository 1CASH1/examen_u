package com.hugo.evaluation.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.hugo.evaluation.R
import com.hugo.evaluation.databinding.FragmentMapBinding
import com.hugo.evaluation.helper.Channelname
import com.hugo.evaluation.helper.channelId
import com.hugo.evaluation.view.HomeActivity
import com.hugo.evaluation.view.service.LocationService
import java.util.*

const val channelId = "canal_notificacion"
const val Channelname = "com.hugo.evaluation"
class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback, LocationListener {

    private lateinit var binding: FragmentMapBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var PERMISION_MAP = 1101
    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        binding.btBuscar.setOnClickListener{
            getLastLocation()
            generateNotification("Local","Local")



        }
    }



    fun getRemoteView(title: String, msg: String): RemoteViews {
        val remoteView = RemoteViews("com.hugo.evaluation",R.layout.item_messaging)
        remoteView.setTextViewText(R.id.tvTitleMessaging,title)
        remoteView.setTextViewText(R.id.tvMessaging,msg)
        remoteView.setImageViewResource(R.id.ivImagenMessaging,R.drawable.icon_map)

        return remoteView
    }

    fun generateNotification(title: String, msg:String){
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pIntent = PendingIntent.getActivity(requireActivity(),0,intent,PendingIntent.FLAG_ONE_SHOT)

        var  builder: NotificationCompat.Builder = NotificationCompat.Builder(requireActivity(),channelId)
            .setSmallIcon(R.drawable.icon_map)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pIntent)

        builder = builder.setContent(getRemoteView(title,msg))
        val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId, Channelname, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())

    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        if(checkPermision()){
            if (isLocationEnable()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener{ task->
                    var location: Location? = task.result
                    if (location == null){
                        getNetworkLocation()
                    }else{
                        //requireActivity().startService(,Intent() tent(this, LocationService::class.java))

                        ContextCompat.startForegroundService(requireActivity(),
                            Intent(requireActivity(), LocationService::class.java)
                        )
                        binding.tvUbicacion.text = "Lat: ${location.latitude} log ${location.longitude} \n Ciudad: " + getCityName(location.latitude, location.longitude) + ", pais"+ getcountryName(location.latitude,location.longitude)
                        var cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),18F)
                        mMap!!.moveCamera(cameraUpdate)
                        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
                    }
                }

            }else{
                Toast.makeText(this.context,"Activa tu servicio de Ubicacion por Favor", Toast.LENGTH_LONG).show()
            }

        }else{
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getNetworkLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest,locationCallback, Looper.myLooper()

        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation: Location = p0.lastLocation
            binding.tvUbicacion.text = "Lat: ${lastLocation.latitude} log ${lastLocation.longitude} \n Ciudad: " + getCityName(lastLocation.latitude, lastLocation.longitude) + ", pais"+ getcountryName(lastLocation.latitude,lastLocation.longitude)
            //mMap.mapType(GoogleMap.MAP_TYPE_NORMAL)

            ContextCompat.startForegroundService(requireActivity(),
                Intent(requireActivity(), LocationService::class.java))
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(checkPermision()) {
            mMap!!.isMyLocationEnabled = true;
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = MapFragment()
    }


    private fun checkPermision():Boolean{
        if(ActivityCompat
                .checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            return true

        }
        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            PERMISION_MAP
        )
    }

    private fun isLocationEnable():Boolean{
        val locationManager = this.context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
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
}