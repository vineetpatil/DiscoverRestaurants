package com.example.discoverrestaurants.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.discoverrestaurants.R
import com.example.discoverrestaurants.data.model.Restaurant

class RestaurantsAdapter constructor(
    private val context: Context,
    private var restaurants: List<Restaurant>
) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item, parent, false)
        return RestaurantViewHolder(view, object : ClickListener {
            override fun handleClick(position: Int) {
                val id = restaurants[position].id
                // TODO: handle clicking on restaurant listing
            }
        })
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        if (restaurant != null) {
            val thumbnailUrl = restaurant.cover_img_url
            if (thumbnailUrl != null && thumbnailUrl.isNotEmpty()) {
                // load the image into holder's imageview
                Glide.with(context)
                    .load(thumbnailUrl)
                    .into(holder.thumbnailImageView)
            }
            holder.restaurantNameTextView.text = restaurant.name
            holder.descriptionTextView.text = restaurant.description
            holder.statusTextView.text = restaurant.status
        }
    }

    override fun getItemCount() = restaurants.size

    class RestaurantViewHolder(view: View, clickListener: ClickListener?) :
        ViewHolder(view) {
        val thumbnailImageView: ImageView = view.findViewById(R.id.thumbnail_image_view)
        val restaurantNameTextView: TextView = view.findViewById(R.id.restaurant_name_text_view)
        val descriptionTextView: TextView = view.findViewById(R.id.description_text_view)
        val statusTextView: TextView = view.findViewById(R.id.status_text_view)
        private val mClickListener: ClickListener? = clickListener

        init {
            view.setOnClickListener {
                val position = adapterPosition
                mClickListener?.handleClick(position)
            }
        }
    }

    interface ClickListener {
        fun handleClick(position: Int)
    }

    fun updateRestaurants(restaurants: List<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }
}