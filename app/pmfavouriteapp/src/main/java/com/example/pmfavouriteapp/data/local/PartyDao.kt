package com.example.pmfavouriteapp.data.local

import androidx.room.*
import com.example.pmfavouriteapp.data.model.Party
import kotlinx.coroutines.flow.Flow

@Dao
interface PartyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(parties: List<Party>)

    @Query("SELECT * FROM Party")
    fun getAllParties(): Flow<List<Party>>

    @Update
    suspend fun updateParty(party: Party)
}