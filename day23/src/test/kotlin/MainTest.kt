import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `output is correctly converted to inputs` () {
        val output0 = listOf<Number>(1,2,3,2,3,4)// creates an input of 2,3 for computer1 and 3,4 for computer 2
        val output1 = listOf<Number>(2,5,6)// creates an input of 5,6 for computer2
        val output2 = listOf<Number>(1,7,8)// creates an input of 7,8 for computer1

        val expectedResult0 = listOf<Number>(-1)
        val expectedResult1 = listOf<Number>(2,3,7,8)
        val expectedResult2 = listOf<Number>(3,4,5,6)
        val result = listOf(output0,output1,output2).getInputForEachComputer()
        assertEquals(expectedResult0, result[0])
        assertEquals(expectedResult1, result[1])
        assertEquals(expectedResult2, result[2])
    }
    @Test
    fun `new inputs are added to computers`() {
        val program0 = Program(sourceCode,listOf(0))
        val program1 = Program(sourceCode,listOf(1))
        val program2 = Program(sourceCode,listOf(2))

        val newInput0 = listOf<Number>(-1)
        val newInput1 = listOf<Number>(2,3,7,8)
        val newInput2 = listOf<Number>(3,4,5,6)

        listOf(program0,program1,program2).addNewInput(listOf(newInput0, newInput1, newInput2))

        assertEquals(listOf<Number>(0,-1), program0.input)
        assertEquals(listOf<Number>(1,2,3,7,8), program1.input)
        assertEquals(listOf<Number>(2,3,4,5,6), program2.input)
    }
    @Test
    fun`day one`() {
        val YValueAtAddress255 = dayOne(sourceCode)
        assertEquals(17949, YValueAtAddress255)
    }

    @Test
    fun `day two`() {
        val Y = dayTwo(sourceCode)
        println(Y)
    }
}