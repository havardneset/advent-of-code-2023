package puzzles.day6

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day6Part1: Puzzle {

    private val input = ResourceFile("day_6_input.txt")
    private val regex = Regex("\\d+\\d*")

    override fun solve(): PuzzleResult {
        val times = regex.findAll(input.lines.first()).map { it.value.toInt() }.toList()
        val distances = regex.findAll(input.lines.last()).map { it.value.toInt() }.toList()

        return times
            .mapIndexed{ index, time -> findAmountOfPossibleWins(time.toLong(), distances[index].toLong()) }
            .reduce { acc, value -> acc * value }
            .let { PuzzleResult(it) }
    }



}