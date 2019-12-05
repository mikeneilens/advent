import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `sample data 1 returns data in the exmaple`() {
        val sampleData = listOf(
            1,9,10,3,
            2,3,11,0,
            99,
            30,40,50)
        val sampleResult = listOf(
            3500,9,10,70,
            2,3,11,0,
            99,
            30,40,50)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 2 returns data in the exmaple`() {
        val sampleData = listOf(
            1,0,0,0,99)
        val sampleResult = listOf(
            2,0,0,0,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 3 returns data in the exmaple`() {
        val sampleData = listOf(
            2,3,0,3,99)
        val sampleResult = listOf(
            2,3,0,6,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 4 returns data in the exmaple`() {
        val sampleData = listOf(
            2,4,4,5,99,0)
        val sampleResult = listOf(
            2,4,4,5,99,9801)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 5 returns data in the exmaple`() {
        val sampleData = listOf(
            1,1,1,4,99,5,6,0,99)
        val sampleResult = listOf(
            30,1,1,4,2,5,6,0,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `Part one`() {
        val sampleData = listOf(1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0)
        val result = process(sampleData)
        println("Part one: ${result[0]}")
    }
    @Test
    fun `Part two`() {
        val sampleData = listOf(1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0)
        val (parameter1, parameter2) =  findInputsThatCreateAValue(sampleData, 19690720)
        println("Part two: $parameter1$parameter2")
    }
    @Test
    fun `opcode 2 is an op of 2`() {
        assertEquals(2,Opcode(2).operation)
    }
 @Test
    fun `opcode 1002 is an op of 2`() {
        assertEquals(2,Opcode(1002).operation)
    }

    @Test
    fun `3,0,4,0,99 outputs what is input`() {
        val result = processDay5(listOf(3,0,4,0,99))
        assertEquals(1, result.output)
    }
    @Test
    fun `1002,4,3,4,33 gives 1002,4,3,4,99`() {
        val result = process(listOf(1002,4,3,4,33))
        assertEquals (listOf(1002, 4, 3, 4, 99),result)
    }
    @Test
    fun `1101,100,-1,4,0 stores result of adding 100 to -1 in position 4`() {
        val result = process(listOf(1101,100,-1,4,0))
        assertEquals (listOf(1101, 100, -1, 4, 99),result)
    }

    @Test
    fun `Day 5 part one`() {
        val sampleData = listOf(3,225,1,225,6,6,1100,1,238,225,104,0,1102,27,28,225,1,113,14,224,1001,224,-34,224,4,224,102,8,223,223,101,7,224,224,1,224,223,223,1102,52,34,224,101,-1768,224,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,1002,187,14,224,1001,224,-126,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1102,54,74,225,1101,75,66,225,101,20,161,224,101,-54,224,224,4,224,1002,223,8,223,1001,224,7,224,1,224,223,223,1101,6,30,225,2,88,84,224,101,-4884,224,224,4,224,1002,223,8,223,101,2,224,224,1,224,223,223,1001,214,55,224,1001,224,-89,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,1101,34,69,225,1101,45,67,224,101,-112,224,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1102,9,81,225,102,81,218,224,101,-7290,224,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,1101,84,34,225,1102,94,90,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1007,677,677,224,102,2,223,223,1005,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1005,224,344,101,1,223,223,1008,677,677,224,102,2,223,223,1005,224,359,101,1,223,223,8,226,677,224,1002,223,2,223,1006,224,374,101,1,223,223,108,226,677,224,1002,223,2,223,1006,224,389,1001,223,1,223,1107,226,677,224,102,2,223,223,1005,224,404,1001,223,1,223,7,226,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,434,1001,223,1,223,1107,226,226,224,1002,223,2,223,1006,224,449,101,1,223,223,1108,226,226,224,1002,223,2,223,1005,224,464,101,1,223,223,8,677,226,224,102,2,223,223,1005,224,479,101,1,223,223,8,226,226,224,1002,223,2,223,1006,224,494,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,509,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,524,1001,223,1,223,1108,677,226,224,102,2,223,223,1006,224,539,101,1,223,223,1008,677,226,224,102,2,223,223,1006,224,554,101,1,223,223,107,226,677,224,1002,223,2,223,1006,224,569,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,7,677,226,224,102,2,223,223,1005,224,599,101,1,223,223,1008,226,226,224,1002,223,2,223,1005,224,614,1001,223,1,223,107,226,226,224,1002,223,2,223,1005,224,629,101,1,223,223,7,226,226,224,102,2,223,223,1006,224,644,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,659,101,1,223,223,108,677,677,224,102,2,223,223,1005,224,674,1001,223,1,223,4,223,99,226)
        val result = processDay5(sampleData)
        println("Day 5 part one: ${result.output}" )
    }
}