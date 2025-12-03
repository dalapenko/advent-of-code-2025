package org.example.solution

import org.example.core.Puzzle
import kotlin.math.abs

private typealias RotationWay = Char
private typealias RotationDistance = Int
private typealias RotationInstruction = Pair<RotationWay, RotationDistance>

object Puzzle1 : Puzzle {

    override fun firstPartSolution(inputData: List<String>): String {
        return inputData.asSequence()
            .map(String::toRotationInstruction)
            .fold(
                initial = DIAL_START_POSITION to 0
            ) { (dialPosition, counter), (way, distance) ->
                val distance = if (way == LEFT_ROTATION_WAY) -distance else distance

                val newDialPosition = (dialPosition + distance).mod(100)
                val newCounter = if (newDialPosition == 0) counter + 1 else counter

                return@fold newDialPosition to newCounter
            }.solution()
    }

    override fun secondPartSolution(inputData: List<String>): String {
        return inputData.asSequence()
            .map(String::toRotationInstruction)
            .fold(
                initial = DIAL_START_POSITION to 0
            ) { (position, counter), (way, distance) ->
                val distance = if (way == LEFT_ROTATION_WAY) -distance else distance

                var newCounter = counter

                val rawPosition = position + distance

                val newPosition = rawPosition.mod(100)
                val transitions = rawPosition.floorDiv(100)

                val isTransitionFromZeroPosition = transitions < 0 && position == 0
                val isTransitionToZeroPosition = transitions > 0 && newPosition == 0

                if (isTransitionFromZeroPosition || isTransitionToZeroPosition) {
                    newCounter -= 1
                }

                if (newPosition == 0) {
                    newCounter += 1
                }

                newCounter += abs(transitions)

                return@fold newPosition to newCounter
            }.solution()
    }

    override val inputFileName: String = "puzzle_1_input.txt"
}

private fun String.toRotationInstruction(): RotationInstruction {
    return rotationWay() to rotationDistance()
}

private fun String.rotationWay(): RotationWay {
    return get(0)
}

private fun String.rotationDistance(): RotationDistance {
    return drop(1).toIntOrDefault(0)
}

private fun String.toIntOrDefault(default: Int): Int {
    return toIntOrNull() ?: default
}

private fun Pair<Int, Int>.solution(): String {
    return second.toString()
}

private const val LEFT_ROTATION_WAY: RotationWay = 'L'

private const val DIAL_START_POSITION = 50