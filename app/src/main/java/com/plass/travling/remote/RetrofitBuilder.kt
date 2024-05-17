package com.plass.travling.remote

import com.plass.travling.utiles.Env
import retrofit2.Retrofit

object RetrofitBuilder {
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Env.BASE_URl)
                .build()
        }
        return retrofit!!
    }
}