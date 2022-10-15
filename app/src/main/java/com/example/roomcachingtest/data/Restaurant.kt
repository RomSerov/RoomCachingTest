package com.example.roomcachingtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
    val address: String,
    val logo: String,
    @PrimaryKey
    val name: String,
    val type: String
)