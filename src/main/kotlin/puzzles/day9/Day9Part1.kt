package puzzles.day9

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day9Part1: Puzzle {

    private val input = ResourceFile("day_9_input.txt")

    override fun solve(): PuzzleResult = input.lines
        .map { line -> line.split(" ").map { it.toLong() } }
        .sumOf { it.findNextInSequence() }
        .let { PuzzleResult(it) }

}

fun List<Long>.findNextInSequence(): Long {

    if (this.all { it == 0L }) {
        return 0
    }

    val nextSequence = mutableListOf<Long>()
    for (i in 0..<this.size - 1) {
        nextSequence.add((this[i + 1] - this[i]))
    }

    return this.last() + nextSequence.findNextInSequence()
}