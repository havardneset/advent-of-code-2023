package puzzles.day3

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day3Part1: Puzzle {

    private val input = ResourceFile("day_3_input.txt")

    override fun solve(): PuzzleResult {

        val engineParts = input.lines()
            .map { EnginePart.parse(it) }
            .withIndex()
            .associate { it.index to it.value }

        return engineParts.keys
            .map { key ->
                findValidNumbers(
                    engineParts.getOrElse(key) { EnginePart.EMPTY },
                    engineParts.getOrElse(key - 1) { EnginePart.EMPTY },
                    engineParts.getOrElse(key + 1) { EnginePart.EMPTY }
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


