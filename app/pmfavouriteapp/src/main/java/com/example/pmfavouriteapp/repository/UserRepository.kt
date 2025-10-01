package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.entities.User
import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef
import com.example.pmfavouriteapp.relations.UserWithFavourites
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun addUser(user: User)
    fun getUsersWithFavourites(): Flow<List<UserWithFavourites>>

    suspend fun addFavourite(userId: Int, hetekaId: Int)

    suspend fun removeFavourite(userId: Int, hetekaId: Int)
}
