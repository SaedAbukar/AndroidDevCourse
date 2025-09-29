package com.example.retrofit
class WikiRepository {
    private val api = WikiApi.service

    suspend fun hitCountCheck(name: String): WikiApi.WikiResponse {
        return api.getHits(name)
    }
}
