package day01

import readInput

fun part1(input: List<String>): Int {
    return input.sumOf(String::toInt)
}

fun part2(input: List<String>): Int {
    val sums = mutableListOf(0)
    var index = 0
    while (true) {
        val next = sums.last() + input[index % input.size].toInt()
        if (sums.contains(next)) {
            println("Index: $index")
            return next
        } else {
            sums.add(next)
        }
        index++
    }
}

fun main() {


    val input = readInput("main/day01/Day01")
    println(part1(input))
    println(part2(input))
}
