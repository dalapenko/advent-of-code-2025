import org.example.core.Utils

interface PuzzleTest

@Suppress("UnusedReceiverParameter")
fun PuzzleTest.readLinesFromResources(fileName: String): List<String> {
    return Utils.readLinesFromResources(fileName)
}