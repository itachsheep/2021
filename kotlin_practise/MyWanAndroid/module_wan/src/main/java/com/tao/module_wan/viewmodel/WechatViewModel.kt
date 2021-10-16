package com.tao.module_wan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hao.library.http.subscribeBy
import com.tao.module_base.LogUtils
import com.tao.module_wan.http.Api
import com.tao.module_wan.model.Ad

class WechatViewModel: AuthorViewModel() {
    val adLiveData = MutableLiveData<ArrayList<Ad>>()

    fun initData() {
        Api.getAd().subscribeBy({
            if(it != null) {
//                LogUtils.d("getAd size: ${it.size}" )
                adLiveData.value = it
            }
        }).add()
        getAuthors()
    }
}