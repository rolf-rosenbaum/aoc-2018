package day07

import readInput

typealias Steps = MutableMap<String, MutableList<String>>

fun part1(input: List<String>): String {

    val steps = parseSteps(input)

    val done = mutableListOf<String>()
    while (true) {
        val nextStep = steps.filter { (_, pres) ->
            pres.isEmpty()
        }.keys.minOfOrNull { it } ?: return done.joinToString("")

        steps.execute(nextStep)
        done.add(nextStep)
    }
}

private fun parseSteps(input: List<String>): MutableMap<String, MutableList<String>> {
    val steps = mutableMapOf<String, MutableList<String>>()
    input.forEach {
        val foo = it.split(" ").drop(1)
        val pre = foo.first()
        val step = foo.dropLast(2).last()

        if (steps[step] == null) {
            steps[step] = mutableListOf(pre)
        } else {
            steps[step]!!.add(pre)
        }
        if (steps[pre] == null) {
            steps[pre] = mutableListOf()
        }
    }
    return steps
}

fun Steps.execute(step: String): Int {
    forEach { (_, pres) ->
        pres.remove(step)
    }
    if (this[step]?.isEmpty() != false) this.remove(step)
    return step.first().executionTime()
}

fun part2(input: List<String>): Int {
    return 0
}

fun Char.executionTime() = 60 + ('A' - this + 1)

fun main() {
    val input = readInput("main/day07/Day07")
    println(part1(input))
    println(part2(input))
}

