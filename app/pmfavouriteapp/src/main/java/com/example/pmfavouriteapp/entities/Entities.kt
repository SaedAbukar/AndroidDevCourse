package com.example.pmfavouriteapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val username: String
)

@Entity(tableName = "parliament_member")
data class ParliamentMember(
    @PrimaryKey val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
)

@Entity(
    tableName = "user_favourite_cross_ref",
    primaryKeys = ["userId", "hetekaId"]
)
data class UserFavouriteCrossRef(
    val userId: Int,
    val hetekaId: Int
)
