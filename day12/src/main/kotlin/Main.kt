import kotlin.math.abs

data class Position(val x:Int, val y:Int, val z:Int) {
    fun calcGravity(other:Position) = Velocity(calcAxisGravity(x, other.x),calcAxisGravity(y, other.y),calcAxisGravity(z, other.z))

    fun calcGravity(allMoons:List<Position>):Velocity = allMoons.map{ otherMoon -> this.calcGravity(otherMoon) }.sum()

    private fun calcAxisGravity(val1:Int, val2:Int) = when (true) {
            (val1 < val2) -> +1
            (val1 > val2) -> -1
            else -> 0 }

    operator fun plus(other:Velocity) = Position(x + other.x, y + other.y, z + other.z)
}

data class Velocity(val x:Int, val y:Int, val z:Int) {
    operator fun plus(other:Velocity) = Velocity(x + other.x, y + other.y, z + other.z)
}

fun List<Velocity>.sum() = fold(Velocity(0,0,0)){ total, velocity -> total + velocity}

fun moveMoons(startingPositions:List<Position>, startingVelocities:List<Velocity>, maxSteps:Int):Pair<List<Position>, List<Velocity>> {
    var moonPositions = startingPositions
    var moonVelocities = startingVelocities
    var steps = 0

    while (steps < maxSteps) {
        moonVelocities = moonPositions.mapIndexed { index, moonPosition -> moonVelocities[index] + moonPosition.calcGravity(moonPositions) }
        moonPositions = moonPositions.mapIndexed { index, moonPosition -> moonPosition + moonVelocities[index] }
        ++steps
    }
    return Pair(moonPositions, moonVelocities)
}

fun totalEnergy(moonPositions:List<Position>, moonVelocities:List<Velocity>) =  moonPositions.mapIndexed { index, moonPosition ->
    (abs(moonPosition.x) + abs(moonPosition.y) + abs(moonPosition.z)) * (abs(moonVelocities[index].x) + abs(moonVelocities[index].y) + abs(moonVelocities[index].z))
}.sum()

fun findFirstRepeatingXYZ(startingPositions:List<Position>, startingVelocities:List<Velocity>, maxSteps:Int):Triple<Int,Int,Int> {
    var moonPositions = startingPositions
    var moonVelocities = startingVelocities
    var steps = 0
    var repeatingX = 0
    var repeatingY = 0
    var repeatingZ = 0

    while ((steps < maxSteps) && listOf(repeatingX,repeatingY,repeatingZ).any{it == 0 }) {
        moonVelocities = moonPositions.mapIndexed { index, moonPosition -> moonVelocities[index] + moonPosition.calcGravity(moonPositions) }
        moonPositions = moonPositions.mapIndexed { index, moonPosition -> moonPosition + moonVelocities[index] }

        if   (allMoonsHaveSameXPositionTheyStartedWith(moonPositions,startingPositions) && (repeatingX == 0))
            repeatingX = steps
        if   (allMoonsHaveSameYPositionTheyStartedWith(moonPositions,startingPositions) && (repeatingY == 0))
            repeatingY = steps
        if   (allMoonsHaveSameZPositionTheyStartedWith(moonPositions,startingPositions) && (repeatingZ == 0))
            repeatingZ = steps

        ++steps
    }
    println("$repeatingX $repeatingY $repeatingZ")
    return Triple(repeatingX,repeatingY,repeatingZ)
}

fun allMoonsHaveSameXPositionTheyStartedWith(moonPositions:List<Position>, original:List<Position>) = moonPositions.mapIndexed{i, moonPosition -> Pair(i,moonPosition)}.all{(i,moon) -> moon.x == original[i].x}
fun allMoonsHaveSameYPositionTheyStartedWith(moonPositions:List<Position>, original:List<Position>) = moonPositions.mapIndexed{i, moonPosition -> Pair(i,moonPosition)}.all{(i,moon) -> moon.y == original[i].y}
fun allMoonsHaveSameZPositionTheyStartedWith(moonPositions:List<Position>, original:List<Position>) = moonPositions.mapIndexed{i, moonPosition -> Pair(i,moonPosition)}.all{(i,moon) -> moon.z == original[i].z}

fun findSteps(moonPositions:List<Position>, moonVelocities:List<Velocity>):Long {
    val (xRepeat,yRepeat,zRepeat) = findFirstRepeatingXYZ(moonPositions, moonVelocities, 100000000)
    val greatestCommonFactor = listOf(xRepeat + 2,yRepeat + 2,zRepeat + 2).greatestCommonFactor()
    val totalSteps:Long = 1L * (xRepeat + 2) / greatestCommonFactor * (yRepeat + 2) / greatestCommonFactor * (zRepeat + 2) / greatestCommonFactor
    println(totalSteps)
    return totalSteps
}

fun List<Int>.greatestCommonFactor() =  (1..(this.min() ?: 0)).fold(1){gcf,i -> if (this.all{(it % i == 0)} ) i else  gcf}
