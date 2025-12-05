package org.example.solution

import org.example.core.Puzzle
import kotlin.math.max

object Puzzle5 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        val (freshIngredientRanges, availableFreshIngredientIds) = inputData.toIngredientsData()

        val freshIngredientLongRanges = freshIngredientRanges.map(String::toLongRange)

        return availableFreshIngredientIds.map(String::toLong)
            .count { id ->
                freshIngredientLongRanges.any { range -> id in range }
            }
            .toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        val freshIngredientRanges = inputData.takeOnlyFreshRanges()
        return freshIngredientRanges
            .map(String::toLongRange)
            .fold(mutableListOf<LongRange>()) { uniqueRanges, ingredientRange ->
                var range = ingredientRange
                var positionIndex = -1

                val iterator = uniqueRanges.iterator()
                while(iterator.hasNext()) {
                    val existing = iterator.next()
                    positionIndex++

                    if(range.last < existing.first - 1) {
                        uniqueRanges.add(positionIndex, range)
                        return@fold uniqueRanges
                    }

                    if(range.first < existing.first) {
                        if(range.last < existing.last) {
                            range = LongRange(range.first, existing.last)
                        }

                        iterator.remove()
                        positionIndex--
                        continue
                    }

                    if(range.last <= existing.last) return@fold uniqueRanges

                    if(range.first <= existing.last + 1) {
                        range = LongRange(existing.first, range.last)
                        iterator.remove()
                        positionIndex--
                    }
                }

                uniqueRanges.add(range)

                return@fold uniqueRanges
            }.sumOf(LongRange::length).toString()
    }

    override val inputFileName: String = "puzzle_5_input.txt"
}

private fun List<String>.toIngredientsData(): Pair<List<String>, List<String>> {
    val separatorIndex = indexOfFirst(String::isEmpty)

    val freshIngredientRanges = subList(
        fromIndex = 0,
        toIndex = separatorIndex
    )

    val availableFreshIngredientIds = subList(
        fromIndex = separatorIndex + 1,
        toIndex = size
    )

    return freshIngredientRanges to availableFreshIngredientIds
}

private fun List<String>.takeOnlyFreshRanges(): List<String> {
    val separatorIndex = indexOfFirst(String::isEmpty)
    return subList(
        fromIndex = 0,
        toIndex = separatorIndex
    )
}

private fun String.toLongRange(): LongRange {
    val (start, end) = split(RANGE_DELIMITER).map(String::toLong)
    return start..end
}

private fun LongRange.length(): Long {
    return max(0L, last - first + 1)
}


private const val RANGE_DELIMITER = "-"
