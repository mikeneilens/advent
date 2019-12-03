import kotlin.math.absoluteValue

data class Position(val x:Int, val y:Int) {
    val manhattanDistance = x.absoluteValue + y.absoluteValue
    operator fun plus(other:Position) = Position(this.x + other.x, this.y + other.y)
}

data class VisitRecord(val wireSteps:MutableList<Int>)

class WireMap {
    val wiresAtPosition = mutableMapOf<Position,VisitRecord>()
    var closestIntersectionPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
    var leastStepsPosition = Position(Int.MAX_VALUE/2, Int.MAX_VALUE/2)
    private val wirePosition = mutableListOf(Position(0,0),Position(0,0))
    private val wireStepsTaken = mutableListOf(0,0)

    val movesMap = mapOf('L' to Position(-1,+0),'R' to Position(+1,+0),'U' to Position(+0,-1),'D' to Position(+0,+1))

    fun String.move() = movesMap[this.first()] ?:  Position(0,0)
    fun String.steps() = this.drop(1).toInt()

    fun move(instruction:String, wireNo:Int) {

        repeat(instruction.steps()) {
            wirePosition[wireNo] += instruction.move()
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
    val wireMap = WireMap()
    wireList1.forEach { wireMap.move(it,0) }
    wireList2.forEach { wireMap.move(it,1) }
    return wireMap.closestIntersectionPosition.manhattanDistance
}

fun calculateStepsToClosestIntersection(wireList1: List<String>, wireList2: List<String>): Int {
    val wireMap = WireMap()
    wireList1.forEach { wireMap.move(it,0) }
    wireList2.forEach { wireMap.move(it,1) }

    val contentsOfLocation = wireMap.wiresAtPosition[wireMap.leastStepsPosition] ?: VisitRecord(mutableListOf(0,0))
    return contentsOfLocation.wireSteps.sum()
}
