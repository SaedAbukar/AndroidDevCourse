package com.example.pmfavouriteapp.repository

import com.example.pmfavouriteapp.data.network.PmApi
import com.example.pmfavouriteapp.entities.ParliamentMember
class NetworkParliamentRepository(
    private val api: PmApi.Service = PmApi.service
) {
    suspend fun fetchAllMembers(): List<ParliamentMember> {
        return api.getSeating()
    }
}
