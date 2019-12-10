import java.lang.Math.abs
import java.lang.Math.atan2

data class Position(val x:Int, val y:Int)

fun Position.distanceTo(position:Position) = (this.x - position.x) * (this.x - position.x) + abs(this.y - position.y) * abs(this.y - position.y)

fun Position.bearingTo(position:Position):Double { //took me ages to get this right!
    val TWOPI = 6.2831853071795865;
    val RAD2DEG = 57.2957795130823209;
// if (a1 = b1 and a2 = b2) throw an error
    var theta = atan2(this.x.toDouble() - position.x, this.y.toDouble() - position.y);
    return if (theta < 0.0)
        (theta + TWOPI) * RAD2DEG;
    else RAD2DEG * theta;
}

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
    val bearingToAsteroidPosition = sourcePosition.bearingTo(asteroidPosition)
    val asteroidInTheWay = this.keys.any{otherAsteroidPosition ->
            (otherAsteroidPosition != sourcePosition)
                    && (sourcePosition.bearingTo(otherAsteroidPosition) == bearingToAsteroidPosition)
                    && (otherAsteroidPosition.distanceTo(sourcePosition) < asteroidPosition.distanceTo(sourcePosition))  }
    if (asteroidInTheWay) 0 else 1
}.sum() - 1

fun Map<Position,Char>.asteroidThatCanSeeTheMostOther():Pair<Position, Int>? {
   return this.map{ Pair(it.key, this.asteroidsVisible(it.key))}.maxBy { it.second }
}