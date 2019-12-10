import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class MainTest
{
    @Test
    fun `Get test data`() {
        val input = File("day10.txt").readLines()
    }
    @Test
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
        //println("$position $quantity")
        assertEquals(Position(3,4),position)
    }
 //   @Test
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
        //println("$position $quantity")
        assertEquals(Position(5,8),position)
        assertEquals(33,quantity)
    }
//    @Test
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
        //println("$position $quantity")
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
        val (position ,quantity) =  asteroidMap.asteroidThatCanSeeTheMostOther() ?: Pair(Position(0,0),0)
        println("$position $quantity")
        println(  Position(6,3).let { Pair(it, asteroidMap.asteroidsVisible(it))})
        assertEquals(Position(6,3),position)
        assertEquals(41,quantity)


    }
}
