package puzzles.day1

import PuzzleResult
import Puzzle
import file.ResourceFile


class Day1Part1: Puzzle {

    private var input = ResourceFile("day_1_input.txt")

    override fun solve(): PuzzleResult =
        input.lines()
            .map { findNumbers(it) }
            .map { it.first() + it.last() }
            .sumOf { it.toInt() }
            .let { PuzzleResult(1, 1, it) }

    private fun findNumbers(line: String): Sequence<String> {
        val regex = "\\d".toRegex()
        return regex.findAll(line).map { it.value }
    }

}