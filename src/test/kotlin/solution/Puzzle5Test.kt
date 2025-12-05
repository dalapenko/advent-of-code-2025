package solution

import PuzzleTest
import org.example.solution.Puzzle5
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle5Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle5.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "3", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle5.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "14", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_5_test_input.txt"