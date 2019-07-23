package pl.polsl.student.michaldomino.voice_command_controlled_application.data.logic

class Command(private val possibleMatches: ArrayList<String>?) {

    private val DEFAULT_THRESHOLD = 80.0

    // source: https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance
    private fun levenshtein(lhs: CharSequence, rhs: CharSequence): Double {
        val lhsLength = lhs.length
        val rhsLength = rhs.length

        var cost = IntArray(lhsLength + 1) { it }
        var newCost = IntArray(lhsLength + 1) { 0 }

        for (i in 1..rhsLength) {
            newCost[0] = i

            for (j in 1..lhsLength) {
                val editCost = if (lhs[j - 1] == rhs[i - 1]) 0 else 1

                val costReplace = cost[j - 1] + editCost
                val costInsert = cost[j] + 1
                val costDelete = newCost[j - 1] + 1

                newCost[j] = minOf(costInsert, costDelete, costReplace)
            }

            val swap = cost
            cost = newCost
            newCost = swap
        }

        return (1.0 - cost[lhsLength].toDouble() / lhsLength.toDouble()) * 100.0
    }

    fun similarTo(string: String, threshold: Double = DEFAULT_THRESHOLD): Boolean {
        val a = possibleMatches!!.any { levenshtein(it, string) >= threshold }
        val b = possibleMatches.firstOrNull { levenshtein(it, string) >= threshold }
        val c = possibleMatches.filter { levenshtein(it, string) >= threshold }
        var d: ArrayList<Double> = arrayListOf()
        for (it in possibleMatches) {
            d.add(d.lastIndex, levenshtein(it, string))
        }
        val f = 0
        return false
    }
}