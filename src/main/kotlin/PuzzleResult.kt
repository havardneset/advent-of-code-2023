data class PuzzleResult(val day: Int, val part: Int, val result: Any) {

    override fun toString(): String {
        return "Result of Day $day [Part $part] -> $result"
    }

}