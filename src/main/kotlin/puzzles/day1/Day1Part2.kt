package puzzles.day1

import PuzzleResult
import Puzzle
import file.ResourceFile


class Day1Part2: Puzzle {

    private val textNumbers = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9")

    private val input = ResourceFile("day_1_input.txt")

    override fun solve(): PuzzleResult =
       input.lines()
           .map { findNumbers(it) }
           .map { it.first() + it.last() }
           .sumOf { it.toInt() }
           .let { PuzzleResult(it) }

    private fun findNumbers(line: String): Sequence<String> {
        val regex = "(?=(\\d|${textNumbers.keys.joinToString("|")}))".toRegex()

        return regex.findAll(line).map { match ->
            match.groupValues.filter { it.isNotEmpty() }
        }
        .flatten()
        .map { textNumbers[it] ?: it }

    }

}