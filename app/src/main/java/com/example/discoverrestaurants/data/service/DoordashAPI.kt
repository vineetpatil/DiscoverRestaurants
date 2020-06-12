package com.example.discoverrestaurants.data.service

import com.example.discoverrestaurants.data.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DoordashAPI {
    /**
     * Example search url : "https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956"
     */
    @GET("/v2/restaurant")
    fun searchRestaurants(
        @Query("lat") key: Double,
        @Query("lng") query: Double
    ): Call<List<Restaurant>?>?

    /**
     * Example get url : "​https://api.doordash.com/v2/restaurant/30/"
     */
    @GET("/v2/restaurant/{restaurant_id}")
    fun getRestaurant(
        @Path("restaurant_id") id: Int
    ): Call<Restaurant?>?
}