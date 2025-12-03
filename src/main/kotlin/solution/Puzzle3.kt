package org.example.solution

import org.example.core.Puzzle

object Puzzle3 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        return inputData
            .map(String::toIntArray)
            .sumOf { bank ->
                val bankMaxSuffix = bank.findMaxSuffix()
                val getCombination: (Int) -> Int = { index ->
                    bank[index] * 10 + bankMaxSuffix[index]
                }
                var maxCombination = getCombination(0)

                for (index in 1 until bank.lastIndex) {
                    maxCombination = maxOf(maxCombination, getCombination(index))
                }

                return@sumOf maxCombination
            }.toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        return inputData
            .map(String::toIntArray)
            .sumOf { bank ->
                val bankLength = bank.size
                val maxCombination = StringBuilder()

                var windowStartIndex = 0

                repeat(BATTERIES_IN_JOLTAGE) { index ->
                    val windowEndIndex = bankLength - (BATTERIES_IN_JOLTAGE - index)

                    var (maxDigitIndex, maxDigit) = windowStartIndex to bank[windowStartIndex]

                    for (index in windowStartIndex + 1..windowEndIndex) {
                        val digitAtIndex = bank[index]

                        if (digitAtIndex > maxDigit) {
                            maxDigitIndex = index
                            maxDigit = digitAtIndex
                        }
                    }

                    windowStartIndex = maxDigitIndex + 1
                    maxCombination.append(maxDigit)
                }

                "$maxCombination".toLong()
            }.toString()
    }

    override val inputFileName: String = "puzzle_3_input.txt"
}

private fun IntArray.findMaxSuffix(): IntArray {
    var max = 0
    val suffixIntArray = IntArray(size - 1)

    for (index in lastIndex - 1 downTo 0) {
        max = maxOf(max, get(index + 1))
        suffixIntArray[index] = max
    }
    return suffixIntArray
}

private fun String.toIntArray(): IntArray {
    return map(Char::digitToInt).toIntArray()
}

private const val BATTERIES_IN_JOLTAGE = 12