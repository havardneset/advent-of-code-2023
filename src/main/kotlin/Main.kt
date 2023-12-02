import puzzles.day1.Day1Part1
import puzzles.day1.Day1Part2
import puzzles.day2.Day2Part1
import puzzles.day2.Day2Part2

fun main(args: Array<String>) {

    val puzzles = listOf(
        Day1Part1(), Day1Part2(),
        Day2Part1(), Day2Part2()
    )

    puzzles.forEach {
        println(it.solve())
    }

}