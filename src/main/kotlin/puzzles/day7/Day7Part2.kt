package puzzles.day7

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day7Part2: Puzzle {

    private val input = ResourceFile("day_7_input.txt")

    override fun solve(): PuzzleResult =
        input.lines
            .map { JokerHand.parse(it) }
            .sortedBy { it }
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()
            .let { PuzzleResult(it) }
}


data class JokerCard(val value: Int) {
    companion object {
        fun parse(code: Char): JokerCard {
            val value = when (code) {
                'T' -> 10
                'J' -> 1
                'Q' -> 12
                'K' -> 13
                'A' -> 14
                else -> code.toString().toInt()
            }
            return JokerCard(value)
        }
    }
}


data class JokerHand(val cards: List<JokerCard>, val bid: Int) : Comparable<JokerHand> {

    private val score = (1..14).maxOfOrNull { card ->

        val cardsWithJokerReplaced = cards.map { if (it.value == 1) JokerCard(card) else it }

        when {
            cardsWithJokerReplaced.all {  it == cardsWithJokerReplaced[0] } -> 10 // Five of a kind
            cardsWithJokerReplaced.groupBy { it }.any { it.value.size == 4 } -> 8 // Four of a kind
            cardsWithJokerReplaced.groupBy { it }.any { it.value.size == 3 } &&
                    cardsWithJokerReplaced.groupBy { it }.any { it.value.size == 2 } -> 6 // Full house
            cardsWithJokerReplaced.groupBy { it }.any { it.value.size == 3 } -> 4 // Three of a kind
            cardsWithJokerReplaced.groupBy { it }.filter { it.value.size == 2 }.size == 2 -> 3 // Two pair
            cardsWithJokerReplaced.groupBy { it }.any { it.value.size == 2 } -> 2 // One pair
            else -> 1 // High card
        }
    } ?: 0

    override fun compareTo(other: JokerHand): Int {
        return when {
            score > other.score -> 1
            score < other.score -> -1
            else -> {
                cards.zip(other.cards).map { it.first.value - it.second.value }.firstOrNull { it != 0 } ?: 0
            }
        }
    }

    companion object {
        fun parse(line: String): JokerHand {
            val (cards, bid) = line.split(" ")
            return JokerHand(cards.map { JokerCard.parse(it) }, bid.toInt())
        }
    }
}