typealias Orbiter = String
typealias Centre = String

var orbitRelationship = mutableMapOf<Orbiter,Centre>()

fun countOrbits(orbits:List<String>):Int {
    orbits.forEach{orbitString ->
        val centre = orbitString.split(")").first()
        val orbiter = orbitString.split(")").last()
        orbitRelationship[orbiter] = centre
    }
    return  orbitRelationship.map{  noOfDirectAndIndirectOrbits(orbitRelationship, it.key) }.sum()
}

tailrec fun noOfDirectAndIndirectOrbits(orbitRelationship:MutableMap<Orbiter,Centre>, orbiter:Orbiter, count:Int=1):Int {
    val centre = orbitRelationship[orbiter] ?: "COM"
    if (orbitRelationship[orbiter] == "COM") return count
    else return noOfDirectAndIndirectOrbits(orbitRelationship, centre, 1 + count)
}

fun calcStepsToSantasOrbit(orbits:List<String>):Int {
    orbits.forEach{orbitString ->
        val centre = orbitString.split(")").first()
        val orbiter = orbitString.split(")").last()
        orbitRelationship[orbiter] = centre
    }
    val centresForSanta = centresForAnOrbiter(orbitRelationship,"SAN")
    val centresForYou = centresForAnOrbiter(orbitRelationship,"YOU")
    println("centres for santa ${centresForSanta.toList()}")
    println("centres for you ${centresForYou.toList()}")
    val myCentreWhichMatchesSantas = centresForYou.mapNotNull{myCentre -> if (centresForSanta.contains(myCentre)) myCentre else null}.firstOrNull() ?: "COM"

    println("common centre $myCentreWhichMatchesSantas")
    val myStepsToCommonCentre = stepsToAnotherCentre(orbitRelationship,"YOU", myCentreWhichMatchesSantas)
    val santasStepsToCommonCentre = stepsToAnotherCentre(orbitRelationship,"SAN", myCentreWhichMatchesSantas)
    println("steps to common centre $myStepsToCommonCentre $santasStepsToCommonCentre = ${myStepsToCommonCentre + santasStepsToCommonCentre}" )
    return myStepsToCommonCentre + santasStepsToCommonCentre
}
fun centresForAnOrbiter(orbitRelationship: MutableMap<Orbiter, Centre>, orbiter:Orbiter, centres:Sequence<Centre> = sequenceOf() ):Sequence<Centre> {
    val centre = orbitRelationship[orbiter] ?: "COM"
    if (centre == "COM") return centres
    else return centresForAnOrbiter(orbitRelationship, centre, centres + centre)
}
fun stepsToAnotherCentre(orbitRelationship: MutableMap<Orbiter, Centre>,orbiter:Orbiter, otherCentre:Centre, count:Int = 0):Int {
    val centre = orbitRelationship[orbiter] ?: "COM"
    if (centre == "COM") {
        println("never reached $otherCentre")
        return count
    }
    if (centre == otherCentre) return count
    else return stepsToAnotherCentre(orbitRelationship, centre, otherCentre, 1 + count)
}
