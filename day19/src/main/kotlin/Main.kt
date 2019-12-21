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
        kotlin.io.println()
    }
}
fun SpaceMap.noOfPointsAffectedByPull() = values.count { it == Content.pull }


fun runComputerForPosition(sourceCode:List<Number>, position:Position):List<Int> {
    val input = listOf(position.x,position.y).map{it.toNumber()}
    val program = Program(sourceCode, input)
    program.execute()
    return program.output.map{it.toInt()}
}

fun pullAtEachLocation(sourceCode: List<Number>):SpaceMap {
    val spaceMap:SpaceMap = mutableMapOf()
    (0..49).forEach{ y ->
        (0..49).forEach { x ->
            val output = runComputerForPosition(sourceCode,Position(x,y))
            output.firstOrNull()?.let{spaceMap[Position(x,y)] = Content.create(it)}
        }
    }
    return spaceMap
}

fun pullAtEachLocation(sourceCode: List<Number>,y:Int):SpaceMap {
    val spaceMap:SpaceMap = mutableMapOf()
    (0..1000).forEach { x ->
        val output = runComputerForPosition(sourceCode,Position(x,y))
        output.firstOrNull()?.let{spaceMap[Position(x,y)] = Content.create(it)}
    }
   return spaceMap
}