package puzzles.day3

private val numberRegex = Regex("\\d+\\d*")
private val symbolRegex = Regex("[*#+$/&%=@-]")

data class EnginePart(val numbers: List<Number>, val symbols: List<Symbol>) {
    fun containsSymbolNear(number: Number): Boolean =
        symbols.any { number.indexes.contains(it.index) }

    fun findNumbersNear(symbol: Symbol): List<Number> =
        numbers.filter { it.indexes.contains(symbol.index) }

    companion object {
        fun parse(line: String): EnginePart {

            val numbers = numberRegex.findAll(line)
                .map { Number(it.value.toInt(), (it.range.first - 1 .. it.range.last + 1).toList()) }
                .toList()

            val symbols = symbolRegex.findAll(line)
                .map { Symbol(it.value, it.range.first) }
                .toList()

            return EnginePart(numbers, symbols)

        }

    }
}

data class Gear(val ratio: Int)
data class Number(val value: Int, val indexes: List<Int>)
data class Symbol(val symbol: String, val index: Int)