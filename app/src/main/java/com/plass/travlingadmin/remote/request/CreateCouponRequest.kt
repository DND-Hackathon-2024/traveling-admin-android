package com.plass.travlingadmin.remote.request

data class CreateCouponRequest(
    val couponName: String,
    val description: String,
    val location: String,
    val couponDiscount: String
)
