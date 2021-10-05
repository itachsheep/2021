package com.tao.lib

class Rectangle(var height: Double, var width: Double)
    : Shape(listOf(height,width,height,width)), RectangleProperties {
    override fun calculateArea(): Double {
        return height * width
    }

    override val isSquare: Boolean get() = height == width

}