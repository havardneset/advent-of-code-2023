package puzzles.day2

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day2Part2: Puzzle {

    private val input = ResourceFile("day_2_input.txt")

    override fun solve(): PuzzleResult =
        input.lines()
            .map { Game.parse(it) }
            .map { it.reds * it.greens * it.blues }
            .sumOf { it }
            .let { PuzzleResult(2, 2, it) }

}