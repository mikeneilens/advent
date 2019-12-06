fun process(sampleData: List<Int>): List<Int> {
    val result = sampleData.toMutableList()
    for (index in (0..(result.size - 1)) step 4) {
        val opcode = result[index]
        when (opcode) {
            1 -> result[result[index + 3]] = result[result[index + 1]] + result[result[index + 2]]
            2 -> result[result[index + 3]] = result[result[index + 1]] * result[result[index + 2]]
            99 -> return result
        }
    }
    return result
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