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

fun findFirstRepeatingXYZ(_moonPositions:List<Position>, _moonVelocities:List<Velocity>, maxSteps:Int):Triple<Int,Int,Int> {
    var moonPositions = _moonPositions
    var moonVelocities = _moonVelocities
    var steps = 0
    var repeatingX = 0
    var repeatingY = 0
    var repeatingZ = 0

    while ((steps < maxSteps)&&((repeatingX==0) ||(repeatingY==0) || (repeatingZ==0) )) {
        moonVelocities = moonPositions.mapIndexed { index, moonPosition ->
            moonVelocities[index] + calcGravitiesForAMoon(moonPosition, moonPositions)
        }

        moonPositions = moonPositions.mapIndexed { index, moonPosition ->
            moonPosition + moonVelocities[index]
        }

        if ((moonPositions[0].x == _moonPositions[0].x ) && (moonPositions[1].x == _moonPositions[1].x ) && (moonPositions[2].x == _moonPositions[2].x ) && (moonPositions[3].x == _moonPositions[3].x ) && (repeatingX == 0))
            repeatingX = steps
        if ((moonPositions[0].y == _moonPositions[0].y ) && (moonPositions[1].y == _moonPositions[1].y ) && (moonPositions[2].y == _moonPositions[2].y ) && (moonPositions[3].y == _moonPositions[3].y ) && (repeatingY == 0))
            repeatingY = steps
        if ((moonPositions[0].z == _moonPositions[0].z ) && (moonPositions[1].z == _moonPositions[1].z ) && (moonPositions[2].z == _moonPositions[2].z ) && (moonPositions[3].z == _moonPositions[3].z ) && (repeatingZ== 0))
            repeatingZ = steps
        steps += 1
    }
    println("$repeatingX $repeatingY $repeatingZ")
    return Triple(repeatingX,repeatingY,repeatingZ)
}

fun findSteps(moonPositions:List<Position>, moonVelocities:List<Velocity>, maxSteps:Int):Long {
    val (xRepeat,yRepeat,zRepeat) = findFirstRepeatingXYZ(moonPositions, moonVelocities, 100000000)
    val gcf = gcf(xRepeat + 2,yRepeat + 2,zRepeat + 2)
    val totalSteps:Long = 1L * (xRepeat + 2) / gcf * (yRepeat + 2) / gcf * (zRepeat + 2) / gcf
    println(totalSteps)
    return totalSteps
}
