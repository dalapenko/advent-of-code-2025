package solution

import PuzzleTest
import org.example.solution.Puzzle4
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle4Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle4.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "13", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle4.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "43", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_4_test_input.txt"