package com.example.discoverrestaurants.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.discoverrestaurants.data.model.Restaurant
import com.example.discoverrestaurants.data.service.DoordashAPI
import com.example.discoverrestaurants.data.service.DoordashAPIProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantListViewModel : ViewModel() {
    val doordashAPI: DoordashAPI = DoordashAPIProvider.doordashAPI
    //creating livedata to get restaurants results
    val restaurantsList: MutableLiveData<List<Restaurant>?> by lazy {
        MutableLiveData<List<Restaurant>?>()
    }


    fun searchRestaurants(lat: Double, lng: Double): LiveData<List<Restaurant>?> {
        doordashAPI.searchRestaurants(lat, lng)?.enqueue(object: Callback<List<Restaurant>?> {
            override fun onFailure(call: Call<List<Restaurant>?>, t: Throwable) {
                Log.e(TAG, "Error getting restaurants list from server. Failure!", t)
            }

            override fun onResponse(call: Call<List<Restaurant>?>, response: Response<List<Restaurant>?>) {
                Log.d(TAG, "Received restaurants list response from server ${response.isSuccessful}")
                if (response.isSuccessful) {
                    val restaurants : List<Restaurant>? = response.body()
                    restaurantsList.value = restaurants
                } else {
                    Log.e(TAG, "Error getting restaurants list from server ${response.errorBody()}")
                }
            }
        })
        return restaurantsList
    }

    companion object {
        private val TAG = RestaurantListViewModel::class.java.name
    }
}