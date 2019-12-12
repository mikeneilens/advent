import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.coroutines.coroutineContext

class MainTest {

    @Test
    fun`Sampe Code for Part one`() {
        val moon1Position = Position(x=-1, y=0, z=2)
        val moon2Position = Position(x=2, y=-10, z=-7)
        val moon3Position = Position(x=4, y=-8, z=8)
        val moon4Position = Position(x=3, y=5, z=-1)

        val moonPositions = listOf(moon1Position,moon2Position,moon3Position,moon4Position)
        val moonVelocities = listOf(Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0))

        val (positions, velocities) = moveMoons(moonPositions, moonVelocities, 10)

        assertEquals(179, totalEnergy(positions,velocities))
    }
 @Test
    fun`Sampe Code2 for Part one`() {
        val moon1Position = Position(x=-8, y=-10, z=0)
        val moon2Position = Position(x=5, y=5, z=10)
        val moon3Position = Position(x=2, y=-7, z=3)
        val moon4Position = Position(x=9, y=-8, z=-3)

        val moonPositions = listOf(moon1Position,moon2Position,moon3Position,moon4Position)
        val moonVelocities = listOf(Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0))

        val (positions, velocities) = moveMoons(moonPositions, moonVelocities, 100)

        assertEquals(1940, totalEnergy(positions,velocities))
    }

    @Test
    fun`Answer for Part one`() {
        val moon1Position = Position(x=4, y=12, z=13)
        val moon2Position = Position(x=-9, y=14, z=-3)
        val moon3Position = Position(x=-7, y=-1, z=2)
        val moon4Position = Position(x=-11, y=17, z=-1)

        val moonPositions = listOf(moon1Position,moon2Position,moon3Position,moon4Position)
        val moonVelocities = listOf(Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0))

        val (positions, velocities) = moveMoons(moonPositions, moonVelocities, 1000)

        assertEquals(5350, totalEnergy(positions,velocities))
    }

    @Test
    fun`Sample code for Part two`() {

        val moon1Position = Position(x=4, y=12, z=13)
        val moon2Position = Position(x=-9, y=14, z=-3)
        val moon3Position = Position(x=-7, y=-1, z=2)
        val moon4Position = Position(x=-11, y=17, z=-1)

        val moonPositions = listOf(moon1Position,moon2Position,moon3Position,moon4Position)
        val moonVelocities = listOf(Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0),Velocity(0,0,0))

        val result=findSteps(moonPositions, moonVelocities)

        assertEquals(467034091553512,result)
    }
    @Test
    fun `greatest common factor of 24, 108 and 42 is 6`() {
        assertEquals(6 ,listOf(24,108,42).greatestCommonFactor())
    }

}