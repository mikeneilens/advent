enum class Tile(val symbol:Char, val value:Long) {
    Empty(' ',0L),
    Wall ('#',1L),
    Block ( '$',2L) ,
    Horizontal('-',3L),
    Ball('O',4L)
}
fun createTile(id:Number):Tile = when (id) {
    Tile.Wall.value -> Tile.Wall
    Tile.Block.value -> Tile.Block
    Tile.Horizontal.value -> Tile.Horizontal
    Tile.Ball.value -> Tile.Ball
    else -> Tile.Empty
}
data class Position(val x:Long, val y:Long)

enum class BatMove(val value:Number) { Left(-1), Right(+1), DoNotMove(0) }

typealias  Screen = MutableMap<Position, Tile>

fun updateScreen(sourceCode:List<Number>):Screen {
    val screen = mutableMapOf<Position, Tile>()
    val program = Program(sourceCode, listOf())
    program.execute()
    screen.process(program.output)
    return screen
}
fun Screen.process(output:List<Number>) {
    output.chunked(3).forEach {
        val x = it[0]
        val y = it[1]
        val tile = createTile(it[2])
        if (!((x == -1L) && (y==0L)))  this[Position(x, y)] = tile
    }
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

fun List<Number>.positionOf(tile:Tile) = chunked(3).firstOrNull(){it[2] == tile.value}?.run{ Position(this[0],this[1]) }
fun List<Number>.score() = chunked(3).firstOrNull{ it[0] == -1L && it[1] == 0L }?.elementAt(2)

class Game(var score:Number = 0, var rounds:Int = 0, var  batPosition:Position = Position(999,999), var  ballPosition:Position = Position(999,999), private var ballPreviousPosition:Position = Position(999,999), var startOfOutput:Int = 0) {
    fun startNextRound(outputSize:Int) {
        ++rounds
        ballPreviousPosition = ballPosition
        startOfOutput += outputSize
    }
    private val nextPositionOfBall get() = when (true) {
        (ballPosition.x > ballPreviousPosition.x) -> Position(ballPosition.x + 1, ballPosition.y)
        (ballPosition.x < ballPreviousPosition.x) -> Position(ballPosition.x - 1, ballPosition.y)
        else -> ballPosition
    }
    fun batMoveValue() = when (true) {
        nextPositionOfBall.x < batPosition.x -> BatMove.Left.value
        nextPositionOfBall.x > batPosition.x -> BatMove.Right.value
        else -> BatMove.DoNotMove.value
    }
}


fun playGame(sourceCode: List<Number>):Number {
    val program = Program(sourceCode, listOf(0))
    val game = Game()

    while (!program.isFinished && game.rounds < 10000 ) {
        program.execute()

        val newData = program.output.drop(game.startOfOutput)
        newData.score()?.let{game.score = it}
        newData.positionOf(Tile.Ball)?.let {game.ballPosition = it}
        newData.positionOf(Tile.Horizontal)?.let {game.batPosition = it}

        program.input += program.input + game.batMoveValue()

        game.startNextRound(newData.size)
    }
    println(game.score)
    return game.score
}
