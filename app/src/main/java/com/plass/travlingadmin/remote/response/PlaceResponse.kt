package com.plass.travlingadmin.remote.response

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("placeDesc")
    val placeDesc: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("couponId")
    val couponId: Int,
    @SerializedName("imgUrl")
    val imgUrl: String
)