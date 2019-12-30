
inline class Card(val value:Int)

typealias SequenceNo = Int
typealias Deck = Map<SequenceNo, Card>
typealias MutableDeck = MutableMap<SequenceNo, Card>
typealias DeckShuffler = (Deck)->Deck

val dealIntoNewStack = fun(deck:Deck):Deck = deck.map{ (sequenceNo, Card) -> Pair(deck.size - 1 - sequenceNo, Card)}.toMap()

val cutNCards = fun(deck:Deck, n:Int):Deck {
    val newDeck:MutableDeck = mutableMapOf()

    deck.keys.forEach{ sequenceNo ->
        val newSequenceNo = sequenceNo - n
        if (newSequenceNo >= 0 && newSequenceNo < deck.size) {
            deck[sequenceNo]?.let { newDeck[newSequenceNo] = it }
        }
    }
    if (n >= 0) {
        (0 until n).forEach { sequenceNo ->
            val newSequenceNo = sequenceNo + deck.size - n
            deck[sequenceNo]?.let { newDeck[newSequenceNo] = it }
        }
    } else {
        ((deck.size + n) until deck.size).forEach { sequenceNo ->
            val newSequenceNo = sequenceNo - deck.size - n
            deck[sequenceNo]?.let { newDeck[newSequenceNo] = it }
        }
    }

    return newDeck
}

val dealWithIncrementN = fun(deck:Deck,n:Int):Deck {
    val newDeck:MutableDeck = mutableMapOf()
    deck.keys.sorted().forEachIndexed{ndx, sequenceNo ->
        val newSequenceNo = (ndx * n) % deck.size
        deck[sequenceNo]?.let{ newDeck[newSequenceNo] = it}
    }
    return newDeck
}
fun createDeck(deckRange:IntRange) = deckRange.map{Pair(it, Card(it))}.toMap()

fun List<String>.createDeckShufflers():List<DeckShuffler> = mapNotNull{
        instruction ->
        when (true) {
            instruction.isDealIntoNewStack() -> dealIntoNewStack
            instruction.isCut() -> {deck:Deck ->  cutNCards(deck,instruction.valueForN())}
            instruction.isDealWithIncrement() -> {deck:Deck ->  dealWithIncrementN(deck, instruction.valueForN())}
            else -> null
        }
    }

fun String.toListOfString() = split("\n")

fun dayOne(data:String, deck:Deck):Deck
        = data.toListOfString()
            .createDeckShufflers()
            .fold(deck){ latestDeck, deckShuffler -> deckShuffler(latestDeck) }


fun String.isDealIntoNewStack():Boolean  = contains(Regex("deal into new stack"))
fun String.isDealWithIncrement():Boolean  = contains(Regex("deal with increment"))
fun String.isCut():Boolean  = contains(Regex("cut"))
fun String.valueForN():Int = split(" ").last().toInt()