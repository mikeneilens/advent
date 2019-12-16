import kotlin.math.abs
import kotlin.math.ceil

fun List<Int>.repeatPattern(repeat:Int, lengthRequired:Int):List<Int> {
    val pattern = flatMap { value ->
        (1..repeat).map { value }
    }
    val timesToRepeat = ceil(1.0 * (lengthRequired + 1)/pattern.size).toInt()
    return (1..timesToRepeat).flatMap { pattern }
}

fun processSignal(patterns:List<List<Int>>, inputSignal:List<Int>,digit:Int):Int {
    val pattern = patterns[digit - 1]
    val total = inputSignal.mapIndexed{ndx, it -> it * pattern[ndx] }
    return abs(total.sum() % 10)
}

fun processPhase(patterns:List<List<Int>>, inputSignal:List<Int> ):List<Int> {
    return inputSignal.mapIndexed{ndx, it -> processSignal(patterns, inputSignal, ndx + 1)}
}

fun processPhases(basePattern:List<Int>, inputSignal:List<Int>, phases:Int):List<Int> {
    val patterns = patterns(basePattern, inputSignal.size)

    var signal = inputSignal
    (1..phases).forEach{
        signal = processPhase(patterns, signal)
    }
    return signal
}

//precomputes patterns to use with each digit to help performance. But I think this runs out of memory as it ends up 100000 * 100000 for part two
fun patterns(basePattern:List<Int>,qty:Int) = (1..qty).map{basePattern.repeatPattern(it,qty).drop(1)}

fun List<Int>.toInt() = map{it.toString()}.fold(""){a,e -> a + e  }.toInt()

