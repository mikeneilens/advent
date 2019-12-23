import kotlin.math.absoluteValue
import kotlin.math.floor

fun String.toIngredient() = Ingredient(split(" ").last(),split(" ").first().toLong())

fun createDataSet(data: String): FuelCalculator {
    val inputData = data.split("\n")
    val elementMap = mutableMapOf<String, Element>()

    inputData. forEach {
        val produced = it.split(" => ").last().toIngredient()
        val ingredients = it.split(" => ").first().split(", ").map{it.toIngredient()}
        elementMap[produced.name] =  Element(produced.name, produced.amount, ingredients)
    }
    return FuelCalculator(elementMap)
}

class FuelCalculator(val elementMap:Map<String, Element>) {

    val surplus: MutableMap<String, Long> = mutableMapOf()

    fun calculateOreToMakeNumberOfElement(name: String, amount: Long): Long {

        var oreNeeded = 0L

        if (name == "ORE") {
            oreNeeded = amount
        } else {
            val currentElement: Element = elementMap[name]!!
            val createdByList: List<Ingredient> = currentElement.createdFrom

            createdByList.forEach {ingredient ->

                val currentSurplus =  surplus[ingredient.name] ?: 0

                var amountOfThisNeeded = (ingredient.amount * amount) - currentSurplus
                if (amountOfThisNeeded <= 0) {
                    surplus[ingredient.name] = amountOfThisNeeded.absoluteValue
                    amountOfThisNeeded = 0
                } else
                    surplus[ingredient.name] = 0

                val nextElement = elementMap[ingredient.name]
                val nextAmount: Long
                val nextSurplus: Long

                if (nextElement != null) {

                    if ((amountOfThisNeeded).rem(nextElement.amount) > 0) {
                        nextAmount = floor(((amountOfThisNeeded) / nextElement.amount).toDouble()).toLong() + 1
                        nextSurplus = nextAmount * nextElement.amount - amountOfThisNeeded
                        surplus[ingredient.name] = (surplus[ingredient.name] ?: 0) + nextSurplus
                    } else {
                        nextAmount = amountOfThisNeeded / nextElement.amount
                        nextSurplus = nextAmount * nextElement.amount - amountOfThisNeeded
                        surplus[ingredient.name] = (surplus[ingredient.name] ?: 0) + nextSurplus
                    }
                } else {
                    // then next recursive call will be for an ORE.
                    nextAmount = amountOfThisNeeded
                }

                if (amountOfThisNeeded == 0L) {
                    oreNeeded += calculateOreToMakeNumberOfElement(ingredient.name, 0)
                } else {
                    oreNeeded += calculateOreToMakeNumberOfElement(ingredient.name, nextAmount)
                }
            }
        }
        return oreNeeded
    }

    fun totalFuelFromOre(totalOre: Long): Long {
        var totalFuel = 0
        var currentOreUsage: Long = 0

        while (currentOreUsage <= totalOre) {
            currentOreUsage += calculateOreToMakeNumberOfElement("FUEL", 1)
            totalFuel++
            println(currentOreUsage)
        }
        return totalFuel - 1L
    }



}

open class Ingredient(val name:String, val amount:Long)

class Element(name:String, amount:Long, val createdFrom:List<Ingredient>):Ingredient(name, amount  )
