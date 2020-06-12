package com.example.discoverrestaurants.data.model

/**
 * Restaurant​ object key fields:
○ id
○ name
○ description - ​type of food
○ cover_img_url - ​restaurant thumbnail url
○ status - ​string representing status of the restaurant (“30 mins”, “closed”,
etc.)
○ delivery_fee - ​in cents
 */
data class Restaurant (
    val id: Long,
    val name: String,
    val description: String,
    val cover_img_url: String,
    val status: String,
    val delivery_fee: Long // in cents
)