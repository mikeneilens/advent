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
    HitWall(0L, '#'),MovedOneStep(1L, ' '),AtOxygenSystem(2L, 'O');
    companion object {
        fun create(value:Number) = values().first{it.value == value}
    }
}

fun makeMove(program:Program, movementCommand:MovementCommand ):StatusCode {
    program.input += movementCommand.value
    program.execute()
    return StatusCode.create(program.output.last())
}

fun solveProblem(sourceCode:List<Number>):List<MovementCommand> {

    val mutableMap = mutableMapOf<Position, Char>()
    mutableMap[Position(0,0)] = 'S'
    var route :List<MovementCommand> = listOf()

    fun moveUntilBlocked(program:Program, moves:Int = 1, position:Position = Position(0,0),movementCommands:List<MovementCommand> = listOf()){

        val programs = listOf(program.copy(), program.copy(), program.copy(), program.copy())

        MovementCommand.values().forEach {movementCommand ->
            val imageAtNewPositon = mutableMap[position + movementCommand.move]

            if (imageAtNewPositon == null) {
                val ndx = (movementCommand.value - 1).toInt()
                val statusCode = makeMove(programs[ndx],movementCommand)
                mutableMap[position + movementCommand.move] = statusCode.image
                when (statusCode) {
                    StatusCode.MovedOneStep -> {
                        println("moved $moves")
                        moveUntilBlocked(programs[ndx],moves + 1, position + movementCommand.move, movementCommands + movementCommand)
                    }
                    StatusCode.HitWall ->{
                        println("hit wall $moves")
                    }
                    StatusCode.AtOxygenSystem -> {
                        route = movementCommands + movementCommand
                        println("At Oxygen $moves")
                    }
                }
            }
        }
    }
    moveUntilBlocked(Program(sourceCode,listOf()))
    mutableMap.print()
    return route
}

fun attemptRoute(sourceCode: List<Number>, movementCommands: List<MovementCommand>) {
    val program = Program(sourceCode,listOf())
    movementCommands.forEach {
        val statusCode = makeMove(program,it)
        if (statusCode == StatusCode.HitWall) {
            println("Error")
        }
        println(statusCode)
    }
}

fun MutableMap<Position, Char>.xRange() = (keys.map{it.x}.min() ?: 0)..(keys.map{it.x}.max() ?: 0)
fun MutableMap<Position, Char>.yRange() = (keys.map{it.y}.min() ?: 0)..(keys.map{it.y}.max() ?: 0)
fun MutableMap<Position, Char>.print() {
    yRange().forEach{y -> xRange().forEach { x -> print(this[Position(x,y)] ?: ' ') }
       println()
    }
}