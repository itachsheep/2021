package com.tao.lib


fun testSequence() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    // 将列表转换为序列
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("map $it , length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // 末端操作：以列表形式获取结果。
    println(lengthsSequence.toList())
}

fun testRangeIn() {
    val arr = arrayOf(1,2,3,4,5,6,7,8)
    for (i in 3 until arr.size - 1) {
        print("${arr[i]} ")
    }
}

fun testRemoveIterator() {
    val list = mutableListOf<String>("one", "two", "three")
    val iterator = list.iterator()
    iterator.next()
    iterator.remove()
    println(list)
}


fun testIterator() {
    val list = listOf<String>("one", "two", "three")
    val lisIterator = list.iterator()
    while (lisIterator.hasNext()) {
        val next = lisIterator.next()
        print("$next , ")
    }
    println()

    for (item in list) {
        print("$item ")
    }
    println()

    list.forEach {
        print("$it ")
    }
}

fun testMapConvert() {
    val set = setOf<Int>(1, 2, 3)
    val map = set.map { it * 3.1 }
    val mapIndexed = set.mapIndexed({ index, key -> (index + 1) * key })
    println(map)
    println(mapIndexed)
}

fun testInitCollection() {
    val double = List(3, { it * 3.0})
    println(double)
}

fun testMap() {
    val numMap = mapOf<String,Int>(
        "key1" to 1 ,
        "key2" to 2 ,
        "key3" to 3
    )

    val mutableMap = mutableMapOf<String, String>()
        .apply {
            this["one"] = "1";
            this["two"] = "2"
        }
    println("all key: ${numMap.keys}")
    println("all value: ${numMap.values}")

}

fun testHashSet() {
    val hashSet = HashSet<String>()
    hashSet.add("one")
    hashSet.add("1")
    hashSet.add("two")
    println(hashSet)
}

fun testList() {
//    val list = listOf<String>("one", "two", "three", "four","five")
    val list = mutableListOf<String>("one", "two", "three", "four","five")
    println("size: ${list.size}")
    println("ele 1th: ${list[0]}")
    println("ele 2th: ${list.get(1)}")
    println("ele 'four' index : ${list.indexOf("four")}")
    list.add("six")
    list.remove("one")
    println(list)
}

fun testGetShortWordsTo() {
    val words = "A long time ago in a galaxy far far away"
    val list = words.split(" ")
    val targetList = mutableListOf<String>()
    list.getShortWordsTo(targetList,3)
    println(targetList)
}

fun List<String>.getShortWordsTo(
    shortWords: MutableList<String>,
    maxLength: Int) {
    //this.filter { it.length <= maxLength }
    this.filterTo(shortWords) {
        it.length <= maxLength
    }
    val setArticle = setOf("a","A" ,"an", "An", "The", "the")
    shortWords -= setArticle
}

fun testPrintAll() {
    val list = listOf("one", "two", "three")
    printAll(list)

    val set = setOf("one", "two", "three")
    printAll(set)
}

fun <T> printAll(collections: Collection<T>) {
    for (it in collections) {
        print("$it , ")
    }
    println()
}