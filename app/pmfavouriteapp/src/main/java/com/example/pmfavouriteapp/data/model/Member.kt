package com.example.pmfavouriteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Member(
    @PrimaryKey val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
)