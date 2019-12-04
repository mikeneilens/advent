
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
    if (codeAsList.containsPairNotInGroup()) return false
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
/*
fun List<Int>.containsPairNotInGroup() = this.fold(Triple(-1,false, -1)){(previousDigit, result, previousGroup:Int), digit ->
    if (digit == previousDigit)
        if (digit != previousGroup)
            Triple(digit,true, digit)
        else
            Triple(digit, false,digit)
    else Triple(digit, result, -1)
}.second
*/
fun List<Int>.containsPairNotInGroup():Boolean {
    var ndx = 0
    var listOfGroups=mutableListOf<List<Int>>()
    while (ndx < this.size) {
        val digit = this[ndx]
        var group = mutableListOf(digit)
        ndx +=1
        while ((ndx < this.size) && (this[ndx]== digit )) {
            group.add(digit)
            ndx +=1
        }
        listOfGroups.add(group)
    }
    println("$this ${listOfGroups}" )
    return listOfGroups.map{it.size}.filter{it == 2}.isNotEmpty()
}