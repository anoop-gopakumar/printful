package com.user.locationfinder.printful.musiclocationfinder.models

import com.google.gson.annotations.SerializedName

data class Places(
    val id: String,
    var type: String,
    var score: Int,
    var name: String,
    var coordinates: Coordinates,
    @SerializedName("life-span")
    var lifespan : LifeSpan
)