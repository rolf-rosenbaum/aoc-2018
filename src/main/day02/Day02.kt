package day02

import diff
import readInput
import second

fun part1(input: List<String>): Int {
    val pairs = input.map { it.findPairsAndTriples() }

    return pairs.count { it.first } * pairs.count { it.second }
}

fun part2(input: List<String>): String {

    input.forEach { line ->
        val similar = input.filter { it.diff(line) == 1 }
        if (similar.size == 1) {
            return (similar + line).commonChars()
        }
    }
    error("no similar ids found")
}

fun main() {

    val input = readInput("main/day02/Day02")
    println(part1(input))
    println(part2(input))
}

fun String.findPairsAndTriples(): Pair<Boolean, Boolean> {
    val byChar = this.groupingBy { it }.eachCount()
    return byChar.any { it.value == 2 } to byChar.any { it.value == 3 }
}

fun List<String>.commonChars(): String {
    return first().mapIndexedNotNull { index, c ->
        if (second()[index] == c) c else null
    }.joinToString("")
}
