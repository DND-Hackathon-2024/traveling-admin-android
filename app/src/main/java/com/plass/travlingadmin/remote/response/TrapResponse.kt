package com.plass.travlingadmin.remote.response

import com.google.gson.annotations.SerializedName

data class TrapResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("placeDesc")
    val placeDesc: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("couponId")
    val couponId: CouponResponse,
    @SerializedName("imgUrl")
    val imgUrl: String
)