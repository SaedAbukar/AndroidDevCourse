package com.example.composebasics.lottogame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebasics.R

@Composable
fun LottoScreen(
    modifier: Modifier = Modifier,
    lottoViewModel: LottoViewModel = viewModel(),
) {
    val uiState by lottoViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        LottoLayout(
            selectedNumbers = uiState.selectedNumbers,
            onNumberClick = { lottoViewModel.toggleNumberSelection(it) }
        )

        Text(text = stringResource(R.string.guesses_label, uiState.guesses))
        Text(text = stringResource(R.string.results_label, uiState.results.toString()))

        if (uiState.guesses > 0) {
            Text(
                text = stringResource(
                    R.string.winning_numbers_label,
                    uiState.drawnNumbers.sorted().joinToString(", ")
                ),
                color = Color.Blue
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { lottoViewModel.checkSelectedNumbers() }) {
                Text(text = stringResource(R.string.submit_guess))
            }
            Button(onClick = { lottoViewModel.resetGame() }) {
                Text(text = stringResource(R.string.reset_game))
            }
        }

        uiState.errorMessage?.takeIf { it.isNotEmpty() }?.let { error ->
            Text(text = error, color = Color.Red)
        }

    }
}




@Composable
fun LottoLayout(
    selectedNumbers: Set<Int>,
    onNumberClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0 until 4) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (j in i * 10 + 1..i * 10 + 10) {
                    LottoBox(
                        number = j,
                        selected = selectedNumbers.contains(j),
                        onClick = { onNumberClick(j) }
                    )
                }
            }
        }
    }
}


@Composable
fun LottoBox(
    number: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .padding(4.dp)
            .background(
                color = if (selected) Color.Green else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = if (selected) Color.White else Color.Black
        )
    }
}
