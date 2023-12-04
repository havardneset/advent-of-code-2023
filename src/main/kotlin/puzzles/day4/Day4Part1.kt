package puzzles.day4

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day4Part1: Puzzle {

    private val input = ResourceFile("day_4_input.txt")

    override fun solve(): PuzzleResult =
        input.lines()
            .map { Card.parse(it) }
            .sumOf { it.points() }
            .let { PuzzleResult(it) }
}

data class Card(val card: Int, val winners: List<Int>, val numbers: List<Int>) {

    fun points(): Int {

        println(this)

        val N = numbers.filter { winners.contains(it) }

        if (N.isEmpty()) return 0

        return N.fold(0) { acc, i -> acc * i + 1}

    }

    companion object {

        private val regex = Regex("\\d+\\d*")

        fun parse(line: String): Card {
            val (card, rest) = line.split(":")
            val (winners, numbers) = rest.split(" | ")

            val cardId = regex.findAll(card).first().value.toInt()

            val winnersList = regex.findAll(winners).map { it.value.toInt() }.toList()
            val numberList = regex.findAll(numbers).map { it.value.toInt() }.toList()

            return Card(cardId, winnersList, numberList)
        }
    }

}