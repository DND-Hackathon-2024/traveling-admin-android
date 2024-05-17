package com.plass.travlingadmin.remote.request

data class CreateTrapRequest(
    val placeName: String,
    val placeDesc: String,
    val address: String,
    val couponId: Int,
    val imgUrl: String = ""
)