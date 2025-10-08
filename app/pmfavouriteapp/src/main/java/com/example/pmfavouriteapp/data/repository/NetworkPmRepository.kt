package com.example.pmfavouriteapp.data.repository

import com.example.pmfavouriteapp.data.model.Member
import com.example.pmfavouriteapp.data.network.PmApi
class NetworkParliamentRepository(
    private val api: PmApi.Service = PmApi.service
) {
    suspend fun fetchAllMembers(): List<Member> {
        return api.getSeating()
    }
}
