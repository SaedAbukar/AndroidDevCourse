package com.example.pmfavouriteapp.data

import android.content.Context
import com.example.pmfavouriteapp.repository.OfflineParliamentRepository
import com.example.pmfavouriteapp.repository.OfflineUserRepository
import com.example.pmfavouriteapp.repository.ParliamentRepository
import com.example.pmfavouriteapp.repository.UserRepository
import com.example.pmfavouriteapp.repository.NetworkParliamentRepository

class AppContainer(private val context: Context) {

    val userRepository: UserRepository by lazy {
        OfflineUserRepository(
            AppDatabase.getInstance(context).userDao()
        )
    }

    val parliamentRepository: ParliamentRepository by lazy {
        OfflineParliamentRepository(
            AppDatabase.getInstance(context).parliamentMemberDao()
        )
    }

    val networkParliamentRepository: NetworkParliamentRepository by lazy {
        NetworkParliamentRepository()
    }
}
