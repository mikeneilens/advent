import kotlin.math.abs

data class Position(val x:Int, val y:Int, val z:Int) {
    fun calcGravity(other:Position):Velocity {
       return Velocity(calcAxisGravity(x, other.x),calcAxisGravity(y, other.y),calcAxisGravity(z, other.z))
    }
    private fun calcAxisGravity(val1:Int, val2:Int) = when (true) {
            (val1 < val2) -> +1
            (val1 > val2) -> -1
            else -> 0 }
    operator fun plus(other:Velocity) = Position(x + other.x, y + other.y, z + other.z)
}

data class Velocity(val x:Int, val y:Int, val z:Int) {
    operator fun plus(other:Velocity) = Velocity(x + other.x, y + other.y, z + other.z)
}

fun calcGravitiesForAMoon(moon:Position, allMoons:List<Position>):Velocity = allMoons.map{otherMoon -> moon.calcGravity(otherMoon) }.fold(Velocity(0,0,0)){ a, e -> a + e}

fun moveMoons(_moonPositions:List<Position>, _moonVelocities:List<Velocity>, maxSteps:Int):Pair<List<Position>, List<Velocity>> {
    var moonPositions = _moonPositions
    var moonVelocities = _moonVelocities
    var steps = 0

    while (steps < maxSteps) {
        moonVelocities = moonPositions.mapIndexed { index, moonPosition ->
            moonVelocities[index] + calcGravitiesForAMoon(moonPosition, moonPositions)
        }

        moonPositions = moonPositions.mapIndexed { index, moonPosition ->
            moonPosition + moonVelocities[index]
        }
        steps += 1
    }
    return Pair(moonPositions, moonVelocities)
}

fun totalEnergy(moonPositions:List<Position>, moonVelocities:List<Velocity>) =  moonPositions.mapIndexed { index, moonPosition ->
    (abs(moonPosition.x) + abs(moonPosition.y) + abs(moonPosition.z)) * (abs(moonVelocities[index].x) + abs(moonVelocities[index].y) + abs(moonVelocities[index].z))
}.sum()
