package com.example.pmfavouriteapp.data.repository

import com.example.pmfavouriteapp.data.local.MemberDao
import com.example.pmfavouriteapp.data.local.PartyDao
import com.example.pmfavouriteapp.data.model.Member
import com.example.pmfavouriteapp.data.model.Party
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflinePartyRepository(
    private val memberDao: MemberDao,
    private val partyDao: PartyDao
) : PartyRepository {
    override val allMembers: Flow<List<Member>> = memberDao.getAllMembers()
    override val allParties: Flow<List<Party>> = partyDao.getAllParties()

    override suspend fun insertMembers(members: List<Member>) {
        memberDao.insertAll(members)
        val parties = members.groupBy { it.party }.map { (name, list) ->
            Party(name = name, memberCount = list.size)
        }
        partyDao.insertAll(parties)
    }


    override suspend fun toggleFavorite(party: Party) {
        val updated = party.copy(favorite = !party.favorite)
        partyDao.updateParty(updated)
    }

    override val favoriteMemberCount: Flow<Int> = allParties.map { parties ->
        parties.filter { it.favorite }.sumOf { it.memberCount }
    }

    override val isMajority: Flow<Boolean> = allParties.map { parties ->
        val totalMembers = parties.sumOf { it.memberCount }
        val favoriteMembers = parties.filter { it.favorite }.sumOf { it.memberCount }
        favoriteMembers > totalMembers / 2
    }
}