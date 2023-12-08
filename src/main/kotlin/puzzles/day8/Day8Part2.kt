package puzzles.day8

import Puzzle
import PuzzleResult
import file.ResourceFile

class Day8Part2: Puzzle {

    private val input = ResourceFile("day_8_input.txt")

    override fun solve(): PuzzleResult {

        val instructions = input.lines.first().split("").filter { it.isNotBlank() }
        val network = input.lines
            .drop(2)
            .associate {
                val (key, directions) = it.split(" = ")
                val (left, right) = directions
                    .replace("(", "")
                    .replace(")", "")
                    .split(", ")

                key to Node(key, left, right)
            }

        val nodes = network.keys.filter { it.endsWith("A") }.map { network[it] }

        val steps = nodes.map { findPathForNode(it, network, instructions).toLong() }

        return PuzzleResult(findLCM(steps))
    }

    private fun findPathForNode(node: Node?, network: Map<String, Node>, instructions: List<String>): Int {
        var currentNode: Node? = node
        var counter = 0
        var steps = 0
        while (!currentNode?.key?.endsWith("Z")!!) {
            currentNode = if (instructions[counter] == "L") {
                network[currentNode.left]
            } else {
                network[currentNode.right]
            }
            steps++
            counter = if (counter + 1 < instructions.size) counter + 1 else 0
        }
        return steps
    }

    private fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }

    private fun lcm(a: Long, b: Long): Long {
        return a * b / gcd(a, b)
    }

    private fun findLCM(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1..<numbers.size) {
            result = lcm(result, numbers[i])
        }
        return result
    }

}