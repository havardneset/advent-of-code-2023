package puzzles.day2

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day2Part1: Puzzle {

    private val input = ResourceFile("day_2_input.txt")
    private val maxRed = 12
    private val maxGreen = 13
    private val maxBlue = 14

    override fun solve(): PuzzleResult =
        input.lines()
            .map { Game.parse(it) }
            .filter { it.solvable(maxRed, maxGreen, maxBlue) }
            .sumOf { it.id }
            .let { PuzzleResult(it) }

}

