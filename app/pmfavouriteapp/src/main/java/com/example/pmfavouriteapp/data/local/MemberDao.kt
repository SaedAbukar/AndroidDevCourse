package com.example.pmfavouriteapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pmfavouriteapp.data.model.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(members: List<Member>)

    @Query("SELECT * FROM Member")
    fun getAllMembers(): Flow<List<Member>>
}