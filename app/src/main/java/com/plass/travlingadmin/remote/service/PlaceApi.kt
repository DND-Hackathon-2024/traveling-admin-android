package com.plass.travlingadmin.remote.service

import com.plass.travlingadmin.remote.response.BaseResponse
import com.plass.travlingadmin.remote.response.PlaceResponse
import retrofit2.http.GET

interface PlaceApi {

    @GET("/place/list")
    suspend fun getPlaceList(): BaseResponse<List<PlaceResponse>>
}