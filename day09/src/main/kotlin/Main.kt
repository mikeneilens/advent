
enum class ParameterMode { PositionMode, ImmediateMode }
fun getParameterMode(value:Char) = when(value) {
    '0' -> ParameterMode.PositionMode
    '1' -> ParameterMode.ImmediateMode
    else -> ParameterMode.PositionMode
}

class Opcode (private val code:Int) {
    private val codeAsList:List<Char> get() {
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
    val firstParameterMode get() = getParameterMode(codeAsList[2])
    val secondParameterMode get() = getParameterMode(codeAsList[1])
}

class Program (_instructions:MutableList<Int>, _input:MutableList<Int> = mutableListOf(1)) {
    val instructions:MutableList<Int>
    val input:MutableList<Int>
    init {
        instructions = _instructions
        input = _input
    }
    private var position = 0
    private var inputPosition = 0
    private val opCode get() = Opcode(instructions[position])
    var output = mutableListOf<Int>()
    val isFinished get() = (position >= instructions.size) || (opCode.operation == 99)
    private val isWaitingForInput get() = (functions[opCode.operation] == readInput) && (inputPosition >= input.size)


    private fun performNextOperation() = functions[opCode.operation]?.invoke()

    private val add = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue + secondValue
        position += 4
    }
    private val multiply = fun() {
        val (firstValue, secondValue) = getParameters()
        val outputAddress = instructions[position + 3]
        instructions[outputAddress] = firstValue * secondValue
        position += 4
    }
    private val readInput = fun() {
        val outputAddress = instructions[position + 1]
        instructions[outputAddress] = input[inputPosition]
        position += 2
        inputPosition += 1
    }
    private val writeToOutput = fun() {
        output.add( if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]])
        position += 2
    }
    private val jumpIfTrue = fun() {
        val (firstValue, secondValue) = getParameters()
        if (firstValue != 0) position = secondValue else position += 3
    }
    private val jumpIfFalse = fun() {
        val (firstValue, secondValue) = getParameters()
        if (firstValue == 0) position = secondValue else position += 3
    }
    private val lessThan = fun() {
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
    private val functions = mapOf(1 to add, 2 to multiply, 3 to readInput,4 to writeToOutput,5 to jumpIfTrue, 6 to jumpIfFalse, 7 to lessThan, 8 to equals)

    private fun getParameters():Pair<Int,Int> = Pair(getFirstParameter(),getSecondParameter())
    private fun getFirstParameter():Int = if (opCode.firstParameterMode == ParameterMode.ImmediateMode) instructions[position + 1] else instructions[instructions[position + 1]]
    private fun getSecondParameter():Int = if (opCode.secondParameterMode == ParameterMode.ImmediateMode) instructions[position + 2] else instructions[instructions[position + 2]]

    fun execute() {
        while ((!isFinished) && (!isWaitingForInput ) ) {
            performNextOperation()
        }
    }

}

fun process(sampleData: List<Int>): List<Int> = processDay5(sampleData).instructions

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

fun processDay5(sampleData: List<Int>, input:List<Int> = listOf(1)): Program = Program(sampleData.toMutableList(), input.toMutableList()).apply{execute()}

fun calMaxThrusterSignal(sampleData:List<Int>, phaseSettings:List<Int>):Int{
    var outputFromPreviousAmp = 0
    phaseSettings.forEach {phaseSetting ->
        outputFromPreviousAmp = processDay5(sampleData, listOf(phaseSetting,outputFromPreviousAmp)).output.last()
    }
    return outputFromPreviousAmp
}

fun calcMaxOutput(sampleData:List<Int>, phaseSettings:List<Int>):Int {
    val permutationsOfPhaseSettings = permute(phaseSettings)
    val results = permutationsOfPhaseSettings.map{ phaseSetting -> calMaxThrusterSignal(sampleData,phaseSetting)}
    return results.max() ?: 0
}

fun calcMaxThrusterSignalUsingFeedback(sampleData:List<Int>, phaseSettings:List<Int>):Program {
    var lastOutput = 0
    val programs = listOf(
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[0])),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[1])),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[2])),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[3])),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[4]))
    )
    while (programs.map{it.isFinished}.contains(false)) {
        programs.forEach{ program ->
            if (!program.isFinished) {
                program.input.add(lastOutput)
                program.execute()
                lastOutput = program.output.last()
            }
        }
    }
    return programs[4]
}

fun calcMaxOutputUsingFeedback(sampleData:List<Int>, phaseSettings:List<Int>):Int {
    val permutationsOfPhaseSettings = permute(phaseSettings)
    val programs = permutationsOfPhaseSettings.map{ phaseSetting -> calcMaxThrusterSignalUsingFeedback(sampleData,phaseSetting)}
    return programs.map{it.output.last()}.max() ?: 0
}
