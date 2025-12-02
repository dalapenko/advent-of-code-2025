package org.example.core

interface Puzzle {
    val inputFileName: String

    fun firstPuzzleSolution(inputData: List<String>): String
    fun secondPuzzleSolution(inputData: List<String>): String
}
