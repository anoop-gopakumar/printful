package com.user.locationfinder.printful.musiclocationfinder.models

import com.google.gson.annotations.SerializedName

class PlacesHolder() {

    @SerializedName("count")
    val count: Int = -1

    @SerializedName("offset")
    val offset: Int = -1

    @SerializedName("places")
    val places: MutableList<Places>? = null

}
