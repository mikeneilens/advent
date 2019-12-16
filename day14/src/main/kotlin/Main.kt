import java.lang.Math.ceil

data class Formula (
    val batchSize:Int,
    val name:String,
    val components:List<Pair<Int, Formula>>
) {

    fun calcFuel(quantityRequested:Int, bank:MutableMap<String, Int> = mutableMapOf()):Int {
    }
}

data class FormulaSpec (
    val qty:Int,
    val name:String,
    val components:List<Pair<Int, String>>
)

fun parseFormulas(data:String):List<FormulaSpec> {
    val formulaSpecs = mutableListOf<FormulaSpec>(FormulaSpec(1,"ORE",listOf()))
    val formulaTexts = data.split("\n")
    formulaTexts.forEach{formulaText ->
        val head = formulaText.split(" => ")[1]
        val childFormulas = formulaText.split(" => ")[0].split(", ")
        val headQty = head.split(" ")[0].toInt()
        val headName = head.split(" ")[1]
        val components = mutableListOf<Pair<Int,String>>()
        childFormulas.forEach{childFormula ->
            val qty = childFormula.split(" ")[0].toInt()
            val name = childFormula.split(" ")[1]
            components.add( Pair(qty, name))
        }
        formulaSpecs.add(FormulaSpec(headQty, headName,components))
    }
    return formulaSpecs
}
fun createFormulas(formulaSpecs:List<FormulaSpec>, name:String):Formula {
    val headName = name
    val formulaSpec = formulaSpecs.find{it.name == name} ?: FormulaSpec(0,headName, listOf())
    val headQty = formulaSpec.qty
    val childFormulas = formulaSpec.components.map{(qty, name) ->   Pair(qty, createFormulas(formulaSpecs,name))} ?: listOf()
    return Formula(headQty, headName, childFormulas)
}