package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef
import com.example.pmfavouriteapp.relations.UserWithFavourites
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersWithFavourites(): Flow<List<UserWithFavourites>>

    suspend fun addFavourite(userId: Int, hetekaId: Int)

    suspend fun removeFavourite(userId: Int, hetekaId: Int)
}
