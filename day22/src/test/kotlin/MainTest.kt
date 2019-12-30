import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun `reversing a deck`() {
        val deck = createDeck(0..9)
        val reversedDeck = dealIntoNewStack(deck)
        assertEquals(Card(9), reversedDeck[0])
        assertEquals(Card(8), reversedDeck[1])
        assertEquals(Card(0), reversedDeck[9])
    }
    @Test
    fun `cut positive number`() {
        val deck = createDeck(0..9)
        val cutDeck = cutNCards(deck,3)

        val correctDeck = mapOf(0 to Card(3),1 to Card(4),2 to Card(5),3 to Card(6), 4 to Card(7), 5 to Card(8), 6 to Card(9), 7 to Card(0), 8 to Card(1), 9 to Card(2))

        assertEquals(0, cutDeck.keys.min())
        assertEquals(9, cutDeck.keys.max())
        assertEquals(10, cutDeck.keys.size)
        assertEquals(correctDeck[0], cutDeck[0])
        assertEquals(correctDeck[1], cutDeck[1])
        assertEquals(correctDeck[2], cutDeck[2])
        assertEquals(correctDeck[3], cutDeck[3])
        assertEquals(correctDeck[4], cutDeck[4])
        assertEquals(correctDeck[5], cutDeck[5])
        assertEquals(correctDeck[6], cutDeck[6])
        assertEquals(correctDeck[7], cutDeck[7])
        assertEquals(correctDeck[8], cutDeck[8])
        assertEquals(correctDeck[9], cutDeck[9])
    }
    @Test
    fun `cut negative number`() {
        val deck = createDeck(0..9)
        val cutDeck = cutNCards(deck, -4)

        val correctDeck = mapOf(0 to Card(6),1 to Card(7),2 to Card(8),3 to Card(9), 4 to Card(0)
            , 5 to Card(1), 6 to Card(2), 7 to Card(3), 8 to Card(4), 9 to Card(5))

        assertEquals(0, cutDeck.keys.min())
        assertEquals(9, cutDeck.keys.max())
        assertEquals(10, cutDeck.keys.size)
        assertEquals(correctDeck[0], cutDeck[0])
        assertEquals(correctDeck[1], cutDeck[1])
        assertEquals(correctDeck[2], cutDeck[2])
        assertEquals(correctDeck[3], cutDeck[3])
        assertEquals(correctDeck[4], cutDeck[4])
        assertEquals(correctDeck[5], cutDeck[5])
        assertEquals(correctDeck[6], cutDeck[6])
        assertEquals(correctDeck[7], cutDeck[7])
        assertEquals(correctDeck[8], cutDeck[8])
        assertEquals(correctDeck[9], cutDeck[9])
    }
    @Test
    fun `Deal with increment`() {
        val deck = createDeck(0..9)
        val newDeck = dealWithIncrementN(deck,3)
        val correctDeck = mapOf(0 to Card(0),1 to Card(7),2 to Card(4),3 to Card(1), 4 to Card(8)
            , 5 to Card(5), 6 to Card(2), 7 to Card(9), 8 to Card(6), 9 to Card(3))

        assertEquals(0, newDeck.keys.min())
        assertEquals(9, newDeck.keys.max())
        assertEquals(10, newDeck.keys.size)
        assertEquals(correctDeck[0], newDeck[0])
        assertEquals(correctDeck[1], newDeck[1])
        assertEquals(correctDeck[2], newDeck[2])
        assertEquals(correctDeck[3], newDeck[3])
        assertEquals(correctDeck[4], newDeck[4])
        assertEquals(correctDeck[5], newDeck[5])
        assertEquals(correctDeck[6], newDeck[6])
        assertEquals(correctDeck[7], newDeck[7])
        assertEquals(correctDeck[8], newDeck[8])
        assertEquals(correctDeck[9], newDeck[9])
    }
    @Test
    fun `isDealIntoNewStack is true if string contains correct text`() {
        assertTrue("deal into new stack".isDealIntoNewStack())
        assertFalse("dea into new stack".isDealIntoNewStack())
    }
    @Test
    fun `isDealWithIncrement is true if string contains correct text`() {
        assertTrue("deal with increment 5".isDealWithIncrement())
        assertFalse("deal into new stack".isDealWithIncrement())
    }
    @Test
    fun `isCut is true if string contains correct text`() {
        assertTrue("cut 6".isCut())
        assertFalse("deal into new stack".isCut())
    }
    @Test
    fun `lastValue return last value embedded in a string`() {
        assertEquals(6, "deal with increment 6".valueForN())
        assertEquals(-4, "cut -4".valueForN())
    }
    @Test
    fun `shuffle using sample 1 of shuffle instructions`() {
        val data = "deal with increment 7\n" +
                "deal into new stack\n" +
                "deal into new stack\n"

        val deck = createDeck(0..9)
        val newDeck = dayOne(data, deck)
        val correctDeck = mapOf(0 to Card(0),1 to Card(3),2 to Card(6),3 to Card(9), 4 to Card(2)
            , 5 to Card(5), 6 to Card(8), 7 to Card(1), 8 to Card(4), 9 to Card(7))

        assertEquals(0, newDeck.keys.min())
        assertEquals(9, newDeck.keys.max())
        assertEquals(10, newDeck.keys.size)
        assertEquals(correctDeck, newDeck)
    }

    @Test
    fun `shuffle using sample 2 of shuffle instructions`() {
        val data = "cut 6\n" +
                "deal with increment 7\n" +
                "deal into new stack"

        val deck = createDeck(0..9)
        val newDeck = dayOne(data, deck)
        val correctDeck = mapOf(0 to Card(3),1 to Card(0),2 to Card(7),3 to Card(4), 4 to Card(1)
            , 5 to Card(8), 6 to Card(5), 7 to Card(2), 8 to Card(9), 9 to Card(6))

        assertEquals(0, newDeck.keys.min())
        assertEquals(9, newDeck.keys.max())
        assertEquals(10, newDeck.keys.size)
        assertEquals(correctDeck, newDeck)
    }

    @Test
    fun `shuffle using sample 3 of shuffle instructions`() {
        val data = "deal with increment 7\n" +
                "deal with increment 9\n" +
                "cut -2"

        val deck = createDeck(0..9)
        val newDeck = dayOne(data, deck)
        val correctDeck = mapOf(0 to Card(6),1 to Card(3),2 to Card(0),3 to Card(7), 4 to Card(4)
            , 5 to Card(1), 6 to Card(8), 7 to Card(5), 8 to Card(2), 9 to Card(9))

        assertEquals(0, newDeck.keys.min())
        assertEquals(9, newDeck.keys.max())
        assertEquals(10, newDeck.keys.size)
        assertEquals(correctDeck, newDeck)
    }

    @Test
    fun `shuffle using sample 4 of shuffle instructions`() {
        val data = "deal into new stack\n" +
                "cut -2\n" +
                "deal with increment 7\n" +
                "cut 8\n" +
                "cut -4\n" +
                "deal with increment 7\n" +
                "cut 3\n" +
                "deal with increment 9\n" +
                "deal with increment 3\n" +
                "cut -1"

        val deck = createDeck(0..9)
        val newDeck = dayOne(data, deck)
        val correctDeck = mapOf(0 to Card(9),1 to Card(2),2 to Card(5),3 to Card(8), 4 to Card(1)
            , 5 to Card(4), 6 to Card(7), 7 to Card(0), 8 to Card(3), 9 to Card(6))

        assertEquals(0, newDeck.keys.min())
        assertEquals(9, newDeck.keys.max())
        assertEquals(10, newDeck.keys.size)
        assertEquals(correctDeck, newDeck)
    }

    @Test
    fun `day 20 part one`() {
        val deck = createDeck(0..10006)
        val newDeck = dayOne(data, deck)

        val sequenceNoForCard2019 =  newDeck.toList().filter{(sequenceNo, card)->  card == Card(2019)}.first().first
        assertEquals(3589, sequenceNoForCard2019)
    }
}