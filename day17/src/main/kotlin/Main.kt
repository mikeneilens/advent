data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position):Position = Position(this.x + other.x, this.y + other.y)
}
enum class Content(val value:Char) {
    Empty ('.'),
    Scaffold('#'),
    Intersection('O'),
    Robot('^');
    companion object {
        fun create(value:Char) = values().filter { it.value == value }.firstOrNull() ?: Empty
    }
}

typealias ScaffoldMap = MutableMap<Position, Content>

enum class Direction(val move:Position) {
    Up(Position(0,-1)),
    Down(Position(0,1)),
    Left(Position(-1,0)),
    Right(Position(1,0));
    fun turnLeft() = when(this) {
        Up -> Left
        Left -> Down
        Down -> Right
        Right -> Up
    }
    fun turnRight() = when(this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
    }
}

fun getScaffold(sourceCode:List<Number>):Map<Position, Content> {
    val program = Program(sourceCode,listOf())
    program.execute()
    val scaffoldAsText =   program.output.map{it.toChar().toString()}.fold(""){a,e -> a + e}
    return convertTextToMap(scaffoldAsText)
}

fun convertTextToMap(scaffoldText:String):Map<Position, Content> {
    val scaffoldMap = mutableMapOf<Position,Content>()
    val rows = scaffoldText.split("\n")
    rows.forEachIndexed{ y, row ->
        row.toList().forEachIndexed{x, char ->
            scaffoldMap[Position(x,y)] = Content.create(char)
        }
    }
    return scaffoldMap
}

fun ScaffoldMap.xRange() = (keys.map{it.x}.min() ?: 0)..(keys.map{it.x}.max() ?: 0)
fun ScaffoldMap.yRange() = (keys.map{it.y}.min() ?: 0)..(keys.map{it.y}.max() ?: 0)

fun ScaffoldMap.print() {
    val xRange = xRange()
    val yRange = yRange()
    yRange.forEach{y -> xRange.forEach { x -> print(this[Position(x,y)]?.value) }
        println() }
}

fun ScaffoldMap.updateIntersections():Int {
    val surroundingPositions = Direction.values().map{it.move}
    forEach { position, content ->
        if ((content == Content.Scaffold) && surroundingPositions.all{this[position + it] == Content.Scaffold}) {
            this[position] = Content.Intersection
        }
    }
    return filter{it.value == Content.Intersection}.keys.fold(0){ a, position -> a + (position.x * position.y) }
}

fun ScaffoldMap.findRobot() = filter{it.value == Content.Robot}.keys.first()
fun ScaffoldMap.canMove(position:Position, direction:Direction) = this[position + direction.move] == Content.Scaffold
fun ScaffoldMap.canTurnLeft(position:Position, direction:Direction) = this[position + direction.turnLeft().move] == Content.Scaffold
fun ScaffoldMap.canTurnRight(position:Position, direction:Direction) = this[position + direction.turnRight().move] == Content.Scaffold

fun ScaffoldMap.getMoves():List<Pair<String,Int>> {
    var robotPosition = findRobot()
    var robotDirection = Direction.Up
    val path = mutableListOf<Pair<String,Int>>()

    while (canMove(robotPosition, robotDirection) || canTurnLeft(robotPosition,robotDirection) || canTurnRight(robotPosition,robotDirection)) {
        var steps = 0
        var lastTurn= ""

        if (!canMove(robotPosition, robotDirection)) {
            lastTurn = when (true) {
                canTurnLeft(robotPosition,robotDirection) -> "L"
                canTurnRight(robotPosition,robotDirection) -> "R"
                else -> ""
            }
            robotDirection = when (true) {
                canTurnLeft(robotPosition,robotDirection) -> robotDirection.turnLeft()
                canTurnRight(robotPosition,robotDirection) -> robotDirection.turnRight()
                else -> robotDirection
            }
        }

        while (canMove(robotPosition, robotDirection)) {
            robotPosition = robotPosition + robotDirection.move
            steps = steps + 1
        }
        path.add(Pair(lastTurn,steps))
        println("Turn: $lastTurn $steps")
    }
    return path
}
fun List<Pair<String,Int>>.findRepeatingSequence(size:Int = this.size):List<Pair<String,Int>>?{
    val size = if (size < this.size) size else this.size
    if (size < 1) return null
    val dataChunked = this.chunked(size)
    return dataChunked[0]
}
fun List<Pair<String,Int>>.removeRepeatingSequence(repeatingSequence:List<Pair<String,Int>>?):List<Pair<String,Int>>?{
    if (repeatingSequence == null) return null
    var newSequence = mutableListOf<Pair<String,Int>>()
    val dataChunked = this.chunked(repeatingSequence.size)
    for (chunk in dataChunked) {
        if (chunk != repeatingSequence) {
            newSequence.addAll(chunk)
        }
    }
    return newSequence
}


