package com.tao.lib

class Triangle(var sideA: Double, var sideB: Double, var sideC: Double)
    : Shape(listOf(sideA, sideB, sideC)){
    override fun calculateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }

}