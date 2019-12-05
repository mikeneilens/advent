
sealed class HowToProceed {
    object Stop:HowToProceed()
    class Advance(val steps:Int):HowToProceed()
}

enum class ParameterMode { PositionMode, ImmediateMode }
fun getParamterMode(value:Char) = when(value) {
    '0' -> ParameterMode.PositionMode
    '1' -> ParameterMode.ImmediateMode
    else -> ParameterMode.PositionMode
}

class Opcode (val code:Int) {
    val howToProceed  = when (operation) {
        1,2 -> HowToProceed.Advance(4)
        3,4 -> HowToProceed.Advance(2)
        99 -> HowToProceed.Stop
        else -> HowToProceed.Stop
    }
    val codeAsList:List<Char> get() {
        val list = code.toString().toList()
        return when (list.size) {
            0 -> listOf('0','0','0','0','0')
            1 -> listOf('0','0','0','0') + list
            2 -> listOf('0','0','0') + list
            3 -> listOf('0','0') + list
            4 -> listOf('0') + list
            else -> return list
        }
    }

    val operation:Int  get() = (codeAsList[3].toString() + codeAsList[4].toString()).toInt()
    val firstParameterMode get() = getParamterMode(codeAsList[2])
    val secondParameterMode get() = getParamterMode(codeAsList[1])
    val thirdParameterMode get() = getParamterMode(codeAsList[0])

}

class Program (val instructions:MutableList<Int>) {
    var position = 0
    val opCode get() = Opcode(instructions[position])
    val input = 1
    var output = 0

    fun performNextOperation() {
        when (opCode.operation) {
            1 -> add()
            2 -> multiply()
            3 -> readInput()
            4 -> writeToOutput()
        }
        val howToProceed = opCode.howToProceed
        if (howToProceed is HowToProceed.Advance) {
            position = position + howToProceed.steps
        }
    }

    fun add() {
        val firstValue = if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
        val secondValue = if (opCode.secondParameterMode == ParameterMode.ImmediateMode) instructions[position + 2] else instructions[instructions[position + 2]]
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue + secondValue
    }
    fun multiply() {
        val firstValue = if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
        val secondValue = if (opCode.secondParameterMode == ParameterMode.ImmediateMode) instructions[position + 2] else instructions[instructions[position + 2]]
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue * secondValue
    }
    fun readInput() {
        val outputAddress = instructions[position + 1]
        instructions[outputAddress] = input
    }
    fun writeToOutput() {
        output= if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
    }
}

val input = 1
var output = 0

fun process(sampleData: List<Int>): List<Int> {
    val program = Program(sampleData.toMutableList())
    while ((program.position < program.instructions.size) && (program.opCode.howToProceed != HowToProceed.Stop)) {
        program.performNextOperation()
    }
    return program.instructions
}

fun findInputsThatCreateAValue(sampleData:List<Int>, valueToFind:Int):Pair<Int,Int> {
    for (parameter1 in 0..99) {
        for (parameter2 in 0..99) {
            val freshData = sampleData.toMutableList()
            freshData[1] = parameter1
            freshData[2] = parameter2
            val resultOfProcess = process(freshData)
            if (resultOfProcess[0] == valueToFind) return Pair(parameter1,parameter2)
        }
    }
    return Pair(0,0)
}

fun processDay5(sampleData: List<Int>): Program {
    val program = Program(sampleData.toMutableList())
    while ((program.position < program.instructions.size) && (program.opCode.howToProceed != HowToProceed.Stop)) {
        program.performNextOperation()
    }
    return program
}