package com.example.composebasics
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun NumberGuesser(modifier: Modifier = Modifier) {
    var secretNumber by remember { mutableStateOf(Random.nextInt(1, 100)) }
    var guessInput by remember { mutableStateOf("") }
    var guessCount by remember { mutableStateOf(0) }
    var gameEnded by remember { mutableStateOf(false) }
    var currentGuess by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditNumberField(
            label = stringResource(R.string.enter_guess),
            value = guessInput,
            onValueChange = { guessInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            enabled = !gameEnded,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val guess = guessInput.toIntOrNull()
                val validGuess = guess?.takeIf { it in 1..100 }

                if (validGuess != null) {
                    guessCount++
                    currentGuess = validGuess
                    guessInput = ""
                    if (validGuess == secretNumber) {
                        gameEnded = true
                    }
                } else {
                    currentGuess = null
                }
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = if (gameEnded) stringResource(R.string.game_over) else stringResource(R.string.submit_guess))
        }

        CorrectNumber(
            secretNumber = secretNumber,
            guess = currentGuess,
            guessCount = guessCount,
            gameEnded = gameEnded,
            onCorrect = { gameEnded = true }
        )

        if (gameEnded) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                secretNumber = Random.nextInt(1, 100)
                guessInput = ""
                currentGuess = null
                guessCount = 0
                gameEnded = false
            }) {
                Text(stringResource(R.string.play_again))
            }
        }
    }
}

@Composable
fun EditNumberField(
    label: String,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        modifier = modifier
    )
}

@Composable
fun CorrectNumber(
    secretNumber: Int,
    guess: Int?,
    guessCount: Int,
    gameEnded: Boolean,
    onCorrect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val feedback = when {
        guess == null -> stringResource(R.string.make_guess)
        guess < secretNumber -> stringResource(R.string.too_low)
        guess > secretNumber -> stringResource(R.string.too_high)
        else -> {
            if (!gameEnded) onCorrect()
            stringResource(R.string.correct_guess, secretNumber, guessCount)
        }
    }

    Text(
        text = feedback,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}
