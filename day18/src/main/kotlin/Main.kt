
data class Position(val x:Int, val y:Int) {
    fun adjacentLeft():Position = Position(x - 1, y)
    fun adjacentRight():Position = Position(x + 1, y)
    fun adjacentUp():Position = Position(x, y - 1)
    fun adjacentDown():Position = Position(x, y + 1)
    operator fun plus(other:Position) = Position(x + other.x, y + other.y)
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

fun Char.isUnlocked(unlockedLetters:List<Char>) = unlockedLetters.contains(this) || unlockedLetters.contains(this.toUpperCase())

fun MapOfPaths.keys() = toList().mapNotNull{ (position, path) ->  if (path is Path.SmallLetter) Pair(position, path.letter) else null }
fun MapOfPaths.startPath() = toList().first{it.second is Path.Start }
fun MapOfPaths.noOfKeys() = values.filter { it is Path.SmallLetter && it.letter >= 'a' && it.letter <= 'z'}.count()

fun List<Position>.removeClosedLetters(mapOfPaths:MapOfPaths, unlockedLetters: List<Char>) =
    filter{ val path = mapOfPaths[it]; (path is Path.Start) || (path is Path.Empty) || (path is Path.SmallLetter)
            || (path is Path.BigLetter && path.letter.isUnlocked(unlockedLetters))}

fun List<Position>.removePositionsVisited(positionsVisited: List<Position>) = filter{!positionsVisited.contains(it)}


fun List<List<Position>>.totalSize() = fold(0){a, e -> a + e.size}

fun MapOfPaths.findStepsToEnd():Int? {

    val (positionOfStart, _) = startPath()
    val noOfKeys = noOfKeys()

    fun findSteps(position:Position, unlockedLetters:List<Char> = listOf(), visitedOnThisLeg:List<Position> = listOf(), legs:List<List<Position>> = listOf() ):List<List<Position>>? {
        val path = this[position]
        //if a small letter is found and it hasn't been used, unlock the door and start a new leg.
        if (path is Path.SmallLetter && !path.letter.isUnlocked(unlockedLetters)) {
            if ((unlockedLetters.size + 1) == noOfKeys) {
                return legs + listOf(visitedOnThisLeg)
            }
            else {
                return findSteps(position, unlockedLetters + path.unlocks, listOf(),legs + listOf(visitedOnThisLeg) )
            }
        }

        val adjacentPositions = if (path != null) path.adjacentPaths.mapNotNull{it} else listOf()
        val availablePositions = adjacentPositions.removePositionsVisited(visitedOnThisLeg).removeClosedLetters(this,unlockedLetters)

        return  availablePositions.mapNotNull{availablePosition ->
            findSteps(availablePosition, unlockedLetters, visitedOnThisLeg + position, legs)
        }.minBy { it.totalSize() }
    }

    val result = findSteps(positionOfStart)
    println(result?.totalSize())
    return result?.totalSize()
}
