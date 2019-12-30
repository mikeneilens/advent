sealed class TunnelType {
    object Exit:TunnelType()
    class Entrance(val adjacentTunnelPositions:List<Position>):TunnelType()
    class Tunnel(val adjacentTunnelPositions:List<Position>):TunnelType()

    override fun equals(other: Any?): Boolean = other is TunnelType
            && ((this is Exit && other is Exit)
            || (this is Entrance && other is Entrance && other.adjacentTunnelPositions == this.adjacentTunnelPositions)
            || (this is Tunnel && other is Tunnel && other.adjacentTunnelPositions == this.adjacentTunnelPositions) )

}

data class Position(val x:Int, val y:Int) {
    fun adjacentLeft():Position = Position(x - 1, y)
    fun adjacentRight():Position = Position(x + 1, y)
    fun adjacentUp():Position = Position(x, y - 1)
    fun adjacentDown():Position = Position(x, y + 1)
}

typealias DataMap = Map<Position, Char>
typealias TunnelMap = Map<Position, TunnelType>

fun String.toDataMap():DataMap {
    val result = mutableMapOf<Position, Char>()
    val rows = split("\n")
    rows.forEachIndexed {y, row ->
        val chars = row.toList()
        chars.forEachIndexed{x, char ->
            result[Position(x,y)] = char
        }
    }
    return result
}

fun DataMap.findPortals() =
    mapNotNull{(position, char) ->
        if (char >= 'A' && char <= 'Z') {
            val (exitPosition , portalName) = portalName(position)
            if (portalName.isNotEmpty()) Pair(exitPosition, portalName) else null
        } else null
    }

fun DataMap.portalName(position:Position):Pair<Position, String> {
    val firstLetter = this[position] ?: '!'
    val charToRight = this[position.adjacentRight()]
    if (charToRight != null && charToRight >= 'A' && charToRight <= 'Z')  {
        val twoCharsToRight = this[position.adjacentRight().adjacentRight()]
        return if (twoCharsToRight != null && twoCharsToRight == '.')
            Pair(position.adjacentRight().adjacentRight(),String(charArrayOf(firstLetter,charToRight)))
        else
            Pair(position.adjacentLeft(),String(charArrayOf(firstLetter,charToRight)))
    }
    val charBelow = this[position.adjacentDown()]
    if (charBelow != null && charBelow >= 'A' && charBelow <= 'Z') {
        val twoCharsBelow = this[position.adjacentDown().adjacentDown()]
        return if (twoCharsBelow != null && twoCharsBelow == '.')
            Pair(position.adjacentDown().adjacentDown(),String(charArrayOf(firstLetter,charBelow)))
         else
            Pair(position.adjacentUp(),String(charArrayOf(firstLetter,charBelow)))
    }
    return Pair(position,"")
}

fun DataMap.toContentMap():TunnelMap {
    val listOfPortals = findPortals()
    val result = mutableMapOf<Position,TunnelType>()
    forEach{(position,char) ->
        if (char == '.'){
            if (listOfPortals.isExit(position)) result[position] = TunnelType.Exit
            else  {
                val listOfPositionsToMoveTo = mutableListOf<Position>()
                if (this[position.adjacentLeft()] == '.') listOfPositionsToMoveTo.add(position.adjacentLeft())
                if (this[position.adjacentRight()] == '.') listOfPositionsToMoveTo.add(position.adjacentRight())
                if (this[position.adjacentUp()] == '.') listOfPositionsToMoveTo.add(position.adjacentUp())
                if (this[position.adjacentDown()] == '.') listOfPositionsToMoveTo.add(position.adjacentDown())

                if (listOfPortals.isEntrance(position) )
                    result[position] = TunnelType.Entrance(listOfPositionsToMoveTo)
                else {
                    val portalForThisPosition = listOfPortals.firstOrNull() { (portalPosition, portalName) -> (portalPosition == position) }
                    if (portalForThisPosition != null) listOfPortals.getExitPosition(portalForThisPosition)?.apply {listOfPositionsToMoveTo.add(this)}
                    result[position] = TunnelType.Tunnel(listOfPositionsToMoveTo)
                }
            }
        }
    }
    return result
}

fun TunnelMap.findShortestRoute(position:Position, positionsVisited:List<Position> = listOf()):List<Position>? {
    val tunnelType = this[position]

    fun List<Position>.getBestRoute(position:Position):List<Position>?{
        val routesToExit = filter{!positionsVisited.contains(it)}
                                        .mapNotNull { adjacentPosition -> findShortestRoute(adjacentPosition, positionsVisited + position) }
        return if (routesToExit.isEmpty()) null  else routesToExit.minBy { it.size }
    }

    return when (tunnelType) {
        is TunnelType.Exit -> positionsVisited
        is TunnelType.Tunnel  -> tunnelType.adjacentTunnelPositions.getBestRoute(position)
        is TunnelType.Entrance  -> tunnelType.adjacentTunnelPositions.getBestRoute(position)
        else -> null
    }

}

fun TunnelMap.entrancePosition()= toList().first{ (position, content) -> content is TunnelType.Entrance}.first

fun List<Pair<Position, String>>.getExitPosition(portal:Pair<Position,String>):Position? = firstOrNull{ (otherPosition, otherName) -> (otherName == portal.second && otherPosition != portal.first) }?.first
fun List<Pair<Position, String>>.isEntrance(position:Position) = any{(portalPosition, portalName) -> position == portalPosition && portalName == "AA" }
fun List<Pair<Position, String>>.isExit(position:Position) = any{(portalPosition, portalName) -> position == portalPosition && portalName == "ZZ" }
