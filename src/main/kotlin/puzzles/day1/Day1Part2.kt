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

    override fun solve(): PuzzleResult {

        val file = ResourceFile("day_1_input.txt")
        val result = file.lines().map {
            findNumbers(it)
        }.sumOf {
            (it.first() + it.last()).toInt()
        }

        return PuzzleResult(1, 2, result)
    }

    private fun findNumbers(line: String): Sequence<String> {
        val regex = "(?=(\\d|${textNumbers.keys.joinToString("|")}))".toRegex()

        return regex.findAll(line).map { match ->
            match.groupValues.filter { it.isNotEmpty() }
        }
        .flatten()
        .map {
            textNumbers[it] ?: it
        }

    }


}