package com.wundermobility.carrental.printful.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.*

object AddressFetcher {

    fun getAddressFromLatLong(ctx: Context, latitude: Double, longitude: Double): String {

        val geocoder = Geocoder(ctx, Locale.getDefault())

        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)

        val address: String =
            addresses[0].getAddressLine(0)

        val city: String? = addresses[0].getLocality()
        val state: String? = addresses[0].getAdminArea()
        val country: String? = addresses[0].getCountryName()
        val postalCode: String? = addresses[0].getPostalCode()
        val knownName: String? = addresses[0].getFeatureName()

        return address
    }
}