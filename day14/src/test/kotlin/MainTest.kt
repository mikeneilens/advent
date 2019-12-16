import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `test formulas are created correctly`() {
        val data = "10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL"

        val formulaSpecs = parseFormulas(data)

        val OREformula = Formula(1 ,"ORE", listOf())
        val AFormula = Formula(10 ,"A", listOf(Pair(10, OREformula)))
        val BFormula = Formula(1 ,"B", listOf(Pair(1, OREformula)))
        val CFormula = Formula(1, "C", listOf(Pair(7, AFormula), Pair(1, BFormula)))
        val DFormula = Formula(1, "D", listOf(Pair(7, AFormula), Pair(1, CFormula)))
        val EFormula = Formula(1, "E",listOf(Pair(7, AFormula), Pair(1, DFormula)))
        val FUELFormula = Formula(1,"FUEL", listOf(Pair(7,AFormula), Pair(1,EFormula)))

        val caculatedformulaA = createFormulas(formulaSpecs,"A")
        assertEquals(AFormula, caculatedformulaA)

        val caculatedformulaC = createFormulas(formulaSpecs,"C")
        assertEquals(CFormula, caculatedformulaC)

        val caculatedformulaD = createFormulas(formulaSpecs,"D")
        assertEquals(DFormula, caculatedformulaD)

        val caculatedformulaE = createFormulas(formulaSpecs,"E")
        assertEquals(EFormula, caculatedformulaE)

        val caculatedformulaFUEL = createFormulas(formulaSpecs,"FUEL")
        assertEquals(FUELFormula, caculatedformulaFUEL)

    }

    //@Test
    fun`Example one calculates the numnber of ORE correctly`() {
        val data = "10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL"

        val formulaSpecs = parseFormulas(data)
        val formulaForFUEL = createFormulas(formulaSpecs,"FUEL")
        val ORENeeded = formulaForFUEL.calcFuel(1)
        assertEquals(31, ORENeeded)
    }

    @Test
    fun`Example two calculates the numnber of ORE correctly`() {
        val data = "9 ORE => 2 A\n" +
                "8 ORE => 3 B\n" +
                "7 ORE => 5 C\n" +
                "3 A, 4 B => 1 AB\n" +
                "5 B, 7 C => 1 BC\n" +
                "4 C, 1 A => 1 CA\n" +
                "2 AB, 3 BC, 4 CA => 1 FUEL"

        val formulaSpecs = parseFormulas(data)
        val formulaForFUEL = createFormulas(formulaSpecs,"FUEL")
        val ORENeeded = formulaForFUEL.calcFuel(1)
        
        assertEquals(165, ORENeeded)
    }


}