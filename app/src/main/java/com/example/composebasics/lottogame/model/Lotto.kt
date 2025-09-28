package com.example.composebasics.lottogame.model

data class Lotto(
    val numbers: Set<Int> = generateNumbers(),
    val guesses: Int = 0,
    val results: Map<Int, Int> = emptyMap()
) {
    fun check(guess: Set<Int>): Lotto {
        if (!isValidGuess(guess)) return this

        val matches = numbers.intersect(guess).size
        val updatedResults = results + (matches to (results[matches] ?: 0) + 1)

        return copy(
            guesses = guesses + 1,
            results = updatedResults
        )
    }

    companion object {
        private fun generateNumbers(): Set<Int> =
            (1..40).shuffled().take(7).toSet()

        private fun isValidGuess(guess: Set<Int>): Boolean =
            guess.size == 7 && guess.all { it in 1..40 }
    }
}
