import puzzles.day1.Day1Part1
import puzzles.day1.Day1Part2
import puzzles.day2.Day2Part1
import puzzles.day2.Day2Part2
import puzzles.day3.Day3Part1
import puzzles.day3.Day3Part2
import puzzles.day4.Day4Part1
import puzzles.day4.Day4Part2
import kotlin.time.measureTimedValue

fun main() {

    val puzzles = listOf(
        Day1Part1(), Day1Part2(),
        Day2Part1(), Day2Part2(),
        Day3Part1(), Day3Part2(),
        Day4Part1(), Day4Part2()
    )

    puzzles.forEach { puzzle ->
        val (result, timeTaken) = measureTimedValue {
            puzzle.solve()
        }
        println(
            """
                ${puzzle.javaClass.simpleName}
                Answer: $result
                Time: $timeTaken
                -------------------
            """.trimIndent())
    }

}