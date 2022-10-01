package day02

import diff
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `name here`() {
        val actual = part1(
            listOf(
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab",
            )
        )
        actual shouldBe 12
    }

    @Test
    fun `string diff`() {
        "aaabbb".diff("aaabba") shouldBe 1
        "aaabbb".diff("aaabca") shouldBe 2
    }

    @Test
    fun `common chars`() {
        listOf("12345", "12365").commonChars() shouldBe "1235"
    }
}