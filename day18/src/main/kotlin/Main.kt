import javafx.geometry.Pos

data class Position(val x:Int, val y:Int) {
    fun adjacentLeft():Position = Position(x - 1, y)
    fun adjacentRight():Position = Position(x + 1, y)
    fun adjacentUp():Position = Position(x, y - 1)
    fun adjacentDown():Position = Position(x, y + 1)
}

sealed class Path(val adjacentPaths:List<Position>) {
    class BigLetter(adjacentPaths:List<Position>, val letter:Char):Path(adjacentPaths)
    class SmallLetter(adjacentPaths:List<Position>, val letter:Char, val unlocks:Char):Path(adjacentPaths)
    class Empty(adjacentPaths:List<Position>):Path(adjacentPaths)
    class Start(adjacentPaths:List<Position>):Path(adjacentPaths)

    companion object {
        fun create(char:Char, adjacentPaths: List<Position>):Path?  {
            if (char >= 'a' && char <= 'z') return SmallLetter(adjacentPaths, char, char.toUpperCase())
            if (char >= 'A' && char <= 'Z') return BigLetter(adjacentPaths, char)
            if (char == '.') return Empty(adjacentPaths)
            if (char == '@') return Start(adjacentPaths)
            return null
        }
    }
}

typealias MapOfPaths = Map<Position, Path>
typealias MapOfData = Map<Position, Char>

fun String.toMapOfData():MapOfData {
    val map = mutableMapOf<Position, Char>()
    split("\n").forEachIndexed{ y, string ->
        string.toList().forEachIndexed{ x, char ->
            map[Position(x,y)] = char
        }
    }
    return map
}
fun MapOfData.getAdjacentPaths(position:Position):List<Position> {
   val adjacentPaths = mutableListOf<Position>()
   this[position.adjacentUp()]?.let{if (it != '#') adjacentPaths.add(position.adjacentUp())}
   this[position.adjacentDown()]?.let{if (it != '#') adjacentPaths.add(position.adjacentDown())}
   this[position.adjacentLeft()]?.let{if (it != '#') adjacentPaths.add(position.adjacentLeft())}
   this[position.adjacentRight()]?.let{if (it != '#') adjacentPaths.add(position.adjacentRight())}
   return adjacentPaths
}
fun MapOfData.toMapOfPaths():MapOfPaths {
    val map = mutableMapOf<Position, Path>()
    toList().forEach { (position, char) ->
        val adjacentPaths = getAdjacentPaths(position)
        Path.create(char, adjacentPaths)?.let {map[position] = it}
    }
    return map
}
