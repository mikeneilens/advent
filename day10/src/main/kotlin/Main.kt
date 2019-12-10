import java.lang.Math.abs

data class Position(val x:Int, val y:Int)

fun Position.distanceTo(position:Position) = (this.x - position.x) * (this.x - position.x) + abs(this.y - position.y) * abs(this.y - position.y)

fun Position.angleTo(position:Position):Double =
    if (this.y == position.y)
         if (this.x > position.x) Double.MAX_VALUE else -Double.MAX_VALUE
    else
        if (this.x == position.x) 0.0
    else
        ( (this.x - position.x).toDouble() / (this.y - position.y))


fun List<String>.convertToMap():Map<Position,Char>{
    val map = mutableMapOf<Position,Char>()
    this.forEachIndexed{y, data ->
        data.toList().forEachIndexed{ x, symbol ->
            if (symbol == '#') map[Position(x,y)] = '#'
        }
    }
    return map
}
fun Map<Position,Char>.maxX() = (this.keys.maxBy { it.x }?.x) ?: -1
fun Map<Position,Char>.maxY() = (this.keys.maxBy { it.y }?.y) ?: -1

fun Map<Position,Char>.print() {
    (0..this.maxY()).forEach { y ->
        (0..this.maxX()).forEach { x->
            print(this[Position(x,y)] ?: ".")
        }
        println()
    }
}

fun Map<Position,Char>.asteroidsVisible(sourcePosition:Position) = this.keys.map {asteroidPosition ->
    val angleToAsteroidPosition = sourcePosition.angleTo(asteroidPosition)
    val asteroidInTheWay = this.keys.any{otherAsteroidPosition ->
            (otherAsteroidPosition != sourcePosition)
                    && (sourcePosition.angleTo(otherAsteroidPosition) == angleToAsteroidPosition)
                    && (otherAsteroidPosition.distanceTo(sourcePosition) < asteroidPosition.distanceTo(sourcePosition))  }
//    println("$sourcePosition $asteroidInTheWay $angle")
    if (asteroidInTheWay) 0 else 1
}.sum() - 1

fun Map<Position,Char>.asteroidThatCanSeeTheMostOther():Pair<Position, Int>? {
   return this.map{ Pair(it.key, this.asteroidsVisible(it.key))}.maxBy { it.second }
}