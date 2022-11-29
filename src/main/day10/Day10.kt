package day10

import day06.Point
import readInput

fun part1(input: List<String>) {
    
    var stars = input.map { it.toStar() }
    var seconds = 0
    do {
        stars = stars.move()
        seconds++
    } while(!stars.prettyPrint())
    println(seconds) 
}

fun main() {
    val input = readInput("main/day10/Day10")
    part1(input)
}

fun String.toStar(): Star {
    val values = this.split(">")
    val pos = values[0]
    val vel = values[1]
    
    val coordinates = pos.split("<").last().split(",").map { it.trim().toInt() }
    val velocity = vel.split("<").last().split(",").map { it.trim().toInt() }
    return Star(coordinates.first(), coordinates.last(), Vector(velocity.first(), velocity.last()))
}

data class Vector(val x: Int,  val y: Int)

data class Star(val x: Int, val y: Int, val velocity: Vector) {
    fun toPoint(): Point = Point(x,y)
}

fun List<Star>.move(): List<Star> = this.map {
    Star(it.x + it.velocity.x, it.y + it.velocity.y, it.velocity)
}

fun List<Star>.prettyPrint(): Boolean {
    val minY = minOf { it.y }
    val maxY = maxOf { it.y }

    if (maxY - minY > 15) return false
    
    val points = map{it.toPoint()}
    (minY..maxY).forEach { y ->
        (minOf { it.x } .. maxOf{it.x}).forEach { x ->
            if(points.contains(Point(x,y))) print("#") else print(" ")
        }
        println()
    }
    println()
    
    return true
}