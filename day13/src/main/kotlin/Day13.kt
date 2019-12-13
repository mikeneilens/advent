import sun.jvm.hotspot.opto.Block

enum class Tile {
    Empty,
    Wall,
    Block,
    Horizontal,
    Ball
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
fun Screen.process(output:List<Number>) {
    output.chunked(3).forEach {
        val x = it[0]
        val y = it[1]
        val tile = createTile(it[2])
        this[Position(x, y)] = tile
    }
}

fun Screen.count(tile:Tile) = values.count{it == tile}


