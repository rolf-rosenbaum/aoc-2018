package day03

import readInput
import second

fun part1(input: List<String>): Int {
    val points = addClaims(input)
    return points.count { it.value.size > 1 }
}

private fun addClaims(input: List<String>): MutableMap<Point, MutableSet<Claim>> {
    val points = mutableMapOf<Point, MutableSet<Claim>>()

    input.forEach { line ->
        val claim = extracted(line)
        (claim.startPoint.first until (claim.startPoint.first + claim.width)).forEach { x ->
            (claim.startPoint.second until (claim.startPoint.second + claim.height)).forEach { y ->
                if (points.containsKey(x to y)) {
                    points[x to y]!!.add(claim)
                } else {
                    points[x to y] = mutableSetOf(claim)
                }
            }
        }
    }
    return points
}

fun part2(input: List<String>): Int {
    val claims: MutableMap<Claim, MutableList<Point>> = mutableMapOf()
    
    input.forEach { line ->
        val claim = extracted(line)
        

        (claim.startPoint.first until (claim.startPoint.first + claim.width)).forEach { x ->
            (claim.startPoint.second until (claim.startPoint.second + claim.height)).forEach { y ->
                if (claims.containsKey(claim)) {
                    claims[claim]!!.add(x to y)
                } else {
                    claims[claim] = mutableListOf(x to y)
                }
            }
        }
    }
    
    val singularClaim = claims.filter { (claim, points) -> 
        (claims - claim).all { (_, pointsList) -> 
            pointsList.intersect(points.toSet()).isEmpty()
        }
    }
    
    return singularClaim.keys.first().id.toInt()
}

private fun extracted(line: String): Claim {
    val foo = line.split(" @ ")
    val id = foo.first().substring(1)
    val split = foo[1].split(": ")
    val pos = split.first()
    val startPoint = pos.split(",").first().toInt() to pos.split(",").second().toInt()
    val sizeX = split.second().split("x").first().toInt()
    val sizeY = split.second().split("x").second().toInt()

    return Claim(id, startPoint, sizeX, sizeY)
}

fun main() {

    val input = readInput("main/day03/Day03")
    println(part1(input))
    println(part2(input))
}

typealias Point = Pair<Int, Int>
data class Claim(
    val id: String, 
    val startPoint: Point,
    val width: Int,
    val height: Int
) {
    val size get() = width * height
}
