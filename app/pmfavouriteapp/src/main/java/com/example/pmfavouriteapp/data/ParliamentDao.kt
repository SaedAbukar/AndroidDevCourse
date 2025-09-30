package com.example.pmfavouriteapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pmfavouriteapp.entities.ParliamentMember
import kotlinx.coroutines.flow.Flow

@Dao
interface ParliamentMemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: List<ParliamentMember>)

    @Query("SELECT * FROM parliament_member")
    fun getAllMembers(): Flow<List<ParliamentMember>>
}
