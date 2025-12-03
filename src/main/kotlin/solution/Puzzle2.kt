package org.example.solution

import org.example.core.Puzzle
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

object Puzzle2 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        return inputData
            .asSequence()
            .flatMap(String::commaSplit)
            .filter(String::isNotBlank)
            .flatMap(String::toLongRange)
            .sumOf { value ->
                val isOddDigitCount = value.digitCount() % 2 != 0

                if (isOddDigitCount) return@sumOf 0

                val (left, right) = value.split(parts = 2)

                if (left == right) value else 0
            }.toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        val partitions = listOf(7, 5, 3, 2)

        return inputData
            .asSequence()
            .flatMap(String::commaSplit)
            .filter(String::isNotBlank)
            .flatMap(String::toLongRange)
            .sumOf { value ->
                val valueDigits = value.digitCount()

                partitions
                    .filter(valueDigits::isSplitPossible)
                    .forEach { parts ->
                        val partsSet = mutableSetOf<Int>()

                        value.splitTo(parts, partsSet)

                        if (partsSet.size == 1) {
                            return@sumOf value
                        }
                    }

                return@sumOf 0
            }.toString()
    }

    override val inputFileName: String = "puzzle_2_input.txt"
}

private fun String.toLongRange(): LongRange {
    val start = substringBefore(RANGE_SEPARATOR)
    val end = substringAfter(RANGE_SEPARATOR)

    return start.toLong()..end.toLong()
}

private fun Long.digitCount(): Int {
    return when (this) {
        0L -> 1
        else -> log10(abs(toDouble())).toInt() + 1
    }
}

private fun Int.isSplitPossible(parts: Int): Boolean {
    return this % parts == 0
}

private fun String.commaSplit(): List<String> {
    return split(INPUT_SEPARATOR)
}

private fun Long.split(parts: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    splitTo(parts, resultList)
    return resultList.reversed()
}

private fun Long.splitTo(parts: Int, target: MutableCollection<Int>) {
    var tail = this
    val divisor = 10.0.pow(digitCount() / parts).toLong()

    repeat(parts) {
        target.add((tail % divisor).toInt())
        tail /= divisor
    }
}

private const val INPUT_SEPARATOR = ","
private const val RANGE_SEPARATOR = "-"