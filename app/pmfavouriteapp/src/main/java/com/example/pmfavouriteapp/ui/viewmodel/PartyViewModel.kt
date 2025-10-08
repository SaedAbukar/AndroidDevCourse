package com.example.pmfavouriteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmfavouriteapp.data.model.Member
import com.example.pmfavouriteapp.data.model.Party
import com.example.pmfavouriteapp.data.repository.NetworkParliamentRepository
import com.example.pmfavouriteapp.data.repository.PartyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PartyViewModel(
    private val repository: PartyRepository,
    private val networkRepository: NetworkParliamentRepository
) : ViewModel() {


    val parties: StateFlow<List<Party>> = repository.allParties
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val favoriteCount: StateFlow<Int> = repository.favoriteMemberCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val majority: StateFlow<Boolean> = repository.isMajority
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        fetchAndSaveMembers()
    }

    private fun fetchAndSaveMembers() {
        viewModelScope.launch {
            try {
                val members: List<Member> = networkRepository.fetchAllMembers()
                repository.insertMembers(members)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(party: Party) {
        viewModelScope.launch {
            repository.toggleFavorite(party)
        }
    }
}
