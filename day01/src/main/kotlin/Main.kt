
fun calculateFuel(mass: Int): Int = mass /3 - 2

fun calculateAllFuel(masses:List<Int>) = masses.fold(0){acc, mass -> acc + calculateFuel(mass) }
