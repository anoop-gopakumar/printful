package com.user.locationfinder.printful.musiclocationfinder.views

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.user.locationfinder.R
import com.user.locationfinder.databinding.ActivityMapBinding
import com.wundermobility.carrental.printful.viewmodels.MainActivityViewModel

abstract class BaseMapActivity : AppCompatActivity() , OnMapReadyCallback {

    var mMap: GoogleMap? = null
    private val markerList = ArrayList<Marker?>()

    private val viewModels: MainActivityViewModel by viewModels()

    private lateinit var mapView: View
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.let {
            mapView = it
        }
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
    }

    fun showSnackBar(message: String) {

    }


}