package com.tao.lib

open class Base(val name: String) {

    init {
        println("Initializing Base")
    }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init {
        println("Initializing Derived")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}


open class MyRectangle {
    open fun draw() { /* …… */ }
}

interface Polygon {
    fun draw() { /* …… */ } // 接口成员默认就是“open”的
}

class MySquare(): MyRectangle(), Polygon {
    override fun draw() {
        super<MyRectangle>.draw()
        super<Polygon>.draw()
    }
}

class MyClsCompanion {
    companion object {
        fun create(): MyClsCompanion = MyClsCompanion()
    }

    fun test() {
        println("test")
    }
}

data class User(val name: String , val age: Int) {

}

class Outer {
    private val bar = 1

    class Inner {
        fun foo() {

        }
    }
}

enum class Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST
}

enum class ProtocolState {

    WAITING {
        override fun signal() {
            println("waiting")
        }
    },

    TALKING {
        override fun signal() {
            println("talking")
        }
    };

    abstract fun signal()
}


