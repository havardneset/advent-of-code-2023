package puzzles.day6

fun findAmountOfPossibleWins(time: Long, distance: Long): Int =
    (1..<time)
        .map { (time - it) * it }
        .count { it > distance }