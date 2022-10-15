package com.example.roomcachingtest.data

import android.util.Log
import androidx.room.withTransaction
import com.example.roomcachingtest.api.RestaurantApi
import com.example.roomcachingtest.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase
) {
    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = { restaurantDao.getAllRestaurants() },
        fetchFromApi = {
            delay(2000)
           api.getRestaurant(20)
        },
        saveFetchResult = {
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurant(it)
            }
        }
    )
}