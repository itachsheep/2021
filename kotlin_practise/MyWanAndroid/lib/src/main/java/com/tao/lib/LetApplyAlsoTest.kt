package com.tao.lib


fun testApply() {
    val wUser = WUSer("taowei2",29,"18993040909")
    val res = wUser.apply {
        println("my name is ${this.name} \n "
                + " age is ${wUser.age} \n"
                + " phone: ${this.phone}")
        1000
    }
    println("res = $res")
}

fun testRun() {
    val wUser = WUSer("taowei2",29,"18993040909")
    val res = wUser.run {
        println("my name is ${this.name} \n "
                + " age is ${wUser.age} \n"
                + " phone: ${this.phone}")
    }
}

fun testWith() {
    val wUser = WUSer("taowei",29,"18993040909")
    val res = with(wUser) {
        println("my name is ${this.name} \n "
        + " age is ${wUser.age} \n"
        + " phone: ${this.phone}")
        "Good Morning"
    }
    println("$res")
}

fun testLet() {
    val res = "result".let { it ->
        println("length = ${it.length}")
        "HaHa"
    }
    println("res = $res")
}

class WUSer(name: String , age: Int, phone: String) {
    val name = name
    val age = age
    val phone = phone
}