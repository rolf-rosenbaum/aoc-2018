package day05

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `aA just vanishes`() {
        "aA".process() shouldBe ""
    }
    
    @Test
    fun `abc just stays`() {
        "abc".process() shouldBe "abc"
    }
    
    @Test
    fun `aAA becomes A`() {
        "aAA".process() shouldBe "A"
    }
    
    @Test
    fun `baAA becomes bA`() {
        "baAA".process() shouldBe "bA"
    }
    
    @Test
    fun `AbBaAcCCcb becomes Ab`() {
        "AbBaADcCCcb".process() shouldBe "ADb"
    }

    @Test
    fun `caAC becomes ""`() {
        "caAC".process() shouldBe ""
    }
    
    
}