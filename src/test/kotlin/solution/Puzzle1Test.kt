package solution

import PuzzleTest
import org.example.solution.Puzzle1
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle1Test : PuzzleTest {

    @Test
    fun firstPuzzleSolution() {
        val solution = Puzzle1.firstPuzzleSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "3", actual = solution)
    }

    @Test
    fun secondPuzzleSolution() {
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_1_test_input.txt"