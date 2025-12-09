package org.example.solution

import org.example.core.Puzzle
import kotlin.math.pow
import kotlin.math.sqrt

class Puzzle8(
    val availableConnection: Int = 1000
) : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        val sortedJunctionBoxes = inputData
            .map { line ->
                val (x, y, z) = line.split(COMMA_SEPARATOR).map(String::toInt)
                return@map JunctionBox(x, y, z)
            }
            .mapToCombinationPairs()
            .sortedWith { boxPair1, boxPair2 ->
                boxPair1.distance() compareTo boxPair2.distance()
            }

        val circuits = run breaking@{
            sortedJunctionBoxes.foldIndexed(listOf<Set<JunctionBox>>()) { index, circuits, junctions ->
                if (index == availableConnection) return@breaking circuits

                val junctions = junctions.toSet()
                val merges = mutableSetOf<Set<JunctionBox>>()
                val unmerges = mutableSetOf<Set<JunctionBox>>()

                for (circuit in circuits) {
                    if (circuit.intersect(junctions).isNotEmpty()) {
                        merges.add(circuit)
                    } else {
                        unmerges.add(circuit)
                    }
                }

                return@foldIndexed mutableListOf(merges.flatten().toSet() + junctions) + unmerges
            }
        }

        return circuits
            .sortedByDescending(Set<JunctionBox>::size)
            .take(3)
            .fold(1) { acc, circuit -> acc * circuit.size }.toString()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        val junctionBoxes =  inputData.map { line ->
            val (x, y, z) = line.split(COMMA_SEPARATOR).map(String::toInt)
            return@map JunctionBox(x, y, z)
        }

        val junctionsPairs = junctionBoxes
            .mapToCombinationPairs()
            .sortedWith { boxPair1, boxPair2 ->
                boxPair1.distance() compareTo boxPair2.distance()
            }

        var circuits: List<Set<JunctionBox>> = emptyList()

        return junctionsPairs.asSequence()
            .onEach { junctions ->
                val junctions = junctions.toSet()

                val merges = mutableSetOf<Set<JunctionBox>>()
                val unmerges = mutableSetOf<Set<JunctionBox>>()

                for (circuit in circuits) {
                    if (circuit.intersect(junctions).isNotEmpty()) {
                        merges.add(circuit)
                    } else {
                        unmerges.add(circuit)
                    }
                }

                circuits = mutableListOf(merges.flatten().union(junctions))
                    .apply { addAll(unmerges) }
            }.first {
                junctionBoxes.size == circuits.sizeOfFirst()
            }.let { (box, box1) ->
                1L * box.x * box1.x
            }.toString()
    }

    override val inputFileName: String = "puzzle_8_input.txt"
}

private fun Pair<JunctionBox, JunctionBox>.distance(): Double {
    return first.distanceTo(second)
}

private fun List<Set<JunctionBox>>.sizeOfFirst(): Int {
    return firstOrNull()?.size ?: 0
}

private fun JunctionBox.distanceTo(other: JunctionBox): Double {
    val xDistance = (other.x - x).pow(2)
    val yDistance = (other.y - y).pow(2)
    val zDistance = (other.z - z).pow(2)
    return sqrt(xDistance + yDistance + zDistance)
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

private fun <T> Pair<T, T>.toSet(): Set<T> {
    return setOf(first, second)
}

private data class JunctionBox(val x: Int, val y: Int, val z: Int)

private fun Int.pow(exponent: Int): Double {
    return toDouble().pow(exponent)
}

private const val COMMA_SEPARATOR = ','
