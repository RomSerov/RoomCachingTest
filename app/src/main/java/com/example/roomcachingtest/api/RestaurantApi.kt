package com.example.roomcachingtest.api

import com.example.roomcachingtest.data.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    @GET("restaurant/random_restaurant")
    suspend fun getRestaurant(@Query("size") size: Int): List<Restaurant>

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }
}