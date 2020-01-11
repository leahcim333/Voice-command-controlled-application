package pl.polsl.student.michaldomino.voice_command_controlled_application.logic

import java.util.*

class Word(private val value: String) {

    companion object {
        private const val DEFAULT_THRESHOLD = 80.0
    }

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

    private fun similarityWith(other: String): Double {
        return levenshtein(
            value.toLowerCase(Locale.getDefault()),
            other.toLowerCase(Locale.getDefault())
        )
    }

    private fun similarTo(other: String, threshold: Double = DEFAULT_THRESHOLD): Boolean {
        return similarityWith(other) >= threshold
    }

    fun <T> getMostSimilar(
        collection: Collection<T>,
        accessor: (T) -> String,
        threshold: Double = DEFAULT_THRESHOLD
    ): T? {
        val mostSimilar = collection.maxBy { this.similarityWith(accessor(it)) }
        if (mostSimilar != null && this.similarTo(accessor(mostSimilar), threshold))
            return mostSimilar
        return null
    }
}