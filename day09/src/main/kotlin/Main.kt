typealias Number = Long
fun Int.toNumber() = this.toLong()

enum class ParameterMode { PositionMode, ImmediateMode, RelativeMode}
fun getParameterMode(value:Char) = when(value) {
    '0' -> ParameterMode.PositionMode
    '1' -> ParameterMode.ImmediateMode
    '2' -> ParameterMode.RelativeMode
    else -> ParameterMode.PositionMode
}

class Opcode (code:Number) {
    val operation:Int
    val parameterModes:List<ParameterMode>

    init {
        val list = code.toString().toList()
        val codeAsList = when (list.size) {
            0 -> listOf('0','0','0','0','0')
            1 -> listOf('0','0','0','0') + list
            2 -> listOf('0','0','0') + list
            3 -> listOf('0','0') + list
            4 -> listOf('0') + list
            else -> list
        }
        operation = (codeAsList[3].toString() + codeAsList[4].toString()).toInt()
        parameterModes = listOf(getParameterMode(codeAsList[2]), getParameterMode(codeAsList[1]), getParameterMode(codeAsList[0]) )
    }
}

class Program (_instructions:List<Number>, var input:List<Number> = listOf(1)) {
    val instructions:MutableMap<Number,Number> = mutableMapOf()
    var output = listOf<Number>()
    private var position = 0L
    var relativeBase = 0L

    init {
        _instructions.mapIndexed{key,value -> instructions[key.toNumber()] = value}
    }

    private fun instructionAt(position:Number) = instructions[position] ?: 0

    private val opCode get() = Opcode(instructionAt(position))
    val isFinished get() = (position >= instructions.size) || (opCode.operation == 99)
    private val isWaitingForInput get() = (functions[opCode.operation] == readInput) && (input.isEmpty())

    fun execute() {
        while ((!isFinished) && (!isWaitingForInput ) ) {
            performNextOperation()
        }
    }

    private fun performNextOperation() = functions[opCode.operation]?.invoke()

    private val add = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        val outputAddress = getOutputAddressFromParam(3)
        instructions[outputAddress] = firstValue + secondValue
        position += 4
    }
    private val multiply = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        val outputAddress = getOutputAddressFromParam(3)
        instructions[outputAddress] = firstValue * secondValue
        position += 4
    }
    private val readInput = fun() {
        val outputAddress = getOutputAddressFromParam(1)
        instructions[outputAddress] = input.first()
        position += 2
        input = input.drop(1)
    }
    private val writeToOutput = fun() {
        val firstParameter = getValueFromParam(1)
        output = output + firstParameter
        position += 2
    }
    private val jumpIfTrue = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        if (firstValue != 0L) position = secondValue else position += 3
    }
    private val jumpIfFalse = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        if (firstValue == 0L) position = secondValue else position += 3
    }
    private val lessThan = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        val outputAddress = getOutputAddressFromParam(3)
        if (firstValue < secondValue) instructions[outputAddress] = 1 else instructions[outputAddress] = 0
        position += 4
    }
    private val equals = fun() {
        val (firstValue, secondValue) = getValuesFromParams()
        val outputAddress = getOutputAddressFromParam(3)
        if (firstValue == secondValue) instructions[outputAddress] = 1 else instructions[outputAddress] = 0
        position += 4
    }
    private val adjustRelativeBase = fun() {
        val firstValue = getValueFromParam(1)
        relativeBase += firstValue
        position += 2
    }

    private val functions = mapOf(1 to add, 2 to multiply, 3 to readInput,4 to writeToOutput,5 to jumpIfTrue, 6 to jumpIfFalse, 7 to lessThan, 8 to equals, 9 to adjustRelativeBase)

    private fun getValuesFromParams():Pair<Number,Number> = Pair(getValueFromParam(1),getValueFromParam(2))

    private fun getValueFromParam(n:Int):Number = when (opCode.parameterModes[n - 1]) {
        ParameterMode.ImmediateMode -> instructionAt(position + n )
        ParameterMode.PositionMode -> instructionAt(instructionAt(position + n ))
        ParameterMode.RelativeMode -> instructionAt(relativeBase + instructionAt(position + n ))
    }

    private fun getOutputAddressFromParam(n:Int):Number = when (opCode.parameterModes[n - 1]) {
        ParameterMode.ImmediateMode -> instructionAt(position + n)
        ParameterMode.PositionMode -> instructionAt(position + n)
        ParameterMode.RelativeMode -> relativeBase + instructionAt(position + n)
    }

}

fun process(sampleData: List<Number>): Program = Program(sampleData).apply { execute() }

fun process(sampleData: List<Number>, input:List<Number> = listOf(1)): Program = Program(sampleData, input).apply{ execute()}

fun findInputsThatCreateAValue(sampleData:List<Number>, valueToFind:Number):Pair<Number,Number> {
    for (parameter1 in 0..99) {
        for (parameter2 in 0..99) {
            val freshData = sampleData.toMutableList()
            freshData[1] = parameter1.toNumber()
            freshData[2] = parameter2.toNumber()
            val resultOfProcess = process(freshData).instructions.values.toList()
            if (resultOfProcess[0] == valueToFind) return Pair(parameter1.toNumber(),parameter2.toNumber())
        }
    }
    return Pair(0,0)
}

fun calMaxThrusterSignal(sampleData:List<Number>, phaseSettings:List<Int>):Number{
    var outputFromPreviousAmp = 0L
    phaseSettings.forEach {phaseSetting ->
        outputFromPreviousAmp = process(sampleData, listOf(phaseSetting.toNumber(),outputFromPreviousAmp)).output.last()
    }
    return outputFromPreviousAmp
}

fun calcMaxOutput(sampleData:List<Number>, phaseSettings:List<Int>):Number {
    val permutationsOfPhaseSettings = permute(phaseSettings)
    val results = permutationsOfPhaseSettings.map{ phaseSetting -> calMaxThrusterSignal(sampleData,phaseSetting)}
    return results.max() ?: 0
}

fun calcMaxThrusterSignalUsingFeedback(sampleData:List<Number>, phaseSettings:List<Int>):Program {
    var lastOutput = 0L
    val programs = listOf(
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[0].toNumber())),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[1].toNumber())),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[2].toNumber())),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[3].toNumber())),
        Program(sampleData.toMutableList(),mutableListOf(phaseSettings[4].toNumber()))
    )
    while (programs.map{it.isFinished}.contains(false)) {
        programs.forEach{ program ->
            if (!program.isFinished) {
                program.input += lastOutput
                program.execute()
                lastOutput = program.output.last()
            }
        }
    }
    return programs[4]
}

fun calcMaxOutputUsingFeedback(sampleData:List<Number>, phaseSettings:List<Int>):Number {
    val permutationsOfPhaseSettings = permute(phaseSettings)
    val programs = permutationsOfPhaseSettings.map{ phaseSetting -> calcMaxThrusterSignalUsingFeedback(sampleData,phaseSetting)}
    return programs.map{it.output.last()}.max() ?: 0
}

