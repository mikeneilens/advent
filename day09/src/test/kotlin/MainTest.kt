import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `sample data 1 returns data in the exmaple`() {
        val sampleData = listOf<Number>(
            1,9,10,3,
            2,3,11,0,
            99,
            30,40,50)
        val sampleResult = listOf<Number>(
            3500,9,10,70,
            2,3,11,0,
            99,
            30,40,50)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 2 returns data in the exmaple`() {
        val sampleData = listOf<Number>(
            1,0,0,0,99)
        val sampleResult = listOf<Number>(
            2,0,0,0,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 3 returns data in the exmaple`() {
        val sampleData = listOf<Number>(
            2,3,0,3,99)
        val sampleResult = listOf<Number>(
            2,3,0,6,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 4 returns data in the exmaple`() {
        val sampleData = listOf<Number>(
            2,4,4,5,99,0)
        val sampleResult = listOf<Number>(
            2,4,4,5,99,9801)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `sample data 5 returns data in the exmaple`() {
        val sampleData = listOf<Number>(
            1,1,1,4,99,5,6,0,99)
        val sampleResult = listOf<Number>(
            30,1,1,4,2,5,6,0,99)
        val result = process(sampleData)
        assertEquals(sampleResult,result)
    }
    @Test
    fun `Day 02 Part one`() {
        val sampleData = listOf<Number>(1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0)
        val result = process(sampleData)
        println("Part one: ${result[0]}")
    }
    @Test
    fun `Part two`() {
        val sampleData = listOf<Number>(1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,19,5,23,2,9,23,27,1,5,27,31,1,5,31,35,1,35,13,39,1,39,9,43,1,5,43,47,1,47,6,51,1,51,13,55,1,55,9,59,1,59,13,63,2,63,13,67,1,67,10,71,1,71,6,75,2,10,75,79,2,10,79,83,1,5,83,87,2,6,87,91,1,91,6,95,1,95,13,99,2,99,13,103,1,103,9,107,1,10,107,111,2,111,13,115,1,10,115,119,1,10,119,123,2,13,123,127,2,6,127,131,1,13,131,135,1,135,2,139,1,139,6,0,99,2,0,14,0)
        val (parameter1, parameter2) =  findInputsThatCreateAValue(sampleData, 19690720)
        println("Day 02 Part two: $parameter1$parameter2")
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
        assertEquals(1, result.output[0])
    }
    @Test
    fun `1002,4,3,4,33 gives 1002,4,3,4,99`() {
        val result = process(listOf(1002,4,3,4,33))
        assertEquals (listOf<Number>(1002, 4, 3, 4, 99),result)
    }
    @Test
    fun `1101,100,-1,4,0 stores result of adding 100 to -1 in position 4`() {
        val result = process(listOf(1101,100,-1,4,0))
        assertEquals (listOf<Number>(1101, 100, -1, 4, 99),result)
    }

    @Test
    fun `Day 5 part one`() {
        val sampleData = listOf<Number>(3,225,1,225,6,6,1100,1,238,225,104,0,1102,27,28,225,1,113,14,224,1001,224,-34,224,4,224,102,8,223,223,101,7,224,224,1,224,223,223,1102,52,34,224,101,-1768,224,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,1002,187,14,224,1001,224,-126,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1102,54,74,225,1101,75,66,225,101,20,161,224,101,-54,224,224,4,224,1002,223,8,223,1001,224,7,224,1,224,223,223,1101,6,30,225,2,88,84,224,101,-4884,224,224,4,224,1002,223,8,223,101,2,224,224,1,224,223,223,1001,214,55,224,1001,224,-89,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,1101,34,69,225,1101,45,67,224,101,-112,224,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1102,9,81,225,102,81,218,224,101,-7290,224,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,1101,84,34,225,1102,94,90,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1007,677,677,224,102,2,223,223,1005,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1005,224,344,101,1,223,223,1008,677,677,224,102,2,223,223,1005,224,359,101,1,223,223,8,226,677,224,1002,223,2,223,1006,224,374,101,1,223,223,108,226,677,224,1002,223,2,223,1006,224,389,1001,223,1,223,1107,226,677,224,102,2,223,223,1005,224,404,1001,223,1,223,7,226,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,434,1001,223,1,223,1107,226,226,224,1002,223,2,223,1006,224,449,101,1,223,223,1108,226,226,224,1002,223,2,223,1005,224,464,101,1,223,223,8,677,226,224,102,2,223,223,1005,224,479,101,1,223,223,8,226,226,224,1002,223,2,223,1006,224,494,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,509,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,524,1001,223,1,223,1108,677,226,224,102,2,223,223,1006,224,539,101,1,223,223,1008,677,226,224,102,2,223,223,1006,224,554,101,1,223,223,107,226,677,224,1002,223,2,223,1006,224,569,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,7,677,226,224,102,2,223,223,1005,224,599,101,1,223,223,1008,226,226,224,1002,223,2,223,1005,224,614,1001,223,1,223,107,226,226,224,1002,223,2,223,1005,224,629,101,1,223,223,7,226,226,224,102,2,223,223,1006,224,644,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,659,101,1,223,223,108,677,677,224,102,2,223,223,1005,224,674,1001,223,1,223,4,223,99,226)
        val result = processDay5(sampleData)
        println("Day 5 part one: ${result.output.last()}" )
        assertEquals(16348437,result.output.last())
    }

    @Test
    fun `3,9,8,9,10,9,4,9,99,-1,8 returns 1 if input is equal to 8`() {
        val result = processDay5(listOf(3,9,8,9,10,9,4,9,99,-1,8),listOf<Number>(8))
        assertEquals(1,result.output.last())

        val result2 = processDay5(listOf<Number>(3,9,8,9,10,9,4,9,99,-1,8),listOf<Number>(7))
        assertEquals(0,result2.output.last())
    }
    @Test
    fun `3,9,7,9,10,9,4,9,99,-1,8 return 1 if input is less than 8`() {
        val result = processDay5(listOf<Number>(3,9,7,9,10,9,4,9,99,-1,8),listOf<Number>(7))
        assertEquals(1,result.output.last())

        val result2 = processDay5(listOf<Number>(3,9,7,9,10,9,4,9,99,-1,8),listOf<Number>(8))
        assertEquals(0,result2.output.last())
    }
    @Test
    fun `3,3,1108,-1,8,3,4,3,99 returns 1 if input is equal to 8`() {
        val result = processDay5(listOf<Number>(3,3,1108,-1,8,3,4,3,99),listOf<Number>(8))
        assertEquals(1,result.output.last())

        val result2 = processDay5(listOf<Number>(3,3,1108,-1,8,3,4,3,99),listOf<Number>(7))
        assertEquals(0,result2.output.last())
    }
    @Test
    fun `3,3,1107,-1,8,3,4,3,99 return 1 if input is less than 8`() {
        val result = processDay5(listOf<Number>(3,3,1107,-1,8,3,4,3,99),listOf<Number>(7))
        assertEquals(1,result.output.last())

        val result2 = processDay5(listOf<Number>(3,3,1107,-1,8,3,4,3,99),listOf<Number>(8))
        assertEquals(0,result2.output.last())
    }
    @Test
    fun `3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 retun 0 if output is 0`() {
        val result = processDay5(listOf<Number>(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9),listOf<Number>(0))
        assertEquals(0,result.output.last())
        val result2 = processDay5(listOf<Number>(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9),listOf<Number>(1))
        assertEquals(1,result2.output.last())
    }
    @Test
    fun `3,3,1105,-1,9,1101,0,0,12,4,12,99,1 retun 0 if output is 0`() {
        val result = processDay5(listOf<Number>(3,3,1105,-1,9,1101,0,0,12,4,12,99,1),listOf<Number>(0))
        assertEquals(0,result.output.last())
        val result2 = processDay5(listOf<Number>(3,3,1105,-1,9,1101,0,0,12,4,12,99,1),listOf<Number>(1))
        assertEquals(1,result2.output.last())
    }
    @Test
    fun `Output 999 if the input is below 8, output 1000 if the input is 8, output 1001 if the input is above 8`() {
        val sampleData = listOf<Number>(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
            1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
            999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99)
        val resultBelow8 = processDay5(sampleData,listOf<Number>(7))
        assertEquals(999, resultBelow8.output.last())

        val resultEqual8 = processDay5(sampleData,listOf<Number>(8))
        assertEquals(1000, resultEqual8.output.last())

        val resultAbove8 = processDay5(sampleData,listOf<Number>(9))
        assertEquals(1001, resultAbove8.output.last())
    }

    @Test
    fun `Day 5 part two`() {
        val sampleData = listOf<Number>(3,225,1,225,6,6,1100,1,238,225,104,0,1102,27,28,225,1,113,14,224,1001,224,-34,224,4,224,102,8,223,223,101,7,224,224,1,224,223,223,1102,52,34,224,101,-1768,224,224,4,224,1002,223,8,223,101,6,224,224,1,223,224,223,1002,187,14,224,1001,224,-126,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,1102,54,74,225,1101,75,66,225,101,20,161,224,101,-54,224,224,4,224,1002,223,8,223,1001,224,7,224,1,224,223,223,1101,6,30,225,2,88,84,224,101,-4884,224,224,4,224,1002,223,8,223,101,2,224,224,1,224,223,223,1001,214,55,224,1001,224,-89,224,4,224,102,8,223,223,1001,224,4,224,1,224,223,223,1101,34,69,225,1101,45,67,224,101,-112,224,224,4,224,102,8,223,223,1001,224,2,224,1,223,224,223,1102,9,81,225,102,81,218,224,101,-7290,224,224,4,224,1002,223,8,223,101,5,224,224,1,223,224,223,1101,84,34,225,1102,94,90,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1007,677,677,224,102,2,223,223,1005,224,329,101,1,223,223,1108,226,677,224,1002,223,2,223,1005,224,344,101,1,223,223,1008,677,677,224,102,2,223,223,1005,224,359,101,1,223,223,8,226,677,224,1002,223,2,223,1006,224,374,101,1,223,223,108,226,677,224,1002,223,2,223,1006,224,389,1001,223,1,223,1107,226,677,224,102,2,223,223,1005,224,404,1001,223,1,223,7,226,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1107,677,226,224,102,2,223,223,1006,224,434,1001,223,1,223,1107,226,226,224,1002,223,2,223,1006,224,449,101,1,223,223,1108,226,226,224,1002,223,2,223,1005,224,464,101,1,223,223,8,677,226,224,102,2,223,223,1005,224,479,101,1,223,223,8,226,226,224,1002,223,2,223,1006,224,494,1001,223,1,223,1007,226,677,224,1002,223,2,223,1006,224,509,1001,223,1,223,108,226,226,224,1002,223,2,223,1006,224,524,1001,223,1,223,1108,677,226,224,102,2,223,223,1006,224,539,101,1,223,223,1008,677,226,224,102,2,223,223,1006,224,554,101,1,223,223,107,226,677,224,1002,223,2,223,1006,224,569,101,1,223,223,107,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,7,677,226,224,102,2,223,223,1005,224,599,101,1,223,223,1008,226,226,224,1002,223,2,223,1005,224,614,1001,223,1,223,107,226,226,224,1002,223,2,223,1005,224,629,101,1,223,223,7,226,226,224,102,2,223,223,1006,224,644,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,659,101,1,223,223,108,677,677,224,102,2,223,223,1005,224,674,1001,223,1,223,4,223,99,226)
        val result = processDay5(sampleData,listOf<Number>(5))
        println("Day 5 part two: ${result.output.last()}" )
        assertEquals(6959377, result.output.last())
    }

    @Test
    fun `Day 7 sampe code 1`() {
        val sampleData = listOf<Number>(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
        val phaseSetting = listOf(4,3,2,1,0)
        val result = calMaxThrusterSignal(sampleData,phaseSetting)
        assertEquals(43210, result)
    }
    @Test
    fun `Day 7 sampe code 2`() {
        val sampleData = listOf<Number>(3,23,3,24,1002,24,10,24,1002,23,-1,23, 101,5,23,23,1,24,23,23,4,23,99,0,0)
        val phaseSetting = listOf(0,1,2,3,4)
        val result = calMaxThrusterSignal(sampleData,phaseSetting)
        assertEquals(54321, result)
    }
    @Test
    fun `Day 7 sampe code 3`() {
        val sampleData = listOf<Number>(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0)
        val phaseSetting = listOf(1,0,4,3,2)
        val result = calMaxThrusterSignal(sampleData,phaseSetting)
        assertEquals(65210, result)
    }
    @Test
    fun `Day 7 part one`() {
        val sampleData = listOf<Number>(3,8,1001,8,10,8,105,1,0,0,21,46,67,88,101,126,207,288,369,450,99999,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,102,3,9,9,101,2,9,9,4,9,99,3,9,102,4,9,9,101,5,9,9,102,5,9,9,101,3,9,9,4,9,99,3,9,1001,9,3,9,102,2,9,9,1001,9,5,9,102,4,9,9,4,9,99,3,9,102,3,9,9,1001,9,4,9,4,9,99,3,9,102,3,9,9,1001,9,3,9,1002,9,2,9,101,4,9,9,102,3,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99)
        val result = calcMaxOutput(sampleData,listOf(0,1,2,3,4))
        println("day07 part one $result")
        assertEquals(844468, result)
    }

    @Test
    fun `Day 7 part two sampe code 1`() {
        val sampleData = listOf<Number>(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26, 27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5)
        val phaseSettings = listOf(9,8,7,6,5)
        val result = calcMaxThrusterSignalUsingFeedback(sampleData,phaseSettings)
        assertEquals(139629729, result.output.last())
    }
    @Test
    fun `Day 7 part two sampe code 2`() {
        val sampleData = listOf<Number>(3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54, -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4, 53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10)
        val phaseSettings = listOf(9,7,8,5,6)
        val result = calcMaxThrusterSignalUsingFeedback(sampleData,phaseSettings)
        assertEquals(18216, result.output.last())
    }

    @Test
    fun `Day 7 part two`() {
        val sampleData = listOf<Number>(3,8,1001,8,10,8,105,1,0,0,21,46,67,88,101,126,207,288,369,450,99999,3,9,1001,9,5,9,1002,9,5,9,1001,9,5,9,102,3,9,9,101,2,9,9,4,9,99,3,9,102,4,9,9,101,5,9,9,102,5,9,9,101,3,9,9,4,9,99,3,9,1001,9,3,9,102,2,9,9,1001,9,5,9,102,4,9,9,4,9,99,3,9,102,3,9,9,1001,9,4,9,4,9,99,3,9,102,3,9,9,1001,9,3,9,1002,9,2,9,101,4,9,9,102,3,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99)
        val phaseSettings = listOf(9,8,7,6,5)
        val result = calcMaxOutputUsingFeedback(sampleData,phaseSettings)
        println("Day 7 part two: $result")
        assertEquals(4215746, result)
    }

}