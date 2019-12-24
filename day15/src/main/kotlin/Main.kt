data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position) = Position(x + other.x, y + other.y)
}

enum class MovementCommand (val value:Number, val move:Position) {
    North(1L,Position(0,-1)),
    South(2L,Position(0,1)),
    East(4L,Position(1,0)),
    West(3L,Position(-1,0))

}

enum class StatusCode (val value:Number, val image:Char) {
    HitWall(0L, '#'),MovedOneStep(1L, ' '),AtOxygenSystem(2L, 'O'), NotCharted(3L, '.'),   Start(4L,'S');
    companion object {
        fun create(value:Number) = values().first{it.value == value}
    }
}

fun makeMove(program:Program, movementCommand:MovementCommand ):StatusCode {
    program.input += movementCommand.value
    program.execute()
    return StatusCode.create(program.output.last())
}

fun solveProblemOne(sourceCode:List<Number>):Pair<List<MovementCommand>,MutableMap<Position,StatusCode>> {

    val positionsVisited = mutableMapOf<Position, StatusCode>()
    positionsVisited[Position(0,0)] = StatusCode.Start
    var bestRoute :List<MovementCommand> = listOf()

    fun moveUntilBlocked(program:Program, moves:Int = 1, position:Position = Position(0,0),movementCommands:List<MovementCommand> = listOf()){

        MovementCommand.values().forEach {movementCommand ->
            val imageAtNewPositon = positionsVisited[position + movementCommand.move]

            if (imageAtNewPositon == null) {
                val programCopy = program.copy()
                val statusCode = makeMove(programCopy,movementCommand)
                val adjacentPosition = position + movementCommand.move
                positionsVisited[adjacentPosition] = statusCode

                when (statusCode) {
                    StatusCode.MovedOneStep -> {
                        moveUntilBlocked(programCopy,moves + 1, adjacentPosition, movementCommands + movementCommand)
                    }
                    StatusCode.HitWall ->{
                    }
                    StatusCode.AtOxygenSystem -> {
                        val routeTaken = movementCommands + movementCommand
                        if (bestRoute.isEmpty() || routeTaken.size < bestRoute.size ) {
                            bestRoute = routeTaken
                        }
                        println("At Oxygen $moves")
                    }
                }
            }
        }
    }
    moveUntilBlocked(Program(sourceCode,listOf()))
    return Pair(bestRoute,positionsVisited)
}

fun findPositionsAdjacentToOxygen(map:Map<Position, StatusCode>):List<Position> {
    val result = mutableListOf<Position>()
    map.yRange().forEach{y ->
        map.xRange().forEach { x ->
            val statusCode = map[Position(x,y)] ?: StatusCode.NotCharted
            if (statusCode == StatusCode.AtOxygenSystem) {
                MovementCommand.values().forEach{
                    val adjacentPosition = Position(x,y) + it.move
                    val statusCodeAtAdjacentPosition = map[adjacentPosition] ?: StatusCode.NotCharted
                    if (statusCodeAtAdjacentPosition == StatusCode.MovedOneStep) {
                        result += adjacentPosition
                    }
                }
            }
        }
    }
    return result
}

fun solveProblemTwo(sourceCode: List<Number>):Int {
    val (route,map) = solveProblemOne(sourceCode)
    map.print()
    map[Position(0,0)] = StatusCode.MovedOneStep // reset this as it was set to "Start"
    var mapIsFull = false
    var minutes = 0

    while (!mapIsFull) {
        val listOfPositionsAdjacentToOxygen = findPositionsAdjacentToOxygen(map)
        if (listOfPositionsAdjacentToOxygen.isEmpty()) {
            mapIsFull = true
        } else {
            minutes++
            map.applyStatusCodes(listOfPositionsAdjacentToOxygen)
        }
    }
    return minutes
}

fun MutableMap<Position, StatusCode>.applyStatusCodes(list:List<Position>, statusCode: StatusCode = StatusCode.AtOxygenSystem) =
    list.forEach { position ->
        this[position] = statusCode
    }

fun <T>Map<Position, T>.xRange() = (keys.map{it.x}.min() ?: 0)..(keys.map{it.x}.max() ?: 0)
fun <T>Map<Position, T>.yRange() = (keys.map{it.y}.min() ?: 0)..(keys.map{it.y}.max() ?: 0)
fun MutableMap<Position, StatusCode>.print() {
    yRange().forEach{y -> xRange().forEach { x -> print((this[Position(x,y)] ?: StatusCode.NotCharted).image) }
       println()
    }
}