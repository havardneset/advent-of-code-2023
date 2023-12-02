package puzzles.day2

data class Game(val id: Int, val reds: Int, val greens: Int, val blues: Int) {

    fun solvable(maxReds: Int, maxGreen: Int, maxBlue: Int): Boolean =
        !(reds > maxReds || greens > maxGreen || blues > maxBlue)

    companion object {
        fun parse(line: String): Game {

            val gameAndRounds = line.split(": ")
            val id = gameAndRounds.first().replace("Game ", "").toInt()
            val gems = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0
            )

            val rounds = gameAndRounds.last().split("; ")
            rounds.forEach { round ->
                round.split(", ").forEach { set ->
                    val n = set.split(" ").first().toInt()
                    val type = set.split(" ").last()
                    if (n > gems[type]!!) {
                        gems[type] = n;
                    }
                }
            }

            return Game(id, gems["red"]!!, gems["green"]!!, gems["blue"]!!)
        }
    }

}
