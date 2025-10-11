package com.example.numberguessinggame

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    private lateinit var editTextGuess: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var buttonPlayAgain: Button
    private lateinit var textViewFeedback: TextView

    private var secretNumber = Random.nextInt(1, 100)
    private var guessCount = 0
    private var gameEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextGuess = findViewById(R.id.editTextGuess)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain)
        textViewFeedback = findViewById(R.id.textViewFeedback)

        buttonSubmit.setOnClickListener {
            if (!gameEnded) handleGuess()
        }

        buttonPlayAgain.setOnClickListener {
            resetGame()
        }
    }

    private fun handleGuess() {
        val guessText = editTextGuess.text.toString()
        val guess = guessText.toIntOrNull()

        if (guess == null || guess !in 1..100) {
            textViewFeedback.text = getString(R.string.invalid_guess)
            return
        }

        guessCount++

        when {
            guess < secretNumber -> textViewFeedback.text = getString(R.string.too_low)
            guess > secretNumber -> textViewFeedback.text = getString(R.string.too_high)
            else -> {
                textViewFeedback.text = getString(R.string.correct_guess, secretNumber, guessCount)
                endGame()
            }
        }

        editTextGuess.text.clear()
    }

    private fun endGame() {
        gameEnded = true
        buttonSubmit.isEnabled = false
        editTextGuess.isEnabled = false
        buttonPlayAgain.visibility = View.VISIBLE
    }

    private fun resetGame() {
        secretNumber = Random.nextInt(1, 100)
        guessCount = 0
        gameEnded = false
        buttonSubmit.isEnabled = true
        editTextGuess.isEnabled = true
        editTextGuess.text.clear()
        textViewFeedback.text = getString(R.string.make_guess)
        buttonPlayAgain.visibility = View.GONE
    }
}
