import kotlin.math.absoluteValue

data class Position(val x:Int, val y:Int) {
    val manhattanDistance = x.absoluteValue + y.absoluteValue
    operator fun plus(other:Position) = Position(this.x + other.x, this.y + other.y)
}

data class VisitRecord(val wireSteps:MutableList<Int>)

object WireMap {
    var closestIntersectionPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
    var leastStepsPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
    private var wirePosition = mutableListOf(Position(0,0),Position(0,0))
    var wiresAtPosition = mutableMapOf<Position,VisitRecord>()
    private var wireStepsTaken = mutableListOf(0,0)

    fun reset() {
        closestIntersectionPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
        wirePosition = mutableListOf(Position(0,0),Position(0,0))
        wiresAtPosition = mutableMapOf()
        wireStepsTaken = mutableListOf(0,0)
        leastStepsPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
    }

    fun move(instruction:String, wireNo:Int) {

        val move = when (instruction.first()) {
            'L' -> Position(-1,+0)
            'R' -> Position(+1,+0)
            'U' -> Position(+0,-1)
            'D' -> Position(+0,+1)
            else -> Position(0,0)
        }
        val steps = instruction.drop(1).toInt()
        repeat(steps) {
            wirePosition[wireNo] += move
            wireStepsTaken[wireNo] += 1

            val contentsOfLocation = wiresAtPosition[wirePosition[wireNo]] ?: VisitRecord(mutableListOf(0,0))
            if (contentsOfLocation.wireSteps[wireNo] == 0) contentsOfLocation.wireSteps[wireNo] = wireStepsTaken[wireNo]
            wiresAtPosition[wirePosition[wireNo]] = contentsOfLocation

            if (!contentsOfLocation.wireSteps.contains(0)  ) {
                if (wirePosition[wireNo].manhattanDistance < closestIntersectionPosition.manhattanDistance ) {
                    closestIntersectionPosition = wirePosition[wireNo]
                }
                val stepsToLeastStepsLocation = wiresAtPosition[leastStepsPosition] ?: VisitRecord(mutableListOf(Int.MAX_VALUE/2,Int.MAX_VALUE/2))
                if (contentsOfLocation.wireSteps.sum() < stepsToLeastStepsLocation.wireSteps.sum()) {
                    leastStepsPosition = wirePosition[wireNo]
                }
            }
        }
    }
}

fun calculateClosestIntersection(wireList1: List<String>, wireList2: List<String>): Int {
    wireList1.forEach { WireMap.move(it,0) }
    wireList2.forEach { WireMap.move(it,1) }
    return WireMap.closestIntersectionPosition.manhattanDistance
}

fun calculateStepsToClosestIntersection(wireList1: List<String>, wireList2: List<String>): Int {
    wireList1.forEach { WireMap.move(it,0) }
    wireList2.forEach { WireMap.move(it,1) }

    val contentsOfLocation = WireMap.wiresAtPosition[WireMap.leastStepsPosition] ?: VisitRecord(mutableListOf(0,0))
    return contentsOfLocation.wireSteps.sum()
}
