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