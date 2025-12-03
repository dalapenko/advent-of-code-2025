package solution

import PuzzleTest
import org.example.solution.Puzzle2
import org.junit.jupiter.api.Test
import readLinesFromResources
import kotlin.test.assertEquals

class Puzzle2Test : PuzzleTest {

    @Test
    fun firstPartSolution() {
        val solution = Puzzle2.firstPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "1227775554", actual = solution)
    }

    @Test
    fun secondPartSolution() {
        val solution = Puzzle2.secondPartSolution(
            inputData = readLinesFromResources(INPUT_TEST_FILE_NAME)
        )

        assertEquals(expected = "4174379265", actual = solution)
    }

}

private const val INPUT_TEST_FILE_NAME = "puzzle_2_test_input.txt"