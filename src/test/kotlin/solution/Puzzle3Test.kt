package solution

import PuzzleTest
import org.example.solution.Puzzle3
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle3Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle3.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "357", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle3.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "3121910778619", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_3_test_input.txt"