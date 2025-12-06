package org.example.solution

import org.example.core.Puzzle
import java.util.LinkedList

object Puzzle6 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        val operations = inputData.last().parseMathOperations()
        val calculationArray = LongArray(operations.size)

        val problems = inputData.dropLast(1)
            .map(String::parseNumbers)

        for (line in problems) {
            line.forEachIndexed { index, number ->
                when (operations[index]) {
                    ADD_ACTION -> calculationArray[index] += number
                    MULTIPLIED_ACTION -> {
                        if (calculationArray[index] == 0L) {
                            calculationArray[index] = 1L
                        }
                        calculationArray[index] *= number
                    }
                }
            }
        }

        return calculationArray.sum().toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        val operations = inputData.last().parseMathOperations()

        return inputData
            .dropLast(1)
            .transpose()
            .fold(linkedListOf(linkedListOf<Long>())) { acc, colStr ->
                return@fold acc.apply {
                    if (colStr.isBlank()) {
                        add(linkedListOf())
                    } else {
                        last().add(colStr.trim().toLong())
                    }
                }
            }.sumOfIndexed { index, values ->
                when (operations[index]) {
                    ADD_ACTION -> values.sum()
                    else -> values.fold(1L) { acc, lng -> acc * lng }
                }
            }.toString()
    }

    override val inputFileName: String = "puzzle_6_input.txt"
}

private fun String.parseNumbers(): LongArray {
    return split(SPACE_DELIMITER)
        .filter(String::isNotEmpty)
        .map(String::toLong)
        .toLongArray()
}

private fun String.parseMathOperations(): CharArray {
    return split(SPACE_DELIMITER)
        .filter(String::isNotEmpty)
        .map(String::single)
        .toCharArray()
}

private fun List<String>.transpose(): List<String> {
    val maxWidth = maxOf(String::length)

    return (0 until maxWidth).map { colIndex ->
        map { line ->
            line.getOrNull(colIndex) ?: SPACE_DELIMITER
        }.joinToString(EMPTY_SEPARATOR)
    }
}

private fun <T> linkedListOf(vararg items: T): LinkedList<T> {
    return LinkedList<T>().apply {
        for (i in items) {
            add(i)
        }
    }
}

private inline fun <T> Iterable<T>.sumOfIndexed(selector: (index: Int, T) -> Long): Long {
    var sum: Long = 0.toLong()

    forEachIndexed { index, element ->
        sum += selector(index, element)
    }

    return sum
}

private const val SPACE_DELIMITER = ' '
private const val EMPTY_SEPARATOR = ""

private const val ADD_ACTION = '+'
private const val MULTIPLIED_ACTION = '*'