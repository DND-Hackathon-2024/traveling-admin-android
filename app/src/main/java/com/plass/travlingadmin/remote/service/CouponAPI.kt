package com.plass.travlingadmin.remote.service

import com.plass.travlingadmin.remote.response.BaseResponse
import com.plass.travlingadmin.remote.response.CouponResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CouponAPI {

    @GET("/coupon/{id}")
    suspend fun couponById(
        @Path("id") id: Int
    ): BaseResponse<CouponResponse>

    @GET("/coupon/location/{location}")
    suspend fun couponWithLocation(
        @Path("location") location: String
    ): BaseResponse<List<CouponResponse>>


}