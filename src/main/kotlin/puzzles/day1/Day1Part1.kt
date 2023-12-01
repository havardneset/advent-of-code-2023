package puzzles.day1

import PuzzleResult
import Puzzle
import file.ResourceFile


class Day1Part1: Puzzle {

    override fun solve(): PuzzleResult =
        ResourceFile("day_1_input.txt").lines().map {
            findNumbers(it)
        }.sumOf {
            (it.first() + it.last()).toInt()
        }.let {
            PuzzleResult(1, 1, it)
        }

    private fun findNumbers(line: String): Sequence<String> {
        val regex = "\\d".toRegex()
        return regex.findAll(line).map { it.value }
    }

}