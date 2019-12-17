import javafx.geometry.Pos

data class Position(val x:Int, val y:Int) {
    operator fun plus(other:Position):Position = Position(this.x + other.x, this.y + other.y)
}
enum class Content(val value:Char) {
    Empty ('.'),
    Scaffold('#'),
    Intersection('O');
    companion object {
        fun create(value:Char):Content {
            return when (value) {
                '#' -> Scaffold
                else -> Empty
            }
        }
    }
}

fun getScaffold(sourceCode:List<Number>):Map<Position, Content> {
    val program = Program(sourceCode,listOf())
    program.execute()
    val scaffoldAsText =   program.output.map{it.toChar().toString()}.fold(""){a,e -> a + e}
    return convertTextToMap(scaffoldAsText)
}

fun printScaffold(scaffoldMap:Map<Position, Content>) {
    val xRange = (scaffoldMap.keys.map{it.x}.min() ?: 0)..(scaffoldMap.keys.map{it.x}.max() ?: 0)
    val yRange = (scaffoldMap.keys.map{it.y}.min() ?: 0)..(scaffoldMap.keys.map{it.y}.max() ?: 0)
    yRange.forEach{y -> xRange.forEach { x -> print(scaffoldMap[Position(x,y)]?.value) }
        println() }
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

fun updateScaffoldIntersections(scaffoldMap: MutableMap<Position, Content>):Int {
    val surroundingPositions = listOf(Position(-1,0),Position(0,-1),Position(1,0),Position(0,1))
    scaffoldMap.forEach { position, content ->
        if ((content == Content.Scaffold) && surroundingPositions.all{scaffoldMap[position + it] == Content.Scaffold}) {
            scaffoldMap[position] = Content.Intersection
        }
    }
    return scaffoldMap.filter{it.value == Content.Intersection}.keys.fold(0){ a, position -> a + (position.x * position.y) }
}
