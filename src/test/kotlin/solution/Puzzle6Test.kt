package solution

import PuzzleTest
import org.example.solution.Puzzle6
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle6Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle6.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "4277556", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle6.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "3263827", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_6_test_input.txt"