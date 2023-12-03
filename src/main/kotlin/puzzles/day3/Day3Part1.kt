package puzzles.day3

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day3Part1: Puzzle {

    private val input = ResourceFile("day_3_input.txt")
    private val emptyEnginePart = EnginePart(listOf(), listOf())

    override fun solve(): PuzzleResult {

        val engineParts = input.lines()
            .map { EnginePart.parse(it) }
            .withIndex()
            .associate { it.index to it.value }

        return engineParts.keys
            .map { key ->
                findValidNumbers(
                    engineParts.getOrElse(key) { emptyEnginePart },
                    engineParts.getOrElse(key - 1) { emptyEnginePart },
                    engineParts.getOrElse(key + 1) { emptyEnginePart }
                )
            }
            .flatten()
            .sum()
            .let { PuzzleResult(it) }

    }

    private fun findValidNumbers(current: EnginePart, prev: EnginePart, next: EnginePart): List<Int> {
        return current.numbers
            .filter {
                current.containsSymbolNear(it)
                || prev.containsSymbolNear(it)
                || next.containsSymbolNear(it)
            }
            .map { it.value }
    }

}


