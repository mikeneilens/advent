import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class MainTest
{
  //  @Test
    fun `Get test data`() {
        val input = File("day10.txt").readLines()
    }
 //   @Test
    fun `example one for day 10`() {
        val data = listOf(   ".#..#",
                                        ".....",
                                        "#####",
                                        "....#",
                                        "...##")
        data.convertToMap().print()
    }
    @Test
    fun `Star at position 3,4 can see each other asteroids except one at 1,0`() {
        val data = listOf(
            ".#..#",
            ".....",
            "#####",
            "....#",
            "...##")
        val map = data.convertToMap()
        val sourcePosition = Position(3,4)
        //val sourcePosition = Position(0,2)

        assertEquals(8,map.asteroidsVisible(sourcePosition))
    }

    @Test
    fun `Star at position 3,4 can see the most other asteroids`() {
        val data = listOf(
            ".#..#",
            ".....",
            "#####",
            "....#",
            "...##")
        val asteroidMap = data.convertToMap()
        val (position ,quantity) =  asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0,0),0)
        assertEquals(Position(3,4),position)
    }
    @Test
    fun`Best is 5,8 with 33 other asteroids detected`() {
        val data = listOf(
            "......#.#.",
            "#..#.#....",
            "..#######.",
            ".#.#.###..",
            ".#..#.....",
            "..#....#.#",
            "#..#....#.",
            ".##.#..###",
            "##...#..#.",
            ".#....####"
        )
        val asteroidMap = data.convertToMap()
        val (position ,quantity) =  asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0,0),0)
        assertEquals(Position(5,8),position)
        assertEquals(33,quantity)
    }
    @Test
    fun`Best is 1,2 with 35 other asteroids detected`() {
        val data = listOf(
            "#.#...#.#.",
            ".###....#.",
            ".#....#...",
            "##.#.#.#.#",
            "....#.#.#.",
            ".##..###.#",
            "..#...##..",
            "..##....##",
            "......#...",
            ".####.###."
        )
        val asteroidMap = data.convertToMap()
        val (position ,quantity) =  asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0,0),0)
        assertEquals(Position(1,2),position)
        assertEquals(35,quantity)
    }
    @Test
    fun`Best is 6,3 with 41 other asteroids detected`() {
        val data = listOf(
            ".#..#..###",
            "####.###.#",
            "....###.#.",
            "..###.##.#",
            "##.##.#.#.",
            "....###..#",
            "..#.#..#.#",
            "#..#.#.###",
            ".##...##.#",
            ".....#.#.."
        )
        val asteroidMap = data.convertToMap()
        val (position, quantity) = asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0, 0), 0)
        assertEquals(Position(6, 3), position)
        assertEquals(41, quantity)
    }
    @Test
    fun`Best is 11,13 with 210 other asteroids detected`() {
        val data = listOf(
            ".#..##.###...#######",
            "##.############..##.",
            ".#.######.########.#",
            ".###.#######.####.#.",
            "#####.##.#.##.###.##",
            "..#####..#.#########",
            "####################",
            "#.####....###.#.#.##",
            "##.#################",
            "#####.##.###..####..",
            "..######..##.#######",
            "####.##.####...##..#",
            ".#####..#.######.###",
            "##...#.##########...",
            "#.##########.#######",
            ".####.#.###.###.#.##",
            "....##.##.###..#####",
            ".#.#.###########.###",
            "#.#.#.#####.####.###",
            "###.##.####.##.#..##"
        )

        val asteroidMap = data.convertToMap()
        val (position, quantity) = asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0, 0), 0)
        assertEquals(Position(11, 13), position)
        assertEquals(210, quantity)

    }
    //@Test
    fun `Day 10 part one`() {
        val data = File("day10.txt").readLines()
        val asteroidMap = data.convertToMap()
        val (position, quantity) = asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0, 0), 0)

        println("Day 10 part one $position $quantity")
        assertEquals(Position(22, 19), position)
        assertEquals(282, quantity)
    }

    @Test
    fun `Part two example one`() {
        val data = listOf(
            ".#....#####...#..",
            "##...##.#####..##",
            "##...#...#.#####.",
            "..#.....#...###..",
            "..#.#.....#....##"
        )
        val sourcePosition = Position(8,3)
        val asteroidMap = data.convertToMap().toMutableMap()
        asteroidMap[sourcePosition] = 'X'
        val lastAsteroidZapped = asteroidMap.removeAsteroids(sourcePosition,9)

        asteroidMap.print()
        assertEquals('O', asteroidMap[Position(8,1)])
        assertEquals('O', asteroidMap[Position(9,0)])
        assertEquals('O', asteroidMap[Position(9,1)])
        assertEquals('O', asteroidMap[Position(9,2)])
        assertEquals('O', asteroidMap[Position(10,0)])
        assertEquals('O', asteroidMap[Position(11,1)])
        assertEquals(Position(15,1), lastAsteroidZapped)

    }

    @Test
    fun `Part two example two`() {
        val data = listOf(
            ".#..##.###...#######",
            "##.############..##.",
            ".#.######.########.#",
            ".###.#######.####.#.",
            "#####.##.#.##.###.##",
            "..#####..#.#########",
            "####################",
            "#.####....###.#.#.##",
            "##.#################",
            "#####.##.###..####..",
            "..######..##.#######",
            "####.##.####...##..#",
            ".#####..#.######.###",
            "##...#.##########...",
            "#.##########.#######",
            ".####.#.###.###.#.##",
            "....##.##.###..#####",
            ".#.#.###########.###",
            "#.#.#.#####.####.###",
            "###.##.####.##.#..##"
        )

        val sourcePosition = Position(11,13)
        val asteroidMap = data.convertToMap().toMutableMap()
        asteroidMap[sourcePosition] = 'X'
        val lastAsteroidZapped = asteroidMap.removeAsteroids(sourcePosition,200)
        assertEquals(Position(8,2), lastAsteroidZapped)
    }

    @Test
    fun `Day 10 part two`() {
        val data = File("day10.txt").readLines()

        val sourcePosition = Position(22,19)
        val asteroidMap = data.convertToMap().toMutableMap()
        asteroidMap[sourcePosition] = 'X'
        val lastAsteroidZapped = asteroidMap.removeAsteroids(sourcePosition,200)

        println("Day 10 part two: last asteroid Zapped $lastAsteroidZapped}")

        assertEquals(Position(10,8), lastAsteroidZapped)
    }
}
