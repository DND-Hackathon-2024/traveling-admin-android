package com.plass.travlingadmin.remote.response

data class CouponResponse(
    val couponId: Int,
    val code: String,
    val couponName: String,
    val couponDescription: String,
    val couponLocation: String,
    val couponDiscount: String,
    val couponCreateUserName: String
)