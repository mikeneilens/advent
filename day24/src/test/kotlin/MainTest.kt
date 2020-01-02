import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `Text is converted into a map correctly`() {
        val data =
                "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#...."
        val map:Map<Position, Char> = data.toDataMap()
        assertEquals('.', map[Position(0,0)])
        assertEquals('.', map[Position(1,0)])
        assertEquals('.', map[Position(2,0)])
        assertEquals('.', map[Position(3,0)])
        assertEquals('#', map[Position(4,0)])
        assertEquals('#', map[Position(0,2)])
        assertEquals('.', map[Position(1,2)])
        assertEquals('.', map[Position(2,2)])
        assertEquals('#', map[Position(3,2)])
        assertEquals('#', map[Position(4,2)])
        assertEquals('#', map[Position(0,4)])
        assertEquals('.', map[Position(1,4)])
        assertEquals('.', map[Position(2,4)])
        assertEquals('.', map[Position(3,4)])
        assertEquals('.', map[Position(4,4)])
    }

    @Test
    fun `state of sample one code is as expected after 1 minute`() {
        val data = "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#...."
        val expected = "#..#.\n" +
                "####.\n" +
                "###.#\n" +
                "##.##\n" +
                ".##.."
        val result = data.toDataMap().transitionMap().asString()
        assertEquals(expected, result)
    }

    @Test
    fun `state of sample one code is as expected after 4 minute`() {
        val data = "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#...."
        val expected = "####.\n" +
                "....#\n" +
                "##..#\n" +
                ".....\n" +
                "##..."
        val result = data.toDataMap().transitionMap().transitionMap().transitionMap().transitionMap().asString()
        assertEquals(expected, result)
    }

    @Test
    fun `value map is created correctly`() {
        val valueMap = createValueMap()
        assertEquals(1.0, valueMap[Position(0,0)])
        assertEquals(32768.0, valueMap[Position(0,3)])
        assertEquals(16777216.0, valueMap[Position(4,4)])
    }

    @Test
    fun `score for a map is calculated correctly`() {
        val data = ".....\n" +
                ".....\n" +
                ".....\n" +
                "#....\n" +
                ".#..."
        val score = data.toDataMap().score(createValueMap())
        assertEquals(2129920.0, score)
    }
    @Test
    fun `get repeat score using sample data`() {
        val data = "....#\n" +
                "#..#.\n" +
                "#..##\n" +
                "..#..\n" +
                "#...."
        val map = data.toDataMap()
        val result = dayOne(map)
        assertEquals(2129920, result)
    }
    @Test
    fun `solve day one`() {
        val data = "#..#.\n" +
                "#.#.#\n" +
                "...#.\n" +
                "....#\n" +
                "#.#.#"
        val map = data.toDataMap()
        val result = dayOne(map)
        assertEquals(12531574, result)
    }
}