package day01

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day01Test {

    @Test
    fun `part1 can add +1 and +2`() {
        _2022_day01.part1(listOf("+1", "+2")) shouldBe 3
    }
    
    @Test
    fun `part1 can add -1 and +2`() {
        _2022_day01.part1(listOf("-1", "+2")) shouldBe 1
    }
    
    @Test
    fun `part2 can add -1 and +2`() {
        _2022_day01.part2(listOf("+3", "+3", "+4", "-2", "-4")) shouldBe 10
    }
    
    
}