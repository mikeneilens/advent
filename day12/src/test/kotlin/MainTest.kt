import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun`Sampe Code for Part one`() {
        val moon1 = Moon(Position(x=-1, y=0, z=2))
        val moon2 = Moon(Position(x=2, y=-10, z=-7))
        val moon3 = Moon(Position(x=4, y=-8, z=8))
        val moon4 = Moon(Position(x=3, y=5, z=-1))

        val moonPositions = listOf(moon1,moon2,moon3,moon4)

        val moons = moveMoons(moonPositions, 10)

        assertEquals(179, totalEnergy(moons))
    }
 @Test
    fun`Sampe Code2 for Part one`() {
        val moon1 = Moon(Position(x=-8, y=-10, z=0))
        val moon2 = Moon(Position(x=5, y=5, z=10))
        val moon3 = Moon(Position(x=2, y=-7, z=3))
        val moon4 = Moon(Position(x=9, y=-8, z=-3))

        val moonPositions = listOf(moon1,moon2,moon3,moon4)

        val moons = moveMoons(moonPositions, 100)

        assertEquals(1940, totalEnergy(moons))
    }

    @Test
    fun`Answer for Part one`() {
        val moon1 = Moon(Position(x=4, y=12, z=13))
        val moon2 = Moon(Position(x=-9, y=14, z=-3))
        val moon3 = Moon(Position(x=-7, y=-1, z=2))
        val moon4 = Moon(Position(x=-11, y=17, z=-1))

        val moonPositions = listOf(moon1,moon2,moon3,moon4)

        val moons = moveMoons(moonPositions, 1000)

        assertEquals(5350, totalEnergy(moons))
    }

    @Test
    fun`Sample code for Part two`() {

        val moon1 = Moon(Position(x=4, y=12, z=13))
        val moon2 = Moon(Position(x=-9, y=14, z=-3))
        val moon3 = Moon(Position(x=-7, y=-1, z=2))
        val moon4 = Moon(Position(x=-11, y=17, z=-1))

        val moonPositions = listOf(moon1,moon2,moon3,moon4)

        val result=findSteps(moonPositions)

        assertEquals(467034091553512,result)
    }
    @Test
    fun `greatest common factor of 24, 108 and 42 is 6`() {
        assertEquals(6 ,listOf(24,108,42).greatestCommonFactor())
    }

}