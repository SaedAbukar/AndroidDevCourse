package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.data.UserDao
import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef
import com.example.pmfavouriteapp.relations.UserWithFavourites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OfflineUserRepository(private val dao: UserDao) : UserRepository {

    override fun getUsersWithFavourites(): Flow<List<UserWithFavourites>> =
        dao.getUsersWithFavourites()

    override suspend fun addFavourite(userId: Int, hetekaId: Int) {
        withContext(Dispatchers.IO) {
            dao.addFavourite(UserFavouriteCrossRef(userId, hetekaId))
        }
    }

    override suspend fun removeFavourite(userId: Int, hetekaId: Int) {
        withContext(Dispatchers.IO) {
            dao.removeFavourite(userId, hetekaId)
        }
    }
}