package com.example.pmfavouriteapp.di

import android.content.Context
import com.example.pmfavouriteapp.data.local.AppDatabase
import com.example.pmfavouriteapp.data.repository.NetworkParliamentRepository
import com.example.pmfavouriteapp.data.repository.OfflinePartyRepository
import com.example.pmfavouriteapp.data.repository.PartyRepository

class AppContainer(private val context: Context) {
    val partyRepository: PartyRepository by lazy {
        OfflinePartyRepository(
            AppDatabase.Companion.getInstance(context).memberDao(),
            AppDatabase.Companion.getInstance(context).partyDao()
        )
    }

    val networkParliamentRepository: NetworkParliamentRepository by lazy {
        NetworkParliamentRepository()
    }
}