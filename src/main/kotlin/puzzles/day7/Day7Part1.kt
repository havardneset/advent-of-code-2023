package puzzles.day7

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day7Part1: Puzzle {

    private val input = ResourceFile("day_7_input.txt")

    override fun solve(): PuzzleResult =
        input.lines
            .map { Hand.parse(it) }
            .sortedBy { it }
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()
            .let { PuzzleResult(it) }
}

data class Card(val value: Int) {
    companion object {
        fun parse(code: Char): Card {
            val value = when (code) {
                'T' -> 10
                'J' -> 11
                'Q' -> 12
                'K' -> 13
                'A' -> 14
                else -> code.toString().toInt()
            }
            return Card(value)
        }
    }
}

data class Hand(val cards: List<Card>, val bid: Int) : Comparable<Hand> {

    val score = when {
        cards.all { it == cards[0] } -> 10 // Five of a kind
        cards.groupBy { it }.any { it.value.size == 4 } -> 8 // Four of a kind
        cards.groupBy { it }.any { it.value.size == 3 } &&
                cards.groupBy { it }.any { it.value.size == 2 } -> 6 // Full house
        cards.groupBy { it }.any { it.value.size == 3 } -> 4 // Three of a kind
        cards.groupBy { it }.filter { it.value.size == 2 }.size == 2 -> 3 // Two pair
        cards.groupBy { it }.any { it.value.size == 2 } -> 2 // One pair
        else -> 1 // High card
    }

    override fun compareTo(other: Hand): Int {
        return when {
            score > other.score -> 1
            score < other.score -> -1
            else -> {
                cards.zip(other.cards).map { it.first.value - it.second.value }.firstOrNull { it != 0 } ?: 0
            }
        }
    }

    companion object {
        fun parse(line: String): Hand {
            val (cards, bid) = line.split(" ")
            return Hand(cards.map { Card.parse(it) }, bid.toInt())
        }
    }
}
