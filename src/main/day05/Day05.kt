package day05

import readInput
fun part1(input: List<String>): Int {
    return input.first().process().length
}

fun part2(input: List<String>): Int {

    return ('a'..'z').map { c ->
        c to input.first().filterNot {
            c.lowercase() == it.lowercase()
        }.process().apply { println("'$c' done: $length") }
    }.minOf { it.second.length }
}

fun main() {
    val input = readInput("main/day05/Day05")
    println(part1(input))
    println(part2(input))
}

fun String.process(): String {
    return generateSequence(this to false) { it.first.processOnce() }.first { it.second }.first
}

fun String.processOnce(): Pair<String, Boolean> {

    val result = this.fold(mutableListOf<Char>()) { processed, c ->
        if (processed.lastOrNull() matches c) {
            processed.removeLast()
        } else {
            processed.add(c)
        }
        processed
    }.joinToString("")
    return result to (result == this)
}

infix fun Char?.matches(other: Char): Boolean = this != null && this.isLowerCase() != other.isLowerCase() && this.lowercase() == other.lowercase()