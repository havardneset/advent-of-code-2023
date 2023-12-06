package puzzles.day6

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day6Part2: Puzzle {

    private val input = ResourceFile("day_6_input.txt")
    private val regex = Regex("\\d+\\d*")

    override fun solve(): PuzzleResult {
        val time = regex.findAll(input.lines.first())
            .map { it.value }
            .joinToString("")
            .toLong()

        val distance = regex.findAll(input.lines.last())
            .map { it.value }
            .joinToString("")
            .toLong()

        return PuzzleResult(findAmountOfPossibleWins(time, distance))
    }

}