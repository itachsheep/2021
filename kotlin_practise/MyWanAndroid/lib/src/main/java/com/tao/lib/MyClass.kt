package com.tao.lib




fun main(args: Array<String>) {
    defineVariant()
    stringTemplate()
}

fun stringTemplate() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is","was")},but now is $a"
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