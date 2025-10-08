package com.example.pmfavouriteapp.data.repository

import com.example.pmfavouriteapp.data.model.Member
import com.example.pmfavouriteapp.data.model.Party
import kotlinx.coroutines.flow.Flow

interface PartyRepository {
    val allMembers: Flow<List<Member>>
    val allParties: Flow<List<Party>>
    suspend fun insertMembers(members: List<Member>)

    suspend fun toggleFavorite(party: Party)

    val favoriteMemberCount: Flow<Int>

    val isMajority: Flow<Boolean>
}