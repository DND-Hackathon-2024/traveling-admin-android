package com.plass.travlingadmin.remote.response

data class BaseResponse<T>(
    val httpStatus: String,
    val massage: String,
    val data: T
)

data class BaseVoidResponse(
    val httpStatus: String,
    val massage: String,
)