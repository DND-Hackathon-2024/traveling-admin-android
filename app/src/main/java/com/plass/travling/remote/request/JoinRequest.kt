package com.plass.travling.remote.request

data class JoinRequest(
    val phone: String,
    val password: String,
    val role: String = "USER"
)
