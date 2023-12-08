package puzzles.day8

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day8Part1: Puzzle {

    private val input = ResourceFile("day_8_input.txt")

    override fun solve(): PuzzleResult {

        val instructions = input.lines.first().split("").filter { it.isNotBlank() }
        val network = input.lines
            .drop(2)
            .associate {
                val (key, directions) = it.split(" = ")
                val (left, right) = directions
                    .removeSurrounding("(", ")")
                    .split(", ")

                key to Node(key, left, right)
            }

        var counter = 0
        var steps = 0
        var startNode = network["AAA"]
        val endNode = network["ZZZ"]

        while (startNode != endNode) {
            startNode = if (instructions[counter] == "L") {
                network[startNode!!.left]
            } else {
                network[startNode!!.right]
            }
            counter = if (counter + 1 < instructions.size) counter + 1 else 0
            steps++

        }

        return PuzzleResult(steps)
    }

}

data class Node(val key: String, val left: String, val right: String)