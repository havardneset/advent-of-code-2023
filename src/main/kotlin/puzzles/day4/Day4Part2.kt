package puzzles.day4

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day4Part2: Puzzle {

    private val input = ResourceFile("day_4_input.txt")

    override fun solve(): PuzzleResult {
        val cards = input.lines
            .map { Card.parse(it) }
            .associateBy { it.id }

        return cards.values
            .sumOf { scratchUntilEmpty(cards, it) }
            .let { PuzzleResult(it) }
    }

    private fun scratchUntilEmpty(cards: Map<Int, Card>, card: Card): Int {
        if (card.matches == 0) return 1

        val copiesToMake = (1..card.matches).map { card.id + it }

        return copiesToMake
            .mapNotNull { cards[it] }
            .sumOf { scratchUntilEmpty(cards, it) }
            .plus(1)
    }
}

