package com.example.discoverrestaurants.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.discoverrestaurants.R
import com.example.discoverrestaurants.data.model.Restaurant
import com.example.discoverrestaurants.ui.viewmodel.RestaurantListViewModel

class RestaurantListActivity : AppCompatActivity() {
    // Doordash HQ lat lng
    private val latitude = 37.422740
    private val longitude = -122.139956

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RestaurantsAdapter
    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        adapter = RestaurantsAdapter(this, mutableListOf())
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel::class.java)
        viewModel.searchRestaurants(latitude, longitude).observe(this, Observer<List<Restaurant>?>{ restaurants ->
            if (restaurants != null) {
                Log.d(TAG, "Restaurants : $restaurants")
                // update UI
                adapter.updateRestaurants(restaurants!!)
            }
        })
    }

    companion object {
        private val TAG = RestaurantListActivity::class.java.name
    }
}
