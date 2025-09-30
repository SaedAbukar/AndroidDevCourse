package com.example.pmfavouriteapp.ui.viewmodel

import com.example.pmfavouriteapp.relations.UserWithFavourites

data class UserUiState(
    val userId: Int = 0,
    val name: String = "",
    val favourites: List<ParliamentMemberUiState> = emptyList()
)

fun UserWithFavourites.toUiState(): UserUiState = UserUiState(
    userId = user.userId,
    name = user.username,
    favourites = favourites.map { it.toUiState() }
)

