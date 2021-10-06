package com.tao.lib

interface BasePrint {
    fun print()
}

class BasePrintImpl(val x: Int ) : BasePrint {
    override fun print() {
        println("x = $x")
    }
}

class DerivedPrint(b: BasePrint) : BasePrint by b