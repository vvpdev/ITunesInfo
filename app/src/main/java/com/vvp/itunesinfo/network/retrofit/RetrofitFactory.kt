package com.vvp.itunesinfo.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://itunes.apple.com"

    private fun getRetrofitInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServicesApi(): ServicesApi = getRetrofitInstance().create(
        ServicesApi::class.java)
}