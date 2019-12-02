
fun calculateFuel(mass: Int): Int = mass /3 - 2

fun calculateAllFuel(masses:List<Int>) = masses.fold(0){acc, mass -> acc + calculateFuel(mass) }

fun calculateFuelplusFuel(mass:Int):Int {
    if (calculateFuel(mass) > 0) return calculateFuel(mass) + calculateFuelplusFuel(calculateFuel(mass))
    else return 0
}

fun calculateAllFuelPlusFuel(masses:List<Int>) = masses.fold(0){acc, mass -> acc + calculateFuelplusFuel(mass) }
