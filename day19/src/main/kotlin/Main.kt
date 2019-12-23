data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position):Position = Position(this.x + other.x, this.y + other.y)
}

enum class Content(val value:Int, val symbol:Char) {
    pull(1,'#'), noPull(0,'.');
    companion object {
        fun create(value:Int) = values().first{it.value == value}
    }
}

typealias SpaceMap = MutableMap<Position, Content>
fun SpaceMap.xRange() = (keys.map{it.x}.min() ?: 0)..(keys.map{it.x}.max() ?: 0)
fun SpaceMap.yRange() = (keys.map{it.y}.min() ?: 0)..(keys.map{it.y}.max() ?: 0)
fun SpaceMap.print() {
    yRange().forEach{y -> xRange().forEach { x -> print(this[Position(x, y)]?.symbol) }
        println()
    }
}
fun SpaceMap.noOfPointsAffectedByPull() = values.count { it == Content.pull }

fun runComputerForPosition(sourceCode:List<Number>, position:Position):Content {
    val input = listOf(position.x,position.y).map{it.toNumber()}
    val program = Program(sourceCode, input)
    program.execute()
    val locationValue = program.output.firstOrNull() ?: 0
    return Content.create( locationValue.toInt())
}

fun pullAtEachLocation(sourceCode: List<Number>):SpaceMap {
    val spaceMap:SpaceMap = mutableMapOf()
    (0..49).forEach{ y ->
        (0..49).forEach { x ->
            val contentForPosition = runComputerForPosition(sourceCode,Position(x,y))
            spaceMap[Position(x,y)] = contentForPosition
        }
    }
    return spaceMap
}

fun findFirstXWithPull(sourceCode: List<Number>, y:Int):Int {
    var x = 500
    var pullFound = false
    while (!pullFound) {
        if (isPullAt(sourceCode,x,y)) {
            pullFound = true
        } else {
            x++
        }
    }
    return x
}
fun isPullAt(sourceCode: List<Number>,x: Int, y: Int):Boolean {
    val content = runComputerForPosition(sourceCode,Position(x,y))
    return content == Content.pull
}
fun findPositionWhichContainsA100X100Square(sourceCode: List<Number>):Position {
    var y = 1000 //guestimate this to prevent too much number crunching
    var solutionFound = false
    var solutionPosition = Position(0,0)
    while (!solutionFound) {
        val firstXWithPull = findFirstXWithPull(sourceCode,y)
        if (isPullAt(sourceCode,firstXWithPull + 99,y) && isPullAt(sourceCode,firstXWithPull + 99, y - 99)) {
            solutionFound = true
            solutionPosition = Position(firstXWithPull, y - 99)
        } else {
            y++
        }
    }
    println("Solution is $solutionPosition" )
    return solutionPosition
}
