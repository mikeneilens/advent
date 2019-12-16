fun createLayers(sampleData: String, width:Int, height:Int): List<List<String>> {
    val layers = sampleData.chunked(width * height)
    return  layers.map{it.chunked(width)}
}

fun List<String>.concatLayer() =  this.fold(""){a,e -> a + e}

fun String.countSymbolsInAString(symbol:Char):Int = this.toList().filter{it == symbol}.size

fun findSumOfOneAndTwoDigitsOnLayerWithFewestZeroDigits(layers: List<List<String>> ):Int {
    var layerWithFewestZeros = listOf<String>()
    var fewestZeros = Int.MAX_VALUE
    for (layer in layers) {
        val zeroesInLayer = layer.concatLayer().countSymbolsInAString('0')
        if (zeroesInLayer < fewestZeros) {
            layerWithFewestZeros = layer
            fewestZeros = zeroesInLayer
        }
    }
    return layerWithFewestZeros.concatLayer().countSymbolsInAString('1') * layerWithFewestZeros.concatLayer().countSymbolsInAString('2')
}

fun createPixelList(layers: List<List<String>>, col: Int, row: Int): List<Char> = layers.map{ layer -> layer[row].toList()[col] }

fun getTopLayer(pixelList: List<Char>): Char = pixelList.mapNotNull { if (it == '2') null else it }.first()

fun createImage(sampleData: String, width: Int, height: Int) {
    val outputChar = mapOf('1' to "#", '0' to " ")
    val layers = createLayers(sampleData,width,height)
    (0 until height).forEach { row ->
        (0 until width).forEach { col ->
            val pixelList = createPixelList(layers, col, row)
            print(outputChar[getTopLayer(pixelList)])
        }
        println()
    }
}

