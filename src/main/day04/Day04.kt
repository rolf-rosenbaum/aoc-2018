package day04

import readInput
import second
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun part1(input: List<String>): Int {

    val guards = input.guards()

    val mostAsleepGuard = guards.maxByOrNull {
        it.sleepIntervals.sumOf { range -> range.second - range.first }
    }!!

    val sleepiestMinute = (0..59).maxByOrNull { minute ->
        mostAsleepGuard.sleepIntervals.map { it.toIntRange() }.count { minute in it }
    }!!

    return mostAsleepGuard.id.toInt() * sleepiestMinute
}

fun part2(input: List<String>): Int {
    val guards = input.guards()

    val sleepiestMinuteList = guards.flatMap { guard ->
        (0..59).map { minute ->
            guard.id to guard.sleepIntervals.count { minute in it.toIntRange() }
        }
    }
    val foo = sleepiestMinuteList.maxByOrNull { it.second }!!
    val minute = sleepiestMinuteList.indexOf(foo) % 60

    return foo.first.toInt() * minute
}

private fun List<String>.guards(): MutableList<Guard> {

    val guards: MutableList<Guard> = mutableListOf()

    var currentId = ""
    var sleepStart = -1
    map {
        it.toTimedInput()
    }.sortedBy { it.time }.forEach { line ->
        if (line.action.contains("Guard")) {
            val pattern = """ #(\d+) """.toRegex()
            pattern.find(line.action)?.let {
                currentId = it.value.substring(2).trim()
            }
        } else if (line.action.contains("asleep")) {
            sleepStart = line.time.minute

        } else if (line.action.contains("wakes")) {
            val guard = guards.find { it.id == currentId }
            if (guard == null) {
                guards.add(Guard(currentId).addSleepInterval(sleepStart to line.time.minute))
            } else {
                guards.add(guard.addSleepInterval(sleepStart to line.time.minute))
                guards.remove(guard)
            }
        }
    }
    return guards
}

fun main() {
    val input = readInput("main/day04/Day04")
    println(part1(input))
    println(part2(input))
}

data class TimedInput(
    val time: LocalDateTime,
    val action: String
)

data class Guard(
    val id: String,
    val sleepIntervals: List<Pair<Int, Int>> = emptyList()
) {
    fun addSleepInterval(interval: Pair<Int, Int>): Guard {
        return copy(sleepIntervals = this.sleepIntervals + interval)
    }
}

fun Pair<Int, Int>.toIntRange() = first until second

fun String.toTimedInput(): TimedInput {
    val split = split("] ")
    return TimedInput(
        time = LocalDateTime.parse(split.first().substring(1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        action = split.second()
    )
}