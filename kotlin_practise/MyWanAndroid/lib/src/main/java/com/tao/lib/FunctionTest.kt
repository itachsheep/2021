package com.tao.lib

import com.sun.corba.se.impl.orbutil.graph.Graph
import sun.security.provider.certpath.Vertex

fun testBlock() {
    ordinaryFunction(100){
        println("blockFunction entered")
        return@ordinaryFunction
        println("blockFunction end")
    }
}

fun ordinaryFunction(a: Int , block:() -> Unit) {
    println("ordinaryFunction entered ")
    block()
    println("ordinaryFunction end,  a = $a ")
}

fun testInvoke() {
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: (Int, Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("hello", " world"))

    println(intPlus.invoke(2,3))
    println(intPlus(3,3))
}


fun repeatFun() {
    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
    val twoParameters: (String ,Int) -> String = repeatFun

    fun runTransformation(f: (String,Int) -> String): String {
        return f("hello ", 3)
    }
    val result1 = runTransformation(repeatFun)
    val result2 = runTransformation(twoParameters)

    println("result1: $result1 , result2: $result2")
}

fun testIntTransformer() {
    val intTransformer:(Int) -> Int = IntTransformer()
    println("intTransformer: ${intTransformer(10)}")
}

class IntTransformer: (Int) -> Int {
    override fun invoke(p1: Int) = p1 * 100
}

fun testFold2() {
    val list = listOf(1,2,3,4,5)
    val fold1 = list.fold("Element", {
            acc, i ->
            println("$acc")
            "$acc $i"
    })

    val fold2 = list.fold(1, Int::times)
    println("fold1 = $fold1")
    println("fold2 = $fold2")
}

fun testFold1() {
    val list = listOf(1,2,3,4,5)
    val fold = list.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
            acc: Int, i: Int ->
        println("acc: $acc , i: $i, ")
        val result = acc + i
        println("result: $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })
    println("fold: $fold")

}

fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
//        for (v in current.neighbors)
//            dfs(v)
    }
//    dfs(graph.vertices[0])
}

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (item in ts) {
        result.add(item)
    }
    return result
}

fun executeLambda() {
    testLambda(1){println("hello")}
    testLambda(qux = { println("ha ha ha")})
    testLambda { println("xi xi xi") }
}

fun testLambda(bar: Int = -10, baz: Int = -100, qux: () -> Unit) {
    println("bar: $bar , baz: $baz")
    qux.invoke()
}