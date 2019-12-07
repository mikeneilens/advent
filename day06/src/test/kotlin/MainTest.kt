import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainTest {
    @BeforeEach
    fun resetMutableMap() {
        orbitRelationship = mutableMapOf<Orbiter,Centre>()
    }
    @Test
    fun `No of Orbits of C for COM)C is 1`() {
        val orbitRelationship = mutableMapOf("C" to "COM")
        val noOfOrbits = noOfDirectAndIndirectOrbits(orbitRelationship,"C")
        assertEquals(1,noOfOrbits)
    }
    @Test
    fun `No of Orbits of D for COM)C)D is 2`() {
        val orbitRelationship = mutableMapOf("C" to "COM", "D" to "C")
        val noOfOrbits = noOfDirectAndIndirectOrbits(orbitRelationship,"D")
        assertEquals(2,noOfOrbits)
    }

    @Test
    fun`test something`() {
        val orbits = (
                "B)C," +
                "C)D," +
                "D)E," +
                "COM)B," +
                "E)F," +
                "B)G," +
                "G)H," +
                "D)I," +
                "E)J," +
                "J)K," +
                "K)L").split(",")
        val totalOrbits = countOrbits(orbits)
        assertEquals(42,totalOrbits)
    }
    @Test
    fun `Day 06 part one`() {
        val orbits = testData.split(",")
        val totalOrbits = countOrbits(orbits)
        println(totalOrbits)
    }
    @Test
    fun `Santas orbit takes 4 steps to reach`() {
        val orbits = ("COM)B,"+
                "B)C,"+
        "C)D,"+
        "D)E,"+
        "E)F,"+
        "B)G,"+
        "G)H,"+
        "D)I,"+
        "E)J,"+
        "J)K,"+
        "K)L,"+
        "K)YOU,"+
        "I)SAN").split(",")
        val steps = calcStepsToSantasOrbit(orbits)
        assertEquals(4, steps)
    }
    @Test
    fun `Day 6 part two`() {
        val orbits = testData.split(",")
        val steps = calcStepsToSantasOrbit(orbits)
       print("day 6 part two $steps")
    }

}