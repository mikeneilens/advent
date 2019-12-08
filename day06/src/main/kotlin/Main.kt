typealias Orbiter = String
typealias Centre = String

var orbitRelationship = mutableMapOf<Orbiter,Centre>()

fun countOrbits(orbits:List<String>):Int {
    val orbitRelationship = mutableMapOf<Orbiter,Centre>()
    orbits.forEach{orbitString ->
        val centre = orbitString.split(")").first()
        val orbiter = orbitString.split(")").last()
        orbitRelationship[orbiter] = centre
    }
    return  orbitRelationship.map{  noOfDirectAndIndirectOrbits(orbitRelationship, it.key) }.sum()
}

fun noOfDirectAndIndirectOrbits(orbitRelationship:MutableMap<Orbiter,Centre>, orbiter:Orbiter, count:Int=1):Int {
    return stepsToAnotherCentre(orbitRelationship,orbiter,"COM",count)
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
tailrec fun centresForAnOrbiter(orbitRelationship: MutableMap<Orbiter, Centre>, orbiter:Orbiter, centres:Sequence<Centre> = sequenceOf() ):Sequence<Centre> {
    val centre = orbitRelationship[orbiter] ?: "COM"
    return if (centre == "COM") centres
    else centresForAnOrbiter(orbitRelationship, centre, centres + centre)
}
tailrec fun stepsToAnotherCentre(orbitRelationship: MutableMap<Orbiter, Centre>,orbiter:Orbiter, otherCentre:Centre, count:Int = 0):Int {
    val centre = orbitRelationship[orbiter] ?: "COM"
    if (centre == "COM") return count

    return if (centre == otherCentre) count
    else stepsToAnotherCentre(orbitRelationship, centre, otherCentre, 1 + count)
}
