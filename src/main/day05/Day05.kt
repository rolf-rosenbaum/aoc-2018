package day05

import readInput

fun part1(input: List<String>): Int {
    return input.first().process().length
}

fun part2(input: List<String>): Int {

    val foo = ('a'..'z').map { c ->
        c to input.first().filterNot {
            c.lowercase() == it.lowercase()
        }.process().apply { println("$c done") }
    }
    val minOfOrNull = foo.minOfOrNull { it.second.length }
    return minOfOrNull!!

}

fun main() {
    val input = readInput("main/day05/Day05")
//    println(part1(input))
    println(part2(input))
}

fun String.process(): String {
    return generateSequence(this to false) { it.first.processOnce() }.first { it.second }.first
}

fun String.processOnce(): Pair<String, Boolean> {
    var index = 0
    var result = ""
    while (index < length) {
        if (index == this.length - 1) {
            result += this.last()
            index++
        } else if (this[index].isLowerCase() == this[index + 1].isLowerCase()) {
            result += this[index]
            index++
        } else if (this[index].lowercase() != this[index + 1].lowercase()) {
            result += this[index]
            index++
        } else index += 2
    }
    return result to (this == result)

}
