package org.example.solution

import org.example.core.Puzzle
import kotlin.collections.drop
import kotlin.collections.first
import kotlin.math.max
import kotlin.math.min

private typealias Graph<T> = MutableMap<T, MutableList<T>>

object Puzzle7 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        val startPosition = inputData.first().indexOf(START_CHAR)
        val diagram = inputData.drop(1)

        return diagram.fold(
            setOf(startPosition) to 0
        ) { (manifoldIndexes, splitCounter), line ->
            val splitterPositions = line.indexesOf(SPLITTER_CHAR)

            if (splitterPositions.isEmpty()) {
                return@fold manifoldIndexes to splitCounter
            }

            val splitPoints = splitterPositions.filter(manifoldIndexes::contains)
            val manifoldWithoutChanges = manifoldIndexes - splitterPositions.toSet()

            val manifoldIndexes = buildSet {
                addAll(manifoldWithoutChanges)

                splitPoints.forEach { splitter ->
                    add(max(0, splitter - 1))
                    add(min(splitter + 1, line.lastIndex))
                }
            }
            val splitCounter = splitCounter + splitPoints.size

            return@fold manifoldIndexes to splitCounter
        }.second.toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        val startPosition = inputData.first().indexOf(START_CHAR)
        val diagram = inputData.drop(1)

        val initialManifold = Manifold(row = 0, column = startPosition)

        val graph: Graph<Manifold> = mutableMapOf(
            initialManifold to mutableListOf()
        )

        diagram.forEachIndexed { index, line ->
            val splitPositions = line.indexesOf(SPLITTER_CHAR)

            if (splitPositions.isEmpty()) {
                graph.keys
                    .filter { manifold -> manifold.row == index }
                    .forEach(graph::addDownstreamManifold)
            } else {
                val previousLineManifold = graph.keys.filter { tachyon -> tachyon.row == index }
                val manifoldWithoutChanges = previousLineManifold.filter { it.column !in splitPositions }

                for (splitter in splitPositions) {
                    previousLineManifold
                        .filter { manifold -> manifold.column == splitter }
                        .forEach { manifold ->
                            graph.addSplitManifold(
                                root = manifold,
                                splitColumn = splitter,
                                widthLimit = line.lastIndex
                            )
                        }
                }

                manifoldWithoutChanges.forEach(graph::addDownstreamManifold)
            }
        }

        return graph.countAllPaths(initialManifold).toString()
    }

    override val inputFileName: String = "puzzle_7_input.txt"
}

private fun Graph<Manifold>.addDownstreamManifold(root: Manifold) {
    val nextManifold = Manifold(
        row = root.row + 1,
        column = root.column
    )
    getOrPut(root, ::mutableListOf).add(nextManifold)
    put(nextManifold, mutableListOf())
}

private fun Graph<Manifold>.addSplitManifold(root: Manifold, splitColumn: Int, widthLimit: Int) {
    val leftManifold = Manifold(
        row = root.row + 1,
        column = max(0, splitColumn - 1)
    )
    val rightManifold = Manifold(
        row = root.row + 1,
        column = min(widthLimit, splitColumn + 1)
    )

    getOrPut(root, ::mutableListOf).apply {
        add(leftManifold)
        add(rightManifold)
    }

    put(leftManifold, mutableListOf())
    put(rightManifold, mutableListOf())
}

private fun <T> Graph<T>.countAllPaths(start: T): Long {
    val memo = mutableMapOf<T, Long>()

    fun dfs(node: T): Long = memo.getOrPut(node) {
        val neighbors = get(node) ?: emptyList()

        return@getOrPut if (neighbors.isNotEmpty()) neighbors.sumOf(::dfs) else 1L
    }

    return dfs(start)
}

private fun String.indexesOf(char: Char): List<Int> {
    var lastFoundIndex = 0

    return buildList {
        while (lastFoundIndex != INDEX_NOT_FOUND) {
            lastFoundIndex = this@indexesOf.indexOf(char, startIndex = lastFoundIndex + 1)
            if (lastFoundIndex != INDEX_NOT_FOUND) add(lastFoundIndex)
        }
    }
}

private data class Manifold(val column: Int, val row: Int)

private const val START_CHAR = 'S'
private const val SPLITTER_CHAR = '^'
private const val INDEX_NOT_FOUND = -1
