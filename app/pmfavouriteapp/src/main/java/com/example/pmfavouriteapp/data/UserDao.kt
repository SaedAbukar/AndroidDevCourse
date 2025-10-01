package com.example.pmfavouriteapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pmfavouriteapp.entities.ParliamentMember
import com.example.pmfavouriteapp.entities.User
import com.example.pmfavouriteapp.entities.UserFavouriteCrossRef
import com.example.pmfavouriteapp.relations.UserWithFavourites
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(crossRef: UserFavouriteCrossRef)

    @Query("DELETE FROM user_favourite_cross_ref WHERE userId = :userId AND hetekaId = :hetekaId")
    suspend fun removeFavourite(userId: Int, hetekaId: Int)

    @Transaction
    @Query("SELECT * FROM user")
    fun getUsersWithFavourites(): Flow<List<UserWithFavourites>>

}
