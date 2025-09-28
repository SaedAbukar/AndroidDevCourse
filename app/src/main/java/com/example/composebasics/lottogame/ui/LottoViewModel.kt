package com.example.composebasics.lottogame.ui

import androidx.lifecycle.ViewModel
import com.example.composebasics.lottogame.model.Lotto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LottoViewModel : ViewModel() {

    private var lotto = Lotto()

    private val _uiState = MutableStateFlow(
        LottoUiState(
            drawnNumbers = lotto.numbers,
            guesses = lotto.guesses,
            results = lotto.results,
            selectedNumbers = emptySet(),
            errorMessage = null
        )
    )
    val uiState: StateFlow<LottoUiState> = _uiState.asStateFlow()


    fun toggleNumberSelection(number: Int) {
        val currentSelected = _uiState.value.selectedNumbers
        val newSelected = if (currentSelected.contains(number)) {
            currentSelected - number
        } else {
            currentSelected + number
        }
        _uiState.value = _uiState.value.copy(
            selectedNumbers = newSelected,
            errorMessage = null
        )
    }


    fun checkSelectedNumbers() {
        val selection = _uiState.value.selectedNumbers
        if (selection.size != 7) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "You must select exactly 7 numbers."
            )
            return
        }

        lotto = lotto.check(selection)
        _uiState.value = LottoUiState(
            drawnNumbers = lotto.numbers,
            guesses = lotto.guesses,
            results = lotto.results,
            selectedNumbers = selection,
            errorMessage = null
        )
    }


    fun resetGame() {
        lotto = Lotto()
        _uiState.value = LottoUiState(
            drawnNumbers = lotto.numbers,
            guesses = lotto.guesses,
            results = lotto.results,
            selectedNumbers = emptySet(),
            errorMessage = null
        )
    }
}
