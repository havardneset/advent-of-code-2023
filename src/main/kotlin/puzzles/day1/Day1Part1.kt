package puzzles.day1

import PuzzleResult
import Puzzle
import file.ResourceFile


class Day1Part1: Puzzle {

    private val input = ResourceFile("day_1_input.txt")
    private val regex = "\\d".toRegex()

    override fun solve(): PuzzleResult =
        input.lines
            .map { findNumbers(it) }
            .map { it.first() + it.last() }
            .sumOf { it.toInt() }
            .let { PuzzleResult(it) }

    private fun findNumbers(line: String): Sequence<String> =
        regex.findAll(line).map { it.value }

}