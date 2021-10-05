package com.tao.lib

import com.sun.istack.internal.localization.NullLocalizable

fun main(args: Array<String>) {
    testCreateClass()
}

fun testCreateClass() {
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("rectangle area = ${rectangle.calculateArea()}, " +
            "perimeter = ${rectangle.perimeter}")
    println("triangle area = ${triangle.calculateArea()}, perimeter = ${triangle.perimeter}" )
}

fun testSet() {
    val list = listOf<String>("banana", "avocado", "apple", "kiwifruit")
    when {
        "orange" in list -> println("juicy")
        "banana" in list -> println("I like banana")
        else -> println("no my like")
    }

    list.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }


}

fun testRange() {
    val x = 10
    val y = 9
    if( x !in 1..y) {
        println("not in range")
    }

    for (x in 1..5) {
        print("$x,")
    }
    println()

    for (x in 1..10 step 2) {
        print("$x ,")
    }
    println()

    for (x in 9 downTo 0 step 2){
        print("$x ,")
    }
}

/*println(testWhen(1))
    println(testWhen(23748973294984372))
    println(testWhen(MyClass()))
    println(testWhen(1.908))*/
fun testWhen(obj: Any): String {
    return when (obj) {
        1 -> "one"
        "hello" -> "greeting"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "Unknown"
    }
}

fun testWhile() {
    val list = listOf<String>("apple", "banana", "kiwifruit")
    var index = 0
    while (index < list.size) {
        println("$index - ${list[index]}")
        index++
    }
}

fun testFor() {
    val list = listOf<String>("apple", "banana", "kiwifruit")
    for (item in list) {
        println(item)
    }

    println("---------------------------")

    for (index in list.indices) {
        println("$index - ${list[index]}")
    }

}

fun getStringLength(obj: Any): Int? {
    if ( obj is String) {
        return obj.length
    } else {
        return null
    }
}

fun parseInt(str: String): Int? {
    return null
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int , b: Int) = if ( a > b) a else b

fun stringTemplate() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is", "was")},but now is $a"
    println(s1)
    println(s2)
}

fun defineVariant() {
    val a: Int = 1
    val b = 2;
    val c: Int
    c = 3

    var x = 5
    x += 1

    println("a = $a")
}


fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun sum3(a: Int, b: Int) {
    println("sum3 $a + $b = ${a + b}")
}

class MyClass {

}