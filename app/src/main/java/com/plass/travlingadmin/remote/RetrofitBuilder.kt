package com.plass.travlingadmin.remote

import com.google.gson.Gson
import com.plass.travlingadmin.remote.service.CouponAPI
import com.plass.travlingadmin.remote.service.MemberAPI
import com.plass.travlingadmin.remote.service.PlaceApi
import com.plass.travlingadmin.utiles.Env
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var retrofit: Retrofit? = null
    private var noTokenRetrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val okhttpBuilder = OkHttpClient().newBuilder()
                .addInterceptor(TokenInterceptor())
            retrofit = Retrofit.Builder()
                .baseUrl(Env.BASE_URl)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        }
        return retrofit!!
    }

    private fun getNoTokenRetrofit(): Retrofit {
        if (noTokenRetrofit == null) {
            noTokenRetrofit = Retrofit.Builder()
                .baseUrl(Env.BASE_URl)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        }
        return noTokenRetrofit!!
    }
    fun getMemberApi(): MemberAPI =
        getNoTokenRetrofit().create(MemberAPI::class.java)

    fun getCouponApi(): CouponAPI =
        getRetrofit().create(CouponAPI::class.java)

    fun getPlaceApi(): PlaceApi =
        getRetrofit().create(PlaceApi::class.java)
}