package puzzles.day4

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day4Part1: Puzzle {

    private val input = ResourceFile("day_4_input.txt")

    override fun solve(): PuzzleResult =
        input.lines()
            .map { Card.parse(it) }
            .sumOf { it.points }
            .let { PuzzleResult(it) }
}

