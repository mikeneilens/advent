import kotlin.math.ceil //rounds a double up to the nearest integer

inline class IngredientName(val value:String)

open class Ingredient(val name:IngredientName, val amount:Long)

class Element(name:IngredientName, amount:Long, val ingredients:List<Ingredient>):Ingredient(name, amount  )

fun String.toIngredient() = Ingredient( IngredientName(  split(" ").last()),  split(" ").first().toLong())

fun createElements(data: String): Map<IngredientName, Element> {
    val inputData = data.split("\n")
    val elementMap = mutableMapOf<IngredientName, Element>()

    inputData. forEach { dataRow ->
        val produced = dataRow.split(" => ").last().toIngredient()
        val ingredients = dataRow.split(" => ").first().split(", ").map{it.toIngredient()}
        elementMap[produced.name] =  Element(produced.name, produced.amount, ingredients)
    }
    return elementMap
}

class FuelCalculator(private val elementMap:Map<IngredientName, Element>) {

    private val elementBank: MutableMap<IngredientName, Long> = mutableMapOf()

    fun calcOreRequired(elementName: IngredientName, elementAmount: Long): Long {

        var oreRequired = 0L

        if (elementName.value == "ORE") {
            oreRequired = elementAmount
        } else {
            val currentElement: Element = elementMap[elementName] ?: Element(IngredientName(""),0, listOf())
            val ingredients: List<Ingredient> = currentElement.ingredients

            ingredients.forEach { ingredient ->

                val surplusForIngredient =  elementBank[ingredient.name] ?: 0

                var amountOfIngredientRequired = ingredient.amount * elementAmount

                if (amountOfIngredientRequired > surplusForIngredient) {
                    elementBank[ingredient.name] = 0
                    amountOfIngredientRequired -= surplusForIngredient
                } else {
                    elementBank[ingredient.name] = surplusForIngredient - amountOfIngredientRequired
                    amountOfIngredientRequired = 0
                }

                if (amountOfIngredientRequired > 0L) {
                    val elementForIngredient = elementMap[ingredient.name] ?: Element(IngredientName("ORE"),0, listOf())

                    if (elementForIngredient.name.value != "ORE") {
                        val amountToRequest = ceil((1.0 * amountOfIngredientRequired / elementForIngredient.amount)).toLong()
                        val surplusAmount = amountToRequest * elementForIngredient.amount - amountOfIngredientRequired
                        elementBank[ingredient.name] = (elementBank[ingredient.name] ?: 0) + surplusAmount
                        oreRequired += calcOreRequired(ingredient.name, amountToRequest)
                    } else {
                        oreRequired += calcOreRequired(ingredient.name, amountOfIngredientRequired)
                    }

                }
            }
        }
        return oreRequired
    }
}
