package org.example.solution

import org.example.core.Puzzle

object Puzzle4 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        var accessiblePoint = 0

        val height = inputData.size
        val width = inputData[0].length

        for (y in inputData.indices) {
            for (x in inputData[y].indices) {
                if (inputData[y][x] != PAPER_ROLL_CHAR) continue

                val nearestPaperRolls = nearestPoint(y, x).count { (y, x) ->
                    if (y !in 0 until height || x !in 0 until width) {
                        return@count false // out of bound
                    }

                    inputData[y][x] == PAPER_ROLL_CHAR
                }

                if (nearestPaperRolls > 3) continue

                accessiblePoint++
            }
        }
        return accessiblePoint.toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        var removedRolls = 0
        val rollMap = inputData.toRollMap() // possible without map to 2D IntArray, but it's more clear and optimized

        while (true) {
            var currentMapRemovedRolls = 0
            val height = rollMap.size
            val width = rollMap[0].size

            for (y in rollMap.indices) {
                for (x in rollMap[y].indices) {
                    if (rollMap[y][x] != PAPER_ROLL_DIGIT) continue

                    val nearestPaperRolls = nearestPoint(y, x).count { (y, x) ->
                        if (y !in 0 until height || x !in 0 until width) {
                            return@count false // out of bound
                        }

                        rollMap[y][x] == PAPER_ROLL_DIGIT
                    }

                    if (nearestPaperRolls > 3) continue

                    currentMapRemovedRolls++
                    rollMap[y][x] = DOT_DIGIT
                }
            }

            removedRolls += currentMapRemovedRolls

            if (currentMapRemovedRolls == 0) break
        }

        return removedRolls.toString()
    }

    override val inputFileName: String = "puzzle_4_input.txt"
}

private fun nearestPoint(y: Int, x: Int): Array<Point> {
    return arrayOf(
        Point(y = y - 1, x = x - 1), // top-left
        Point(y = y, x = x - 1),   // top
        Point(y = y + 1, x = x - 1), // top-right
        Point(y = y + 1, x = x), // right
        Point(y = y + 1, x = x + 1), // bottom-right
        Point(y = y, x = x + 1), // bottom
        Point(y = y - 1, x = x + 1), // bottom-left
        Point(y = y - 1, x = x) // left
    )
}

data class Point(val y: Int, val x: Int)

private fun List<String>.toRollMap(): Array<IntArray> {
    return Array(size) { y ->
        IntArray(this[y].length) { x ->
            if (this[y][x] == PAPER_ROLL_CHAR) 1 else 0
        }
    }
}

private const val PAPER_ROLL_CHAR = '@'
private const val DOT_CHAR = '.'

private const val PAPER_ROLL_DIGIT = 1
private const val DOT_DIGIT = 0
