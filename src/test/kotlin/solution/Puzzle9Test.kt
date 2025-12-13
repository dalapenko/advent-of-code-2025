package solution

import PuzzleTest
import org.example.solution.Puzzle9
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle9Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle9.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "50", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle9.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "24", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_9_test_input.txt"