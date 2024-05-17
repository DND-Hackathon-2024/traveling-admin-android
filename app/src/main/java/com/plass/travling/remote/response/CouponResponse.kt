package com.plass.travling.remote.response

data class CouponResponse(
    val couponId: Int,
    val code: String,
    val couponName: String,
    val description: String,
    val location: String,
    val couponDiscount: String,
    val couponCreateUserName: String
)