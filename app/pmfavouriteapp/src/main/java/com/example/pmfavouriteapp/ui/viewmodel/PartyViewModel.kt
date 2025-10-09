package com.example.pmfavouriteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmfavouriteapp.data.model.Member
import com.example.pmfavouriteapp.data.repository.NetworkParliamentRepository
import com.example.pmfavouriteapp.data.repository.PartyRepository
import com.example.pmfavouriteapp.ui.models.PartyUiModel
import com.example.pmfavouriteapp.ui.models.PartyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch



class PartyViewModel(
    private val repository: PartyRepository,
    private val networkRepository: NetworkParliamentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PartyUiState(isLoading = true))
    val uiState: StateFlow<PartyUiState> = _uiState.asStateFlow()

    init {
        fetchAndSaveMembers()
        observeRepository()
    }

    private fun observeRepository() {
        viewModelScope.launch {
            combine(
                repository.allParties,
                repository.favoriteMemberCount,
                repository.isMajority
            ) { parties, favorites, majority ->
                PartyUiState(
                    parties = parties.map { PartyUiModel.fromParty(it) },
                    favoriteCount = favorites,
                    canFormMajority = majority,
                    isLoading = false
                )
            }.catch { e ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    private fun fetchAndSaveMembers() {
        viewModelScope.launch {
            try {
                val members: List<Member> = networkRepository.fetchAllMembers()
                repository.insertMembers(members)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Failed to fetch members: ${e.localizedMessage}"
                )
            }
        }
    }

    fun toggleFavorite(party: PartyUiModel) {
        viewModelScope.launch {
            val dbParty = repository.allParties.firstOrNull()?.find { it.name == party.name }
            dbParty?.let { repository.toggleFavorite(it) }
        }
    }
}