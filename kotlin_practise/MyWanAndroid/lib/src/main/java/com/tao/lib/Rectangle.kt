package com.tao.lib

open class Rectangle(var height: Double, var width: Double)
    : Shape(listOf(height,width,height,width)), RectangleProperties {
    override val vertexCount: Int
        get() = 4

    override fun calculateArea(): Double {
        return height * width
    }

    override val isSquare: Boolean get() = height == width

}