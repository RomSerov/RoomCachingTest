package com.example.roomcachingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomcachingtest.adapter.RestaurantAdapter
import com.example.roomcachingtest.databinding.ActivityMainBinding
import com.example.roomcachingtest.ui.RestaurantViewModel
import com.example.roomcachingtest.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantAdapter = RestaurantAdapter()
        binding.apply {
            recyclerViewRestaurant.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            viewModel.restaurants.observe(this@MainActivity) {
                restaurantAdapter.submitList(it.data)
                progressRestaurant.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                textError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
                textError.text = it.error?.localizedMessage
            }
        }
    }
}