package com.example.roomcachingtest.ui

import androidx.lifecycle.*
import com.example.roomcachingtest.api.RestaurantApi
import com.example.roomcachingtest.data.Restaurant
import com.example.roomcachingtest.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()
}