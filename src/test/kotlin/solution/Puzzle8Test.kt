package solution

import PuzzleTest
import org.example.solution.Puzzle8
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle8Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle8(availableConnection = 10).firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "40", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle8().secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "25272", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_8_test_input.txt"