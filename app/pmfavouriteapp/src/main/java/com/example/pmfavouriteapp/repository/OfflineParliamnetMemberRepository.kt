package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.data.ParliamentMemberDao
import com.example.pmfavouriteapp.entities.ParliamentMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OfflineParliamentRepository(private val dao: ParliamentMemberDao) : ParliamentRepository {

    override fun getAllMembers(): Flow<List<ParliamentMember>> = dao.getAllMembers()

    override suspend fun addAll(members: List<ParliamentMember>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(members)
        }
    }
}