package com.plass.travlingadmin.remote.service

import com.plass.travlingadmin.remote.request.CreateTrapRequest
import com.plass.travlingadmin.remote.response.BaseResponse
import com.plass.travlingadmin.remote.response.BaseVoidResponse
import com.plass.travlingadmin.remote.response.TrapResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TrapApi {

    @GET("/trap/list")
    suspend fun getTraps(): BaseResponse<List<TrapResponse>>

    @POST("/trap/create")
    suspend fun createTrap(
        @Body request: CreateTrapRequest
    ): BaseVoidResponse
}