import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `Solution for Part one`() {
        val route = solveProblem(sourceCode)
        assertEquals(232, route.size)
    }
}