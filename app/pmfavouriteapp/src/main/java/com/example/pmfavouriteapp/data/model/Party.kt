package com.example.pmfavouriteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Party(
    @PrimaryKey val name: String,
    val memberCount: Int = 0,
    val favorite: Boolean = false
)