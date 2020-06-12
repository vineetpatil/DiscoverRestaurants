package com.example.discoverrestaurants.data.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DoordashAPIProvider {
    private const val BASE_URL = "https://api.doordash.com"
    private val sAPI: DoordashAPI? = null

    @JvmStatic
    val doordashAPI: DoordashAPI
        get() {
            if (sAPI == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                return retrofit.create(DoordashAPI::class.java)
            }
            return sAPI
        }
}