package com.tao.gradlebuilddemo

import junit.framework.TestCase
import org.junit.Test

class DbDaoTest : TestCase() {
    override fun setUp() {
        super.setUp()
    }

    @Test   
    fun testGetName() {
        val dbDao = DbDao()
        println("dbDao getName ---> ${dbDao.getName()}")
    }
}