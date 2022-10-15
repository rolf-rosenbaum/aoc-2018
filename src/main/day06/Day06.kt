package day06

import readInput
import second
import kotlin.math.abs

fun part1(input: List<String>): Int {
    val points = input.mapIndexed { index, line ->
        line.toPoint(index.toString())
    }

    val map = mutableSetOf<Point>()
    (points.minOf { it.x }..points.maxOf { it.x }).forEach { x ->
        (points.minOf { it.y }..points.maxOf { it.y }).forEach { y ->
            val p = Point(x, y)
            val sortedBy = points.sortedBy { p.distanceTo(it) }
            val point = sortedBy.first()
            val areaId = if (point.distanceTo(p) == sortedBy.second().distanceTo(p)) "." else point.areaId
            map.add(Point(x, y, areaId))
        }
    }
    return map.filter{it.areaId != "."}.groupingBy { it.areaId }.eachCount().maxByOrNull { it.value }?.value ?: -1
}

fun part2(input: List<String>): Int {
    val points = input.mapIndexed { index, line ->
        line.toPoint(index.toString())
    }
    val map = mutableSetOf<Point>()
    (points.minOf { it.x }..points.maxOf { it.x }).forEach { x ->
        (points.minOf { it.y }..points.maxOf { it.y }).forEach { y ->
            val p = Point(x, y)
            if (points.totalDistanceTo(p) < 10000)
                map.add(Point(x, y))
        }
    }
    return map.size
}

fun main() {
    val input = readInput("main/day06/Day06")
    println(part1(input))
    println(part2(input))
}

data class Point(val x: Int, val y: Int, val areaId: String = "-1") {
    fun distanceTo(other: Point): Int = abs(x - other.x) + abs(y - other.y)
}

fun List<Point>.totalDistanceTo(p: Point): Int = sumOf { it.distanceTo(p) }

fun String.toPoint(areaId: String): Point {
    val (x, y) = split(", ")
    return Point(x.toInt(), y.toInt(), areaId)
}