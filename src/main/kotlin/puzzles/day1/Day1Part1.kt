package puzzles.day1

import PuzzleResult
import Puzzle
import file.ResourceFile


class Day1Part1: Puzzle {

    override fun solve(): PuzzleResult {

        val file = ResourceFile("day_1_input.txt")
        val result = file.lines().map {
            findNumbers(it)
        }.sumOf {
            (it.first() + it.last()).toInt()
        }

        return PuzzleResult(1, 1, result)
    }

    private fun findNumbers(line: String): Sequence<String> {
        val regex = "\\d".toRegex()
        return regex.findAll(line).map { it.value }
    }



}