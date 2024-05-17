package com.plass.travlingadmin.remote.service

import com.plass.travlingadmin.remote.response.BaseResponse
import com.plass.travlingadmin.remote.response.TrapResponse
import retrofit2.http.GET

interface TrapApi {

    @GET("/trap/list")
    suspend fun getPlaceList(): BaseResponse<List<TrapResponse>>
}