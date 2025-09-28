package com.example.composebasics.lottogame.ui

data class LottoUiState(
    val drawnNumbers: Set<Int> = emptySet(),
    val guesses: Int = 0,
    val results: Map<Int, Int> = emptyMap(),
    val selectedNumbers: Set<Int> = emptySet(),
    val errorMessage: String? = null
)




