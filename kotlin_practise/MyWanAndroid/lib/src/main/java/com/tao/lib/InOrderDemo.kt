package com.tao.lib

class InOrderDemo(name: String) {
    val firstProperty = "First property $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property ${name.length} ".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class Person(name: String) {
    var children: MutableList<Person> = mutableListOf()

    init {
        println("main constructor called")
    }

    constructor(name: String, parent: Person): this(name) {
        parent.children.add(this)
        println("sub constructor called...")
    }
}

class DontCreateMe private constructor() {
    override fun toString(): String {
        return super.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

class Square(height: Double, width: Double)
    :Rectangle(height,width) {

}