package puzzles.day9

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day9Part2: Puzzle {

    private val input = ResourceFile("day_9_input.txt")

    override fun solve(): PuzzleResult = input.lines
        .map { line -> line.split(" ").map { it.toLong() } }
        .sumOf { it.reversed().findNextInSequence() }
        .let { PuzzleResult(it) }

}