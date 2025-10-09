package com.example.pmfavouriteapp.ui.models

data class PartyUiState(
    val parties: List<PartyUiModel> = emptyList(),
    val favoriteCount: Int = 0,
    val canFormMajority: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
