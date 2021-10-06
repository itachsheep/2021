package com.tao.lib

/*
** 提示报错
**
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = IntPredicate { it % 2 == 0 }
*/


fun main(args: Array<String>) {
    testWithTimeOut()
}

fun testVarArg() {
    val arr = arrayOf(-1, -20, -100)
    val asList = asList(1, 2, 3, 4, 5, *arr, 6, 7, 7, 88, 10)
    for (i in asList) {
        print("$i ,")
    }
}

fun testWeiTuoMode() {
    val b = BasePrintImpl(10)
    DerivedPrint(b).print()
}

fun testTypedef() {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f)) // 输出 "true"
}

typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(42)



private fun testAnonymousObj() = object {
    val x = 100
}

fun testAnonymousObject() {
    val anonymousObj = object {
        var x = 0
        var y: Int = 0
    }
    println("${anonymousObj.x} , ${anonymousObj.y}")
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}


fun testCompanion() {
    val instance = MyClsCompanion.create()
    instance.test()

}

fun testExtraFunction() {
    val list = mutableListOf<Int>(1, 2, 3)
    list.forEach { print("$it ,") }
    list.swap(0,2)
    println()
    list.forEach { print("$it ,") }
}

/**
 * 先执行Base构造，
 * 再执行自己的构造
 */
fun testConstructorSeqExeute() {
    Derived("hello","world")
}

fun testOpenVal() {
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(1.0,2.0,3.0)
    println("${rectangle.vertexCount}")
    println("${triangle.vertexCount}")
}

fun testConstructor() {
    //InOrderDemo("hello");
    val father = Person("father")

    println("------------------------")
    Person("son",father)

}

fun testReturn() {
    abc@ for (i in 1..100) {
        if(i == 6) continue@abc
        if(i == 10) break@abc
        print("$i ,")
    }
    println()


    listOf<Int>(1,2,3,4,5,6,7,8).forEach {
        if(it == 5) return@forEach
        print("$it ,")
    }
    println()
    println("this is not reachable")
}

fun testChar() {
    for (c in "lsdjfuosdfl") {
        print("$c ,")
    }
}

/**
 * 数组用array表示
 */
fun testArray() {
   //val array = Array<Int>(5) { i -> i * i}
   val arr = IntArray(5){ 23 }
    arr.forEach { print("$it , ") }
}

fun testShL() {
    println((1 shl 2))
    println(8 shr 2)
    println(7 and 4)
}

fun testBox() {
    val a: Int = 128
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    val b: Int = 100
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedA === anotherBoxedA) // true
    println(boxedB === anotherBoxedB) //false
}


fun testFilter() {
    val list = listOf<Int>(32, -12,34,-4,3,-32,9,0,88,-91)
    val positives = list.filter { it > 0 }
    positives.forEach { print("$it ,") }
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