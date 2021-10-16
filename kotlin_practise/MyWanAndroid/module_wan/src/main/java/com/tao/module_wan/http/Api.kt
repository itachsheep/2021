package com.tao.module_wan.http

import com.hao.library.http.HttpManager
import com.tao.module_base.model.HttpResult
import com.tao.module_wan.model.Ad
import io.reactivex.Observable
import retrofit2.http.GET
import kotlin.collections.ArrayList

object Api: Service by HttpManager.RETROFIT.create(Service::class.java)

interface Service {

    @GET("banner/json")
    fun getAd(): Observable<HttpResult<ArrayList<Ad>>>
}