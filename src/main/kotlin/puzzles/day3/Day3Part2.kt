package puzzles.day3

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day3Part2: Puzzle {

    private val input = ResourceFile("day_3_input.txt")
    private val emptyEnginePart = EnginePart(listOf(), listOf())

    override fun solve(): PuzzleResult {

        val engineParts = input.lines()
            .map { EnginePart.parse(it) }
            .withIndex()
            .associate { it.index to it.value }

        return engineParts.keys
            .map { key ->
                findGears(
                    engineParts.getOrElse(key) { emptyEnginePart },
                    engineParts.getOrElse(key - 1) { emptyEnginePart },
                    engineParts.getOrElse(key + 1) { emptyEnginePart }
                )
            }
            .filter { it.isNotEmpty() }
            .flatten()
            .sumOf { it.ratio }
            .let { PuzzleResult(it) }

    }

    private fun findGears(current: EnginePart, prev: EnginePart, next: EnginePart): List<Gear> {
        val gears = current.symbols
            .filter { it.symbol == "*" }
            .map { current.findNumbersNear(it) + prev.findNumbersNear(it) + next.findNumbersNear(it) }
            .filter { it.size == 2 }
            .map { Gear(it.first().value * it.last().value) }

        return gears
    }

}


