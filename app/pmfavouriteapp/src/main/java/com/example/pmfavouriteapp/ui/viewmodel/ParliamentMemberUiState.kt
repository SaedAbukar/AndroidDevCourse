package com.example.pmfavouriteapp.ui.viewmodel

import com.example.pmfavouriteapp.entities.ParliamentMember

data class ParliamentMemberUiState(
    val hetekaId: Int = 0,
    val seatNumber: Int = 0,
    val lastname: String = "",
    val firstname: String = "",
    val party: String = "",
    val minister: Boolean = false,
    val pictureUrl: String = ""
)

fun ParliamentMember.toUiState(): ParliamentMemberUiState = ParliamentMemberUiState(
    hetekaId = hetekaId,
    seatNumber = seatNumber,
    lastname = lastname,
    firstname = firstname,
    party = party,
    minister = minister,
    pictureUrl = pictureUrl
)

