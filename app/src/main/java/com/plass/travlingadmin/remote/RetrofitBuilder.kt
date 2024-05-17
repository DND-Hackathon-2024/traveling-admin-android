package com.plass.travlingadmin.remote

import android.util.Log
import com.google.gson.Gson
import com.plass.travlingadmin.remote.Json.isJsonArray
import com.plass.travlingadmin.remote.Json.isJsonObject
import com.plass.travlingadmin.remote.service.CouponAPI
import com.plass.travlingadmin.remote.service.MemberAPI
import com.plass.travlingadmin.remote.service.TrapApi
import com.plass.travlingadmin.utiles.Env
import com.plass.travlingadmin.utiles.TAG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var retrofit: Retrofit? = null
    private var noTokenRetrofit: Retrofit? = null

    private val logInterceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG, "Retrofit-Client : $message")

        when {
            message.isJsonObject() ->
                Log.d(TAG, JSONObject(message).toString(4))

            message.isJsonArray() ->
                Log.d(TAG, JSONObject(message).toString(4))

            else -> {
                try {
                    Log.d(TAG, JSONObject(message).toString(4))
                } catch (e: Exception) {
                }
            }
        }
    }.setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val okhttpBuilder = OkHttpClient().newBuilder()
                .addInterceptor(TokenInterceptor())
                .addInterceptor(logInterceptor)
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

    fun getPlaceApi(): TrapApi =
        getRetrofit().create(TrapApi::class.java)
}