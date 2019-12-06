
enum class ParameterMode { PositionMode, ImmediateMode }
fun getParamterMode(value:Char) = when(value) {
    '0' -> ParameterMode.PositionMode
    '1' -> ParameterMode.ImmediateMode
    else -> ParameterMode.PositionMode
}

class Opcode (val code:Int) {
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

class Program (val instructions:MutableList<Int>, val input:Int = 1) {
    var position = 0
    val opCode get() = Opcode(instructions[position])
    var output = 0
    val isFinished get() = (position >= instructions.size) || (opCode.operation == 99)

    fun performNextOperation() = functions[opCode.operation]?.let{function -> function()}

    val add = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue + secondValue
        position += 4
    }
    val multiply = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue * secondValue
        position += 4
    }
    val readInput = fun() {
        val outputAddress = instructions[position + 1]
        instructions[outputAddress] = input
        position += 2
    }
    val writeToOutput = fun() {
        output= if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
        position += 2
    }
    val jumpIfTrue = fun() {
        val (firstValue, secondValue) = getParameters()
        if (firstValue != 0) position = secondValue else position += 3
    }
    val jumpIfFalse = fun() {
        val (firstValue, secondValue) = getParameters()
        if (firstValue == 0) position = secondValue else position += 3
    }
    val lessThan = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        if (firstValue < secondValue) instructions[outputAddress] = 1 else instructions[outputAddress] = 0
        position += 4
    }
    val equals = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        if (firstValue == secondValue) instructions[outputAddress] = 1 else instructions[outputAddress] = 0
        position += 4
    }
    val functions = mapOf(1 to add, 2 to multiply, 3 to readInput,4 to writeToOutput,5 to jumpIfTrue, 6 to jumpIfFalse, 7 to lessThan, 8 to equals)

    fun getParameters():Pair<Int,Int> {
        val firstValue = getFirstParameter()
        val secondValue = getSecondParameter()
        return Pair(firstValue,secondValue)
    }
    fun getFirstParameter():Int = if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
    fun getSecondParameter():Int = if (opCode.secondParameterMode == ParameterMode.ImmediateMode) instructions[position + 2] else instructions[instructions[position + 2]]

}

fun process(sampleData: List<Int>): List<Int> {
    val program = Program(sampleData.toMutableList())
    while (!program.isFinished) {
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

fun processDay5(sampleData: List<Int>, input:Int = 1): Program {
    val program = Program(sampleData.toMutableList(), input)
    while (!program.isFinished) {
        program.performNextOperation()
    }
    return program
}