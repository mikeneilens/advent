enum class Direction(val move:Position) {
    Left (Position(-1,0)),
    Right(Position(1,0)),
    Up(Position(0,-1)),
    Down(Position(0,1));

    fun turnLeft() = when(this) {
        Left -> Down
        Down -> Right
        Right -> Up
        Up -> Left
    }
    fun turnRight() = when(this) {
        Left -> Up
        Up -> Right
        Right -> Down
        Down -> Left
    }
}

data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position):Position = Position(this.x + other.x, this.y + other.y)
}
enum class Content(val value:Char) {
    Empty ('.'),
    Scaffold('#'),
    Robot('^'),
    Intersection('O'),
    Error('!');
    companion object {
        fun create(value:Char):Content = values().filter{it.value == value}.firstOrNull() ?: Content.Error
    }
}

typealias ScaffoldMap = MutableMap<Position, Content>

fun ScaffoldMap.canMove(currentPosition:Position, direction:Direction) = this[(currentPosition + direction.move)] == Content.Scaffold
fun ScaffoldMap.canTurnLeft(currentPosition:Position, direction:Direction) = this[(currentPosition + direction.turnLeft().move)] == Content.Scaffold
fun ScaffoldMap.canTurnRight(currentPosition:Position, direction:Direction) = this[(currentPosition + direction.turnRight().move)] == Content.Scaffold
fun ScaffoldMap.positionOfRobot():Position = filter{ it.value == Content.Robot}.keys.first()
fun ScaffoldMap.xRange() = (keys.map{it.x}.min() ?: 0)..(keys.map{it.x}.max() ?: 0)
fun ScaffoldMap.yRange() = (keys.map{it.y}.min() ?: 0)..(keys.map{it.y}.max() ?: 0)


fun getScaffold(sourceCode:List<Number>):Map<Position, Content> {
    val program = Program(sourceCode,listOf())
    program.execute()
    val scaffoldAsText =   program.output.map{it.toChar().toString()}.fold(""){a,e -> a + e}
    return convertTextToMap(scaffoldAsText)
}

fun ScaffoldMap.printScaffold() {
    yRange().forEach{y -> xRange().forEach { x -> print(this[Position(x,y)]?.value) }
        println() }
}
fun convertTextToMap(scaffoldText:String):Map<Position, Content> {
    val scaffoldMap = mutableMapOf<Position,Content>()
    val rows = scaffoldText.split("\n")
    val x = rows.last()
    rows.forEachIndexed{ y, row ->
        if (row.length > 0) {
            row.toList().forEachIndexed{x, char ->
                scaffoldMap[Position(x,y)] = Content.create(char)
            }
        }
    }
    scaffoldMap.printScaffold()
    return scaffoldMap
}

fun  ScaffoldMap.updateScaffoldIntersections():Int {
    val surroundingPositions = listOf(Position(-1,0),Position(0,-1),Position(1,0),Position(0,1))
    forEach { position, content ->
        if ((content == Content.Scaffold) && surroundingPositions.all{this[position + it] == Content.Scaffold}) {
            this[position] = Content.Intersection
        }
    }
    return filter{it.value == Content.Intersection}.keys.fold(0){ a, position -> a + (position.x * position.y) }
}

fun ScaffoldMap.getRoutes():List<Pair<String, Int>> {
    var robotPosition = positionOfRobot()
    var robotDirection = Direction.Up
    val result = mutableListOf<Pair<String, Int>>()

    while (canMove(robotPosition, robotDirection) || canTurnLeft(robotPosition, robotDirection) || canTurnRight(robotPosition, robotDirection)) {

        val turn = if (!canMove(robotPosition, robotDirection)) {
            if (canTurnLeft(robotPosition, robotDirection)) "L"
            else "R"
        } else {
            ""
        }

        robotDirection = if (!canMove(robotPosition, robotDirection)) {
            if (canTurnLeft(robotPosition, robotDirection)) robotDirection.turnLeft()
            else robotDirection.turnRight()
        } else {
            robotDirection
        }

        var steps = 0

        while (canMove(robotPosition, robotDirection)) {
            robotPosition += robotDirection.move
            steps++
        }
        result.add( Pair(turn, steps))

    }
    return result
}
fun List<Pair<String, Int>>.findFunctions(): List<Triple<List<Pair<String, Int>>, List<Pair<String, Int>>, List<Pair<String, Int>>>> {
    var result = mutableListOf<Triple<List<Pair<String, Int>>,List<Pair<String, Int>>,List<Pair<String, Int>> >>()
    for (i in 1..5) {
        for (j in 1..5) {
            for (k in 1..5) {
                if (i + j + k <= size) {
                    val firstFunction = chunked(i).first()
                    var remainingRoutes = chunked(i).dropWhile{it == firstFunction}.flatMap { it }

                    val secondFunction = remainingRoutes.chunked(j).first()

                    var remainingSize = Int.MAX_VALUE
                    while (remainingSize > remainingRoutes.size ){
                        remainingSize = remainingRoutes.size
                        remainingRoutes = remainingRoutes.chunked(j).dropWhile{it == secondFunction}.flatMap{ it }
                        .chunked(i).dropWhile { it == firstFunction }.flatMap { it }
                    }

                    val thirdFunction = remainingRoutes.chunked(k).first()

                    result.add(Triple(firstFunction, secondFunction, thirdFunction))
                }
            }
        }
    }
    return result
}

fun List<Pair<String, Int>>.canTraverseRoute(functions:Triple<List<Pair<String, Int>>,List<Pair<String, Int>>,List<Pair<String, Int>>>, routeTaken:String="" ):String{
    val A = functions.first
    val B = functions.second
    val C = functions.third

    return when (true) {
        size == 0 -> routeTaken
        routeTaken.length >= 20 -> ""
        (take(A.size) == A) -> drop(A.size).canTraverseRoute(functions, routeTaken + "A,")
        (take(B.size) == B) -> drop(B.size).canTraverseRoute(functions, routeTaken + "B,")
        (take(C.size) == C) -> drop(C.size).canTraverseRoute(functions, routeTaken + "C,")
        else -> ""
    }
}


fun calcDust(sourceCode:List<Number>) {
    val scaffoldMap = getScaffold(sourceCode).toMutableMap()

    val routes = scaffoldMap.getRoutes()
    println(routes)

    val listOfFunctions = routes.findFunctions()

    val (functionText, functions) =  listOfFunctions.map{
        Pair(routes.canTraverseRoute(it), it)}.filter{it.first.isNotEmpty() }.first()

    val movementRoutine = functionText.removeSuffix(",").split(",").flatMap{listOf(it,",")}.map{it.first()}.map{it.toInt()}.dropLast(1) + 10
    val A = functions.first.flatMap{it.toAscii() + 44}.dropLast(1) + 10
    val B = functions.second.flatMap{it.toAscii() + 44}.dropLast(1) + 10
    val C = functions.third.flatMap{it.toAscii() + 44}.dropLast(1) + 10

    val programInput = (movementRoutine + A + B + C).map{it.toLong()}

    val program = Program(sourceCode,programInput)
    program.execute()
    println(program.output.map { it.toChar() })
}

fun Pair<String,Int>.toAscii():List<Int> {
    return first.toList().flatMap{ listOf(it.toInt(),44)} + second.toString().toList().flatMap{ listOf(it.toInt(),44)}.dropLast(1)
}
