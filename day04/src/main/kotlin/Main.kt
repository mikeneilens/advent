
fun validCodesInList(listOfCodes:List<Int>) =
    listOfCodes.fold(listOf<Int>()){ acc, code ->
        if (isAValidCode(code)) acc + code
        else acc
    }

fun isAValidCode(code: Int): Boolean {
    val codeAsList = code.toString().toList().map{it.toInt()}
    if (codeAsList.size != 6) return false
    if (!codeAsList.containsPair()) return false
    if (!codeAsList.isAscending()) return false
    return true
}

fun validCodesInListPartTwo(listOfCodes:List<Int>) =
    listOfCodes.fold(listOf<Int>()){ acc, code ->
        if (isAValidCodePartTwo(code)) acc + code
        else acc
    }

fun isAValidCodePartTwo(code: Int): Boolean {
    val codeAsList = code.toString().toList().map{it.toInt()}
    if (codeAsList.size != 6) return false
    if (!codeAsList.containsPairNotInGroup()) return false
    if (!codeAsList.isAscending()) return false
    return true
}

fun List<Int>.containsPair() = this.fold(Pair(-1,false)){(previousDigit, result), digit ->
    if (digit == previousDigit) Pair(digit,true)
    else Pair(digit, result)
}.second
fun List<Int>.isAscending() = this.fold(Pair(-1,true)){(previousDigit, result), digit ->
    if (digit < previousDigit) Pair(digit,false)
    else Pair(digit, result)
}.second

fun List<Int>.containsPairNotInGroup() = this.fold(Triple(-1,false, -1)){(previousDigit, result, previousGroup:Int), digit ->
    if (digit == previousDigit)
        if (digit != previousGroup)
            Triple(digit,true, digit)
        else
            Triple(digit, false,digit)
    else Triple(digit, result, previousGroup)
}.second