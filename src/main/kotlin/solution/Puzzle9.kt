package org.example.solution

import org.example.core.Puzzle
import kotlin.collections.indices
import kotlin.math.abs

private data class Point2D(val x: Int, val y: Int)

object Puzzle9 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        return inputData.toPoints()
            .mapToCombinationPairs()
            .map(Pair<Point2D, Point2D>::size)
            .maxOf { it }
            .toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        return "" // 1465767840
    }

    override val inputFileName: String = "puzzle_9_input.txt"
}

private fun Pair<Point2D, Point2D>.size(): Long {
    return 1L * (abs(second.x - first.x) + 1) * (abs(second.y - first.y) + 1)
}

private fun <R> List<R>.mapToCombinationPairs(): List<Pair<R, R>> {
    val source = this
    return buildList {
        for (i in source.indices) {
            for (j in i + 1 until source.size) {
                add(source[i] to source[j])
            }
        }
    }
}

private fun List<String>.toPoints(): List<Point2D> {
    return map { line ->
        val (x, y) = line.split(COMMA_SPLITTER)
        Point2D(x = x.toInt(), y = y.toInt())
    }
}

private const val COMMA_SPLITTER = ','