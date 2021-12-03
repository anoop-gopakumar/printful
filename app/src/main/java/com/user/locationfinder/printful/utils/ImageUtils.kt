package com.wundermobility.carrental.printful.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.user.locationfinder.R

object ImageUtils {

    fun loadImage(context : Context, url : String , imageView: ImageView ){

        Glide
            .with(context)
            .load(url)
            .centerInside()
            .placeholder(R.drawable.placeholder)
            .into(imageView);
    }

    fun loadImageThumbnail(context : Context, url : String , imageView: ImageView ){

        Glide
            .with(context)
            .load(url)
            .centerInside().thumbnail(0.05f)
            .placeholder(R.drawable.placeholder)
            .into(imageView);
    }



}