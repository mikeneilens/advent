import kotlin.math.pow

data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position) = Position(x + other.x, y + other.y)
}

val adjacentPositions = listOf(
    Position(1,0),Position(0,1),Position(-1,0),Position(0,-1)
)

typealias DataMap = Map<Position, Char>

fun String.toDataMap():DataMap  = split("\n")
                                                .mapIndexed{y, row -> row.toList()
                                                .mapIndexed{x, char -> Pair(Position(x,y) , char) } }
                                                .flatMap{it}.toMap()

fun  DataMap.transitionMap():DataMap =
    toList().map{(position, char) ->
        val adjacentBugs = countAdjacentBugs(position)
        when (true) {
            (char == '#' && adjacentBugs != 1  ) -> Pair(position,'.')
            (char == '.' && (adjacentBugs== 1 || adjacentBugs == 2) ) -> Pair(position, '#')
            else -> Pair(position, char)
        }
    }.toMap()

fun DataMap.asString():String {
    var output = ""
    (0..4).forEach { y -> (0..4).forEach {x -> output += this[Position(x,y)] }; output += "\n" }
    return output.dropLast(1)
}

fun DataMap.score(valueMap:ValueMap) =  toList().map{ if (it.second == '#') (valueMap[it.first] ?: 0.0) else 0.0 }.fold(0.0){a,e -> a + e}


fun DataMap.countAdjacentBugs(position:Position) = adjacentPositions.map{it + position}.count{this[it] == '#'}

typealias ValueMap = Map<Position, Double>
fun createValueMap():ValueMap = (0..4).flatMap { y -> (0..4).map { x ->  Pair(Position(x,y), 2.0.pow((y * 5 + x).toDouble()) )   } }.toMap()

fun dayOne(map:DataMap):Int {
    val valueMap = createValueMap()

    fun calcNextMap(map:DataMap, scoresSoFar:List<Double> = listOf()):Double {
        val score = map.score(valueMap)
        return if (scoresSoFar.contains(score)) score
        else calcNextMap(map.transitionMap(), scoresSoFar + score)
    }
    return calcNextMap(map).toInt()
}
