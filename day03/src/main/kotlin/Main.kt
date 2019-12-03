import kotlin.math.absoluteValue

data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position) = Position(this.x + other.x, this.y + other.y)
}

object WireMap {
    private const val wire1Value = 1
    private const val wire2Value = 2
    private val intersectionValue = wire1Value.or(wire2Value)
    var closestIntersection = Int.MAX_VALUE
    private var wire1Position = Position(0,0)
    private var wire2Position = Position(0,0)
    private var wiresAtPosition = mutableMapOf<Position,Int>()

    fun reset() {
        closestIntersection = Int.MAX_VALUE
        wire1Position = Position(0,0)
        wire2Position = Position(0,0)
        wiresAtPosition = mutableMapOf<Position,Int>()
    }

    fun moveWire1(instruction:String) {
        wire1Position = move(instruction, wire1Position, wire1Value)
    }

    fun moveWire2(instruction:String) {
        wire2Position = move(instruction, wire2Position, wire2Value)
    }

    private fun move(instruction:String, startPosition:Position, wireValue:Int):Position {
        var resultingPosition = startPosition
        val move = when (instruction.first()) {
            'L' -> Position(-1,+0)
            'R' -> Position(+1,+0)
            'U' -> Position(+0,-1)
            'D' -> Position(+0,+1)
            else -> Position(0,0)
        }
        val steps = instruction.drop(1).toInt()
        repeat(steps) {
            resultingPosition += move
            val contentsOfLocation = wiresAtPosition[resultingPosition] ?: 0
            wiresAtPosition[resultingPosition] = contentsOfLocation.or(wireValue)
            if (contentsOfLocation.or(wireValue) == intersectionValue) {
                if ((resultingPosition.x.absoluteValue + resultingPosition.y.absoluteValue) < closestIntersection ) {
                    closestIntersection = (resultingPosition.x.absoluteValue + resultingPosition.y.absoluteValue)
                }
            }
        }
        return resultingPosition
    }
}

fun calcualteClosestIntersection(wireList1: List<String>, wireList2: List<String>): Int {
    wireList1.forEach { WireMap.moveWire1(it) }
    wireList2.forEach { WireMap.moveWire2(it) }
    return WireMap.closestIntersection
}
