

data class Position(val x:Int, val y:Int) {
    private val directions get() = listOf(Position(0,-1), Position(1,0), Position(0,1), Position(-1,0))
    fun move(directionNdx:Int) = Position(this.x + directions[directionNdx].x, this.y + directions[directionNdx].y)
}

class Paint(val value:Number)

fun Map<Position,Paint>.paintedPanels() = this.keys.size
fun Map<Position,Paint>.colorAt(position:Position) = this[position] ?: Paint(0)
fun MutableMap<Position,Paint>.paint(position:Position, paint:Paint) { this[position] = paint }

fun Map<Position,Paint>.print() {
    val rangeY = (this.keys.map{it.y}.min() ?: -999)..(this.keys.map{it.y}.max() ?: 999)
    val rangeX = (this.keys.map{it.x}.min() ?: -999)..(this.keys.map{it.x}.max() ?: 999)

    for (y in rangeY){
        for (x in rangeX) {
            val char = when (this[Position(x,y)]) {
                null -> ' '
                Paint(0) -> ' '
                Paint(1) -> '#'
                else -> ' '
            }
            print(char)
        }
        println()
    }
}


fun solvePartOne(sourceCode:List<Number>, initialPaint:Paint):Int {
    val program = Program(sourceCode,listOf())

    var robotPosition = Position(0,0)
    var robotDirectionNdx = 0
    val hullMap = mutableMapOf<Position, Paint>()
    hullMap.paint(robotPosition,initialPaint)

    while (!program.isFinished) {
        program.input += hullMap.colorAt(robotPosition).value

        program.execute()

        val color = program.output[program.output.lastIndex - 1]
        val turn = program.output[program.output.lastIndex ]

        hullMap.paint(robotPosition,Paint(color))

        robotDirectionNdx = changeDirection(robotDirectionNdx,turn)

        robotPosition = robotPosition.move(robotDirectionNdx)
    }
    hullMap.print()
    return hullMap.paintedPanels()
}

fun changeDirection(directionNdx:Int, turn:Number) =
    if (turn == 0L)
        if (directionNdx > 0) directionNdx - 1 else 3
    else
        if (directionNdx < 3) directionNdx + 1 else 0
