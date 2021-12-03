package com.user.locationfinder.printful.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.wundermobility.carrental.printful.models.UserInfo
import com.wundermobility.carrental.printful.viewmodels.MainActivityViewModel
import kotlin.collections.ArrayList
import com.google.android.gms.maps.model.LatLngBounds
import com.user.locationfinder.R
import com.user.locationfinder.databinding.ActivityMainBinding
import com.wundermobility.carrental.printful.models.CustomInfo
import com.wundermobility.carrental.printful.models.UserUpdatedInfo
import com.wundermobility.carrental.printful.utils.AddressFetcher

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private var mMap: GoogleMap? = null
    private val markerList = ArrayList<Marker?>()
    private val viewModels: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        observeViewModels()
    }

    private fun observeViewModels() {
        viewModels.userUpdatedInfoLiveData.observe(this, {
            updateUserLocationDynamically(it)
        })

        viewModels.userInfoLiveData.observe(this, {
            showUserLocationOnMap(it)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.setInfoWindowAdapter(BubbleWindow(binding.root.context))
    }

    private fun showUserLocationOnMap(userList: ArrayList<UserInfo>) {

        markerList.clear()
        userList.forEach {

            val geoPosition = LatLng(it.latitude, it.longitude)
            val markerOptions =
                MarkerOptions().position(geoPosition).title(it.name).snippet(it.profileImage);

            val inMarker: Marker? = mMap?.addMarker(markerOptions)
            inMarker?.tag = CustomInfo(it.id, AddressFetcher.getAddressFromLatLong(
                baseContext, it.latitude, it.longitude
            ))
            markerList.add(inMarker)
        }

        val cu = CameraUpdateFactory.newLatLngBounds(adjustMarkerPosition(markers = markerList), 0)
        mMap?.moveCamera(cu)

        optimizeZoomLevel()
    }

    private fun updateUserLocationDynamically(userUpdatedInfo: UserUpdatedInfo) {
        markerList.forEach {

           if( it?.tag is CustomInfo){
               if ((it.tag as CustomInfo).id == userUpdatedInfo.id) {
                   it.position = LatLng(userUpdatedInfo.latitude, userUpdatedInfo.longitude)

                   if(it.isInfoWindowShown){
                       it.showInfoWindow()
                   }

                   it.tag = CustomInfo(userUpdatedInfo.id,AddressFetcher.getAddressFromLatLong(
                       baseContext, userUpdatedInfo.latitude, userUpdatedInfo.longitude
                   ))

               }
           }
        }
    }

    private fun adjustMarkerPosition(markers: ArrayList<Marker?>): LatLngBounds {
        val builder = LatLngBounds.Builder()
        for (marker in markers) {
            builder.include(marker!!.position)
        }
        return builder.build()
    }

    private fun optimizeZoomLevel() {
        if (mMap?.cameraPosition?.zoom!! >= 16) {
            mMap?.moveCamera(CameraUpdateFactory.zoomTo(16F));
        }
    }


}