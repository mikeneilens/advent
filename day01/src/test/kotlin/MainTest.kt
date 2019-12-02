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

    @Test
    fun `When adding fuel for fuel A module of mass 14 requires 2 fuel`() {
        val fuelNeeded = calculateFuelplusFuel(14)
        assertEquals(2, fuelNeeded)
    }
    @Test
    fun `When adding fuel for fuel the fuel required for a module of mass 1969 is  966`() {
        val fuelNeeded = calculateFuelplusFuel(1969)
        assertEquals(966, fuelNeeded)
    }
    @Test
    fun `When adding fuel for fuel the fuel required for a module of mass 100756 is  50346`() {
        val fuelNeeded = calculateFuelplusFuel(100756)
        assertEquals(50346, fuelNeeded)
    }
    @Test
    fun `Using advent data for part two`() {
        val fuelNeeded = calculateAllFuelPlusFuel(testData)
        println(fuelNeeded)
    }


}