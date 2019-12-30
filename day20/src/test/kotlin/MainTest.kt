import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun `Parse data into a simple map of chars using sample one data`() {
        val map = sampleOneData.toDataMap()
        assertEquals('F', map[Position(11,10)])
        assertEquals('#', map[Position(18,15)])
        assertEquals(' ', map[Position(19,15)])
        assertEquals('Z', map[Position(13,18)])
    }

    @Test
    fun `list of portals is created correctly using sample one data`() {
        val map = sampleOneData.toDataMap()
        val listOfPortals = map.findPortals()

        assertEquals("AA", listOfPortals[0].second)
        assertEquals(Position(9,2), listOfPortals[0].first)

        assertEquals("BC", listOfPortals[1].second)
        assertEquals(Position(9,6), listOfPortals[1].first)

        assertEquals("BC", listOfPortals[2].second)
        assertEquals(Position(2,8), listOfPortals[2].first)
    }

    @Test
    fun `list of portal exit is found correctly`() {
        val map = sampleOneData.toDataMap()
        val listOfPortals = map.findPortals()

        val portal = Pair(Position(2,8), "BC")

        assertEquals(Position(9,6), listOfPortals.getExitPosition(portal))
    }
    @Test
    fun `map of char is converted to a map of content correctly`() {
        val map = sampleOneData.toDataMap()
        val contentMap = map.toContentMap()

        val entrance = contentMap[Position(9,2)]
        assertEquals(TunnelType.Entrance(listOf(Position(9,3))), entrance)

        val exit = contentMap[Position(13,16)]
        assertEquals(TunnelType.Exit, exit)

        val oneDownFromEntrance = contentMap[Position(9,3)]
        assertEquals(TunnelType.Tunnel(listOf(Position(10,3),Position(9,2),Position(9,4))), oneDownFromEntrance)

        val nextToBC = contentMap[Position(9,6)]
        assertEquals(TunnelType.Tunnel(listOf(Position(9,5),Position(2,8))), nextToBC)

        val nextToFG = contentMap[Position(11,12)]
        assertEquals(TunnelType.Tunnel(listOf(Position(11,13),Position(2,15))), nextToFG)

    }
    @Test
    fun `finding shortest route with sample one data`() {
        val map = sampleOneData.toDataMap()
        val contentMap = map.toContentMap()

        val entrancePosition = contentMap.entrancePosition()
        val shortestRoute = contentMap.findShortestRoute(entrancePosition)

        assertEquals(23,shortestRoute?.size)
    }
    @Test
    fun `finding shortest route with sample two data`() {
        val map = sampleTwoData.toDataMap()
        val contentMap = map.toContentMap()

        val entrancePosition = contentMap.entrancePosition()
        val shortestRoute = contentMap.findShortestRoute(entrancePosition)

        assertEquals(58,shortestRoute?.size)
    }

    @Test
    fun `find solution for part one`() {
        val map = fullData.toDataMap()
        val contentMap = map.toContentMap()

        val entrancePosition = contentMap.entrancePosition()
        val shortestRoute = contentMap.findShortestRoute(entrancePosition)

        assertEquals(510,shortestRoute?.size)
    }




}