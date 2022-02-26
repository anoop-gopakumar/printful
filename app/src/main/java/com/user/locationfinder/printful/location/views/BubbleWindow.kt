package com.user.locationfinder.printful.location.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.user.locationfinder.databinding.BubbleWindowBinding
import com.wundermobility.carrental.printful.models.CustomInfo
import com.wundermobility.carrental.printful.utils.ImageUtils

class BubbleWindow(private val ctx: Context) : GoogleMap.InfoWindowAdapter {

    private var inflater: LayoutInflater? = null
    private lateinit var binding: BubbleWindowBinding

    init {
        inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getInfoContents(marker: Marker): View? {
        binding = BubbleWindowBinding.inflate(inflater!!)

        binding.userAddress.text = (marker.tag as CustomInfo).address

        binding.userName.text = marker.title
        marker.snippet?.let { ImageUtils.loadImageThumbnail(ctx, it, binding.profileImage) }

        return binding.root
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

}