import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `data from sample one is turned into a map of data`() {
        val data =
                "#########\n" +
                "#b.A.@.a#\n" +
                "#########"
        val mapOfData = data.toMapOfData()
        assertEquals('#', mapOfData[Position(0,0)])
        assertEquals('#', mapOfData[Position(8,0)])
        assertEquals('b', mapOfData[Position(1,1)])
        assertEquals('A', mapOfData[Position(3,1)])
        assertEquals('@', mapOfData[Position(5,1)])
    }
    @Test
    fun `adjacent paths are calculated correctly`() {
        val data1 =
                "###\n" +
                "#.#\n" +
                "###"
        val mapOfData1 = data1.toMapOfData()
        assertEquals(listOf<Position>(), mapOfData1.getAdjacentPaths(Position(1,1)))
        val data2 =
                "###\n" +
                "#..\n" +
                "###"
        val mapOfData2 = data2.toMapOfData()
        assertEquals(listOf<Position>(Position(2,1)), mapOfData2.getAdjacentPaths(Position(1,1)))
        val data3 =
                "###\n" +
                "#.#\n" +
                "#.#"
        val mapOfData3 = data3.toMapOfData()
        assertEquals(listOf<Position>(Position(1,2)), mapOfData3.getAdjacentPaths(Position(1,1)))
        val data4 =
                "###\n" +
                "..#\n" +
                "###"
        val mapOfData4 = data4.toMapOfData()
        assertEquals(listOf<Position>(Position(0,1)), mapOfData4.getAdjacentPaths(Position(1,1)))
        val data5 =
                "#.#\n" +
                "#.#\n" +
                "###"
        val mapOfData5 = data5.toMapOfData()
        assertEquals(listOf<Position>(Position(1,0)), mapOfData5.getAdjacentPaths(Position(1,1)))
    }
    @Test
    fun `map of data is converted to a map of paths`() {
        val data =
                    "#########\n" +
                    "#b.A.@.a#\n" +
                    "#########"
        val mapOfPaths = data.toMapOfData().toMapOfPaths()
        val pathAt11 = mapOfPaths[Position(1,1)]
        val pathAt31 = mapOfPaths[Position(3,1)]

        assertTrue( mapOfPaths[Position(5,1)] is Path.Start)
        assertTrue( pathAt11 is Path.SmallLetter && pathAt11.letter == 'b' && pathAt11.unlocks == 'B')
        assertTrue( pathAt31 is Path.BigLetter && pathAt31.letter == 'A')
        assertTrue( pathAt31 is Path.BigLetter && pathAt31.adjacentPaths == listOf(Position(2,1),Position(4,1)))
    }
    @Test
    fun `find shortest steps for first sample`() {
        val data =
                "#########\n" +
                "#b.A.@.a#\n" +
                "#########"
        val mapOfPaths = data.toMapOfData().toMapOfPaths()
        val steps = mapOfPaths.findStepsToEnd()
        assertEquals(8, steps)
    }
    @Test
    fun `find shortest steps for sample 2`() {
        val data =
                "########################\n" +
                "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
                "######################.#\n" +
                "#d.....................#\n" +
                "########################\n"

        val mapOfPaths = data.toMapOfData().toMapOfPaths()
        val steps = mapOfPaths.findStepsToEnd()
        assertEquals(86, steps)
    }
    @Test
    fun `find shortest steps for sample 3`() {
        val data =
                "########################\n" +
                "#...............b.C.D.f#\n" +
                "#.######################\n" +
                "#.....@.a.B.c.d.A.e.F.g#\n" +
                "########################"

        val mapOfPaths = data.toMapOfData().toMapOfPaths()
        val steps = mapOfPaths.findStepsToEnd()
        assertEquals(132, steps)
    }
    @Test
    fun `find shortest steps for sample 4`() {
        val data =
                "#################\n" +
                "#i.G..c...e..H.p#\n" +
                "########.########\n" +
                "#j.A..b...f..D.o#\n" +
                "########@########\n" +
                "#k.E..a...g..B.n#\n" +
                "########.########\n" +
                "#l.F..d...h..C.m#\n" +
                "#################"

        val mapOfPaths = data.toMapOfData().toMapOfPaths()
        val steps = mapOfPaths.findStepsToEnd()
        assertEquals(136, steps)
    }

}