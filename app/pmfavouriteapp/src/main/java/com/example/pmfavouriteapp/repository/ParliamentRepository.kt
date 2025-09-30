package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.entities.ParliamentMember
import kotlinx.coroutines.flow.Flow

interface ParliamentRepository {
    suspend fun addAll(members: List<ParliamentMember>)
    fun getAllMembers(): Flow<List<ParliamentMember>>
}