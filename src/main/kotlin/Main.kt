package org.example

import org.example.core.Puzzle
import org.example.core.Utils
import org.example.solution.Puzzle6

fun main() {
    val puzzle = Puzzle6.let(::PuzzlePrinter)

    puzzle.printFirstPartAnswer()
    puzzle.printSecondPartAnswer()
}

private class PuzzlePrinter(private val puzzle: Puzzle) {

    val puzzleInputData by lazy(LazyThreadSafetyMode.NONE) {
        Utils.readLinesFromResources(puzzle.inputFileName)
    }

    fun printFirstPartAnswer() {
        println(puzzle.firstPartSolution(puzzleInputData))
    }

    fun printSecondPartAnswer() {
        println(puzzle.secondPartSolution(puzzleInputData))
    }
}
