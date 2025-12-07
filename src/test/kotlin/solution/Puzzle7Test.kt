package solution

import PuzzleTest
import org.example.solution.Puzzle7
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle7Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle7.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "21", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle7.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "40", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_7_test_input.txt"