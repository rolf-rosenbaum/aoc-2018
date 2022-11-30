package day11

import kotlin.math.min

const val serial = 5177
const val gridSize = 300


val gridMap = (1..gridSize).flatMap { y ->
    (1..gridSize).map { x ->
        Point(x, y).let {
            it to it.power()
        }
    }
}.associate { it.first to it.second }

fun part1(): Point {

    return gridMap.maxByOrNull { (point, _) ->
        regionPower(point, 3).first
    }?.key ?: error("No max found")
}

fun part2(): String {

    var maxX = 0
    var maxY = 0
    var maxSize = 3
    var maxSum = 0

    for (x in 0..gridSize) {
        for (y in 0..gridSize) {
            var sum = 0
            for (s in 1 until gridSize - maxOf(x, y)) {
                sum += (0 until s).sumOf { dx -> gridMap[Point(x + dx, y + s - 1)]!! }
                sum += (0 until s - 1).sumOf { dy -> gridMap[Point(x + s - 1, y + dy)]!! }
                if (sum > maxSum) {
                    maxSum = sum
                    maxX = x + 1
                    maxY = y + 1
                    maxSize = s
                }
            }
        }
    }

    val answer2 = "$maxX,$maxY,$maxSize"
    return answer2
}

private fun regionPower(point: Point, size: Int) =
    (point.x until min(point.x + size, gridSize)).asSequence().flatMap { x ->
        (point.y until min(point.y + size, gridSize)).map { y ->
            if (x <= gridSize && y <= gridSize)
                gridMap[Point(x, y)]!!
            else 0
        }
    }.sum() to size

fun main() {
    println(part1())
    println(part2())

}

data class Point(val x: Int, val y: Int) {
    fun power(): Int = (((y * (x + 10) + serial) * (x + 10)) / 100) % 10
}
