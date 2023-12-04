package puzzles.day4

data class Card(val id: Int, val winners: List<Int>, val numbers: List<Int>) {

    val matches = numbers
        .filter { winners.contains(it) }
        .size

    val points = if (matches > 0) 1 shl (matches - 1) else 0

    companion object {

        private val regex = Regex("\\d+\\d*")

        fun parse(line: String): Card {
            val (card, rest) = line.split(":")
            val (winners, numbers) = rest.split("|")

            val cardId = regex.findAll(card).first().value.toInt()

            val winnersList = regex.findAll(winners).map { it.value.toInt() }.toList()
            val numberList = regex.findAll(numbers).map { it.value.toInt() }.toList()

            return Card(cardId, winnersList, numberList)
        }
    }

}
