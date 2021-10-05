package com.tao.lib

abstract class Shape(val sides: List<Double>) {
    open val vertexCount = 1

    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

