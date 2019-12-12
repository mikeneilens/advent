import kotlin.math.abs

data class Moon (var position:Position, var velocity: Velocity = Velocity(0,0,0)) {
    fun calcGravity(allMoons:List<Moon>):Velocity = allMoons.map{ otherMoon -> position.calcGravity(otherMoon.position) }.sum()

    fun copy() = Moon(Position(this.position.x, this.position.y, this.position.z), Velocity(this.velocity.x,this.velocity.y,this.velocity.z))
}

data class Position(val x:Int, val y:Int, val z:Int) {
    fun calcGravity(other:Position) = Velocity(calcAxisGravity(x, other.x),calcAxisGravity(y, other.y),calcAxisGravity(z, other.z))

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

fun moveMoons(startingMoons:List<Moon>, maxSteps:Int):List<Moon> {
    val moons = startingMoons.map{it.copy()}
    var steps = 0

    while (steps < maxSteps) {
        moons.forEach { moon -> moon.velocity += moon.calcGravity(moons) }
        moons.forEach { moon -> moon.position += moon.velocity }
        ++steps
    }
    return moons
}

fun totalEnergy(moons:List<Moon>) =  moons.map { moon ->
    (abs(moon.position.x) + abs(moon.position.y) + abs(moon.position.z)) * (abs(moon.velocity.x) + abs(moon.velocity.y) + abs(moon.velocity.z))
}.sum()


fun findFirstRepeatingXYZ(startingMoons:List<Moon>, maxSteps:Int):Triple<Int,Int,Int> {
    val moons = startingMoons.map{it.copy()}
    var steps = 0
    var repeatingX = 0
    var repeatingY = 0
    var repeatingZ = 0

    while ((steps < maxSteps) && listOf(repeatingX,repeatingY,repeatingZ).any{it == 0 }) {
        moons.forEach { moon -> moon.velocity += moon.calcGravity(moons) }
        moons.forEach { moon -> moon.position += moon.velocity }

        if   (allMoonsHaveSameXPositionTheyStartedWith(moons,startingMoons) && (repeatingX == 0))  repeatingX = steps
        if   (allMoonsHaveSameYPositionTheyStartedWith(moons,startingMoons) && (repeatingY == 0))  repeatingY = steps
        if   (allMoonsHaveSameZPositionTheyStartedWith(moons,startingMoons) && (repeatingZ == 0))  repeatingZ = steps

        ++steps
    }
    return Triple(repeatingX,repeatingY,repeatingZ)
}

fun allMoonsHaveSameXPositionTheyStartedWith(moons:List<Moon>, original:List<Moon>) = moons.mapIndexed{i, moon -> Pair(i,moon)}.all{(i,moon) -> moon.position.x == original[i].position.x}
fun allMoonsHaveSameYPositionTheyStartedWith(moons:List<Moon>, original:List<Moon>) = moons.mapIndexed{i, moon -> Pair(i,moon)}.all{(i,moon) -> moon.position.y == original[i].position.y}
fun allMoonsHaveSameZPositionTheyStartedWith(moons:List<Moon>, original:List<Moon>) = moons.mapIndexed{i, moon -> Pair(i,moon)}.all{(i,moon) -> moon.position.z == original[i].position.z}

fun findSteps(moons:List<Moon>):Long {
    val (xRepeat,yRepeat,zRepeat) = findFirstRepeatingXYZ(moons, 100000000)
    val greatestCommonFactor = listOf(xRepeat + 2,yRepeat + 2,zRepeat + 2).greatestCommonFactor()
    val totalSteps:Long = 1L * (xRepeat + 2) / greatestCommonFactor * (yRepeat + 2) / greatestCommonFactor * (zRepeat + 2) / greatestCommonFactor
    println("Day 12 part two: $totalSteps")
    return totalSteps
}

fun List<Int>.greatestCommonFactor() =  (1..(this.min() ?: 0)).fold(1){gcf,i -> if (this.all{(it % i == 0)} ) i else  gcf}
