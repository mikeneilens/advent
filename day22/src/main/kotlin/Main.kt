
inline class Card(val value:Int)

typealias SequenceNo = Int
typealias Deck = Map<SequenceNo, Card>
typealias MutableDeck = MutableMap<SequenceNo, Card>

val reverse = fun(deck:Deck):Deck = deck.map{(sequenceNo, Card) -> Pair(deck.size - 1 - sequenceNo, Card)}.toMap()

val cutNCards = fun(deck:Deck, n:Int):Deck {
    val newDeck:MutableDeck = mutableMapOf()
    if (n >= 0) {
        deck.keys.forEach{ sequenceNo ->
            val newSequence = sequenceNo - n
            if (newSequence >= 0) {
                newDeck[newSequence] = deck[sequenceNo] ?: Card(-1)
            }
        }
        (0..(n - 1)).forEach { sequenceNo ->
            val newSequence = deck.size - n + sequenceNo
            newDeck[newSequence] = deck[sequenceNo] ?: Card(-1)
        }
    } else {
        deck.keys.forEach{ sequenceNo ->
            val newSequence = sequenceNo - n
            if (newSequence < deck.size) {
                newDeck[newSequence] = deck[sequenceNo] ?: Card(-1)
            }
        }
        ((deck.size + n)..(deck.size - 1)).forEach { sequenceNo ->
            val newSequence = sequenceNo - deck.size - n
            newDeck[newSequence] = deck[sequenceNo] ?: Card(-1)
        }
    }
    return newDeck
}

val dealWithIncrementN = fun(deck:Deck,n:Int):Deck {
    val newDeck:MutableDeck = mutableMapOf()
    var newSequence = 0
    deck.keys.sorted().forEach{sequenceNo ->
        newDeck[newSequence] = deck[sequenceNo] ?: Card(-1)
        newSequence += n
        newSequence %= deck.size
    }
    return newDeck
}
fun createDeck(deckRange:IntRange) = deckRange.map{Pair(it, Card(it))}.toMap()

fun x(n:Int) {
    val dealWithIncrement = fun(deck:Deck):Deck {
        return dealWithIncrementN(deck,n)
    }
}

fun List<String>.createListOfFunctions() = mapNotNull{
        instruction ->
        when (true) {
            instruction.isDealIntoNewStack() -> reverse
            instruction.isCut() -> fun(deck:Deck):Deck = cutNCards(deck,instruction.lastValue())
            instruction.isDealWithIncrement() -> fun(deck: Deck): Deck =  dealWithIncrementN(deck, instruction.lastValue())
            else -> null
        }
    }

fun String.toListOfString() = split("\n")

fun dayOne(data:String, deck:Deck):Deck {
    val instrictionsText = data.toListOfString()
    val functions = instrictionsText.createListOfFunctions()
    return  functions.fold(deck){ newDeck, function ->
        function(newDeck)
    }
}

fun String.isDealIntoNewStack():Boolean  = contains(Regex("deal into new stack"))
fun String.isDealWithIncrement():Boolean  = contains(Regex("deal with increment"))
fun String.isCut():Boolean  = contains(Regex("cut"))
fun String.lastValue():Int = split(" ").last().toInt()