/**
 * @ClassName:      LiveDataViewModel.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/17 11:39 AM
 */
package com.tao.lrnpro.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel: ViewModel() {
    private var score: MutableLiveData<Int> = MutableLiveData()

    init {
        score.value = 1
    }

    fun addScore(num: Int) {
//        (num + (score.value!!)).also { score.value = it }
//        score.value = (score.value!! + num)
        score.value = (num + score.value!!)
    }

    fun getScoreValue():Int {
        return score.value!!
    }

    fun getScore(): MutableLiveData<Int> = score
}