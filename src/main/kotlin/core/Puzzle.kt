package org.example.core

interface Puzzle {
    val inputFileName: String

    fun firstPartSolution(inputData: List<String>): String
    fun secondPartSolution(inputData: List<String>): String
}
