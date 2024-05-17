package com.plass.travlingadmin.remote.service

import com.plass.travlingadmin.remote.request.JoinRequest
import com.plass.travlingadmin.remote.request.LoginRequest
import com.plass.travlingadmin.remote.response.BaseResponse
import com.plass.travlingadmin.remote.response.BaseVoidResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberAPI {

    @POST("/member/register")
    suspend fun join(
        @Body request: JoinRequest
    ): BaseVoidResponse

    @POST("/member/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BaseResponse<String>
}