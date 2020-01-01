fun createComputers(sourceCode:List<Number>):List<Program> =
    (0..49).map{networkAddress ->
        Program(sourceCode,listOf(networkAddress.toLong()))
    }

fun List<Program>.startEach(){
    forEach{ it.execute()}
}

fun List<List<Number>>.getInputForEachComputer():List<List<Number>> {
    val inputs = (0..(size - 1)).map{ mutableListOf<Number>() }
    forEach { output ->
        output.chunked(3).forEach { chunk ->
            val networkAddress = chunk[0].toInt()
            if (networkAddress < 50) {
                val X = chunk[1]
                val Y = chunk[2]
                inputs[networkAddress].add(X)
                inputs[networkAddress].add(Y)
            }
        }
    }
    inputs.forEach { input -> if (input.isEmpty()) input.add(-1) }
    return inputs
}

fun dayOne(sourceCode:List<Number>):Number {
    val computers = createComputers(sourceCode)

    var foundAdress255 = false
    var YValueAtAddress255 = 0L
    while (!foundAdress255) {
        val newInputs = computers.map{it.output}.getInputForEachComputer()
        computers.addNewInput(newInputs)
        computers.startEach()
        computers.getXYValueForOutputWithAddress255OrNull()?.let{(_,Y) ->
            YValueAtAddress255 = Y
            foundAdress255 = true
        }
     }

    return YValueAtAddress255
}

fun List<Program>.getXYValueForOutputWithAddress255OrNull():Pair<Number,Number>? {
    var XYValueAtAddress255:Pair<Number,Number>? = null
    forEach { computer ->
        computer.output.chunked(3).forEach{chunk -> if (chunk[0] == 255L) XYValueAtAddress255 = Pair(chunk[1],chunk[2])
    }}
    return XYValueAtAddress255
}

fun List<Program>.addNewInput(newInputs:List<List<Number>>) {
    zip(newInputs).forEach { (program, newInput) -> program.input += newInput  }
}

class NAT(var lastX:Number, var lastY:Number) {
    var lastInputs:List<List<Number>>  = listOf()
    var lastOutputs:List<List<Number>> = listOf()
    fun receivePacket(x:Number, y:Number) {
        lastX = x
        lastY = y
    }
    fun networkIsIdle(programs:List<Program>):Boolean {
        if (lastInputs == programs.map{it.input} && lastOutputs == programs.map{it.output}) {
            val x = 1
        }
        return lastInputs == programs.map{it.input} && lastOutputs == programs.map{it.output} && programs.map{it.input}.any{it.size == 1}
    }
    fun monitorNetwork(programs:List<Program>):Number? {
        var Y:Number? = null
        if (networkIsIdle(programs)) {
            println("network is idle lastY $lastY")
            programs[0].input = listOf(lastX, lastY)
            Y = lastY
        }
        lastInputs = programs.map{it.input}
        lastOutputs = programs.map{it.output}
        return Y
    }
}

fun dayTwo(sourceCode:List<Number>):Number? {

    val computers = createComputers(sourceCode)
    val NAT = NAT(0,0)
    var lastYUsedToResetTheNetwork:Number? = null
    var YUsedToResetNetwork:Number? = null
    var finished = false
    while (!finished) {
        val newInputs = computers.map{it.output}.getInputForEachComputer()
        computers.addNewInput(newInputs)
        NAT.monitorNetwork(computers)?.let{ YUsedToResetNetwork = it
            if (YUsedToResetNetwork == lastYUsedToResetTheNetwork) finished = true
            lastYUsedToResetTheNetwork = YUsedToResetNetwork
        }
        computers.startEach()
        computers.getXYValueForOutputWithAddress255OrNull()?.let{(X,Y) ->
            NAT.receivePacket(X,Y)
        }
    }

    return YUsedToResetNetwork
}