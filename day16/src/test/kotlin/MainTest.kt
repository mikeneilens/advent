import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainTest {
    val basePattern = listOf(0, 1, 0, -1)

    @Test
    fun `So if the third element of the output list is being calculated repeating the values would produce 0, 0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1`() {
        val repeatedPattern = basePattern.repeatPattern(3,11)
        assertEquals(listOf(0, 0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1), repeatedPattern)
    }
    @Test
    fun `first digit with 12345678 gives 12345678 1*1  + 2*0  + 3*-1 + 4*0  + 5*1  + 6*0  + 7*-1 + 8*0  = 4`() {
        val inputSignal = "12345678".toList().map{it.toString().toInt()}
        val patterns = patterns(basePattern, inputSignal.size)
        val result = processSignal(patterns, inputSignal,1)
        assertEquals(4, result)
    }
    @Test
    fun `putting 12345678 through a complete phase gives 48226158`() {
        val inputSignal = "12345678".toList().map{it.toString().toInt()}
        val patterns = patterns(basePattern, inputSignal.size)
        val result = processPhase(patterns,inputSignal)
        assertEquals(listOf(4,8,2,2,6,1,5,8), result)
    }

    @Test
    fun `putting 12345678 through 2 phases gives 34040438`() {
        val inputSignal = "12345678".toList().map{it.toString().toInt()}
        val result = processPhases(basePattern,inputSignal,2)
        assertEquals(listOf(3,4,0,4,0,4,3,8), result)
    }
    @Test
    fun `putting 80871224585914546619083218645595 through 100 phases gives first eight digits of 24176176`() {
        val inputSignal = "80871224585914546619083218645595".toList().map{it.toString().toInt()}
        val result = processPhases(basePattern,inputSignal,100)
        assertEquals(listOf(2,4,1,7,6,1,7,6), result.take(8))
    }
    @Test
    fun `putting 19617804207202209144916044189917 through 100 phases gives first eight digits of 73745418`() {
        val inputSignal = "19617804207202209144916044189917".toList().map{it.toString().toInt()}
        val result = processPhases(basePattern,inputSignal,100)
        assertEquals(listOf(7,3,7,4,5,4,1,8), result.take(8))
    }
    @Test
    fun `putting 69317163492948606335995924319873 through 100 phases gives first eight digits of 52432133`() {
        val inputSignal = "69317163492948606335995924319873".toList().map{it.toString().toInt()}
        val result = processPhases(basePattern,inputSignal,100)
        assertEquals(52432133, result.take(8).toInt())
    }
    @Test
    fun `Day 16 part one`() {
        val inputSignal = "59717513948900379305109702352254961099291386881456676203556183151524797037683068791860532352118123252250974130706958763348105389034831381607519427872819735052750376719383812473081415096360867340158428371353702640632449827967163188043812193288449328058464005995046093112575926165337330100634707115160053682715014464686531460025493602539343245166620098362467196933484413717749680188294435582266877493265037758875197256932099061961217414581388227153472347319505899534413848174322474743198535953826086266146686256066319093589456135923631361106367290236939056758783671975582829257390514211329195992209734175732361974503874578275698611819911236908050184158".toList().map{it.toString().toInt()}
        val result = processPhases(basePattern,inputSignal,100)
        assertEquals(63794407, result.take(8).toInt())
    }
    @Test
    fun `Day 16 part two`() {
        val inputSignal = "59717513948900379305109702352254961099291386881456676203556183151524797037683068791860532352118123252250974130706958763348105389034831381607519427872819735052750376719383812473081415096360867340158428371353702640632449827967163188043812193288449328058464005995046093112575926165337330100634707115160053682715014464686531460025493602539343245166620098362467196933484413717749680188294435582266877493265037758875197256932099061961217414581388227153472347319505899534413848174322474743198535953826086266146686256066319093589456135923631361106367290236939056758783671975582829257390514211329195992209734175732361974503874578275698611819911236908050184158".toList().map{it.toString().toInt()}
        val bigInputsignal = (1..10000).flatMap { inputSignal }
        val result = processPhases(basePattern,bigInputsignal,1)
        assertEquals(63794407, result.take(8).toInt())
    }
}