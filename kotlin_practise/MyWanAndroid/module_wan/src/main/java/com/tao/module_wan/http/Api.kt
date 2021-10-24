package com.tao.module_wan.http

import com.hao.library.http.HttpManager
import com.tao.module_base.model.HttpResult
import com.tao.module_base.model.ListPaged
import com.tao.module_wan.model.Ad
import com.tao.module_wan.model.Article
import com.tao.module_wan.model.Author
import com.tao.module_wan.model.ProjectAdjust
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.collections.ArrayList

object Api: Service by HttpManager.RETROFIT.create(Service::class.java)

interface Service {

    @GET("banner/json")
    fun getAd(): Observable<HttpResult<ArrayList<Ad>>>


    @GET("wxarticle/chapters/json")
    fun getAuthors(): Observable<HttpResult<ArrayList<Author>>>


    @GET("wxarticle/list/{authorId}/{page}/json")
    fun getWechatArticles(@Path("authorId") authorId : Int,
        @Path("page") page : Int) : Observable<HttpResult<ListPaged<Article>>>

    @GET("project/tree/json")
    fun getProjectType(): Observable<HttpResult<ArrayList<ProjectAdjust>>>
}