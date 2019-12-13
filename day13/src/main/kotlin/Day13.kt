

enum class Tile(val symbol:Char) {
    Empty(' '),
    Wall ('#'),
    Block ( '$') ,
    Horizontal('-'),
    Ball('O')
}
fun createTile(id:Number):Tile = when (id) {
    1L -> Tile.Wall
    2L -> Tile.Block
    3L -> Tile.Horizontal
    4L -> Tile.Ball
    else -> Tile.Empty
}
data class Position(val x:Long, val y:Long)

typealias  Screen = MutableMap<Position, Tile>

fun updateScreen(sourceCode:List<Number>):Screen {
    val screen = mutableMapOf<Position, Tile>()
    val program = Program(sourceCode, listOf())
    program.execute()
    screen.process(program.output)
    return screen
}
fun Screen.process(output:List<Number>):Number {
    var score = 0L
    output.chunked(3).forEach {
        val x = it[0]
        val y = it[1]
        val tile = createTile(it[2])
        if ((x == -1L) && (y==0L)) score = it[2] else this[Position(x, y)] = tile
    }
    return score
}
fun Screen.print() {
    val rangeX = (keys.map { it.x }.min() ?: -9999)..(keys.map { it.x }.max() ?: 9999)
    val rangeY = (keys.map { it.y }.min() ?: -9999)..(keys.map { it.y }.max() ?: 9999)
    rangeY.forEach { y ->
        rangeX.forEach { x ->
            this[Position(x, y)]?.let { print(it.symbol) } ?: print(Tile.Empty.symbol)
        }
        println()
    }
}
fun Screen.count(tile:Tile) = values.count{it == tile}

fun playGame(sourceCode: List<Number>):Number {
    val screen = mutableMapOf<Position, Tile>()
    val program = Program(sourceCode, listOf(0))
    var score = 0L
    var runs = 0
    var batPosition = Position(0,0)
    var ballLastPosition = Position(0,0)
    var ballPosition = Position(999,999)

    while (!program.isFinished && runs < 10000) {
        program.execute()
        score = screen.process(program.output)

        ballLastPosition = ballPosition
        ballPosition = screen.ballPosition()
        batPosition = screen.batPosition()
        val ballNextPosition = ballNextPosition(ballPosition,ballLastPosition)
        val batMove = when (true) {
            ballNextPosition.x < batPosition.x -> -1L
            ballNextPosition.x > batPosition.x -> +1L
            else -> 0L
        }
        program.input += program.input + batMove

        ++runs
    }
    screen.print()
    println(score)
    return score
}
fun Screen.batPosition() =  toList().first{(key,value) -> value == Tile.Horizontal} .first
fun Screen.ballPosition() =  toList().first{(key,value) -> value == Tile.Ball}.first
fun ballNextPosition(ballPosition:Position, ballPreviousPosition:Position) = when (true) {
    (ballPosition.x > ballPreviousPosition.x) -> Position(ballPosition.x + 1, ballPosition.y)
    (ballPosition.x < ballPreviousPosition.x) -> Position(ballPosition.x - 1, ballPosition.y)
    else -> ballPosition
}