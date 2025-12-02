package org.example

import org.example.core.Puzzle
import org.example.core.Utils
import org.example.solution.Puzzle1

fun main() {
    val puzzle = Puzzle1.let(::PuzzlePrinter)

    puzzle.printFirstPuzzleAnswer()
    puzzle.printSecondPuzzleAnswer()
}

private class PuzzlePrinter(private val puzzle: Puzzle) {

    val puzzleInputData by lazy(LazyThreadSafetyMode.NONE) {
        Utils.readLinesFromResources(puzzle.inputFileName)
    }

    fun printFirstPuzzleAnswer() {
        println(puzzle.firstPuzzleSolution(puzzleInputData))
    }

    fun printSecondPuzzleAnswer() {
        println(puzzle.secondPuzzleSolution(puzzleInputData))
    }
}
