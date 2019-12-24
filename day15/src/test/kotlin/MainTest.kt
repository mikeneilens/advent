import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `Solution for Part one`() {
        val (route,map) = solveProblemOne(sourceCode)
        assertEquals(232, route.size)
    }
    @Test
    fun `Solution for Part two`() {
        val minutes = solveProblemTwo(sourceCode)
        assertEquals(320, minutes)
    }
}