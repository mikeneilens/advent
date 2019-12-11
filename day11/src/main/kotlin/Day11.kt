typealias Hull = MutableMap<Position,Paint>
fun newHull() = mutableMapOf<Position,Paint>()

enum class Direction { North, East, South, West;
    fun changeDirection(turn:Turn) = when (this) {
        North -> if (turn == Turn.Left) West else East
        East -> if (turn == Turn.Left) North else South
        South -> if (turn == Turn.Left) East else West
        West -> if (turn == Turn.Left) South else North
    }
    val moveBy get() = when (this) {
        North -> Position(0,-1)
        East -> Position(1,0)
        South -> Position(0,1)
        West -> Position(-1,0)
    }
}
enum class Turn {Left, Right}
fun Number.toTurn() = if (this == 0L) Turn.Left else Turn.Right

data class Position(val x:Int = 0, val y:Int = 0) {
    fun move(direction:Direction) = Position(this.x + direction.moveBy.x, this.y + direction.moveBy.y)
}

data class Paint(val value:Number)

fun Hull.paintedPanels() = this.keys.size
fun Hull.colorAt(position:Position) = this[position] ?: Paint(0)
fun Hull.paint(position:Position, paint:Paint) { this[position] = paint }

fun Hull.print() {
    val rangeY = (this.keys.map{it.y}.min() ?: -999)..(this.keys.map{it.y}.max() ?: 999)
    val rangeX = (this.keys.map{it.x}.min() ?: -999)..(this.keys.map{it.x}.max() ?: 999)

    for (y in rangeY){
        for (x in rangeX) {
            val char = when (this[Position(x,y)]) {
                null -> ' '
                Paint(0) -> ' '
                Paint(1) -> '#'
                else -> '!'
            }
            print(char)
        }
        println()
    }
}


fun solveDay11(sourceCode:List<Number>, initialPaint:Paint):Int {
    val program = Program(sourceCode,listOf())

    var robotPosition = Position()
    var robotDirection = Direction.North
    val hull = newHull()
    hull.paint(robotPosition,initialPaint)

    while (!program.isFinished) {
        program.input += hull.colorAt(robotPosition).value

        program.execute()

        val color = program.output[program.output.lastIndex - 1]
        val turn = (program.output[program.output.lastIndex ]).toTurn()

        hull.paint(robotPosition,Paint(color))

        robotDirection = robotDirection.changeDirection(turn)

        robotPosition = robotPosition.move(robotDirection)
    }
    hull.print()
    return hull.paintedPanels()
}
