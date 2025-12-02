package org.example.core

object Utils {

    fun readLinesFromResources(fileName: String): List<String> {
        val data = javaClass.classLoader.getResource(fileName)
            ?: throw IllegalArgumentException("Resource not found")

        return data.readText().lines()
    }
}