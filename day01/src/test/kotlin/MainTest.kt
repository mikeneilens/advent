import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `mass of 12 needs 2 fuel`() {
        val fuelNeeded = calculateFuel(12)
        assertEquals(2, fuelNeeded)

    }
    @Test
    fun `mass of 14 needs 2 fuel`() {
        val fuelNeeded = calculateFuel(12)
        assertEquals(2, fuelNeeded)

    }
    @Test
    fun `For a mass of 1969, the fuel required is 654`() {
        val fuelNeeded = calculateFuel(1969)
        assertEquals(654, fuelNeeded)

    }
    @Test
    fun `For a mass of 100756, the fuel required is 33583`() {
        val fuelNeeded = calculateFuel(100756)
        assertEquals(33583, fuelNeeded)

    }
    @Test
    fun `For a mass of 100756 and 1969 the fuel required is 654 + 33583`() {
        val fuelNeeded = calculateAllFuel(listOf(100756,1969))
        assertEquals(654 + 33583, fuelNeeded)
    }
    @Test
    fun `Using advent data`() {
        val fuelNeeded = calculateAllFuel(testData)
        println(fuelNeeded)
    }
}