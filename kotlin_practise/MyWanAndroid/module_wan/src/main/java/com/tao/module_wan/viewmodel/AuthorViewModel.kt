package com.tao.module_wan.viewmodel

import com.hao.library.http.subscribeBy
import com.hao.library.utils.CoroutineUtils
import com.hao.library.viewmodel.BaseViewModel
import com.tao.module_base.LogUtils
import com.tao.module_wan.db.Db
import com.tao.module_wan.http.Api
import com.tao.module_wan.model.Author

/**
 * 公众号页面 - 博客主条目： 获取各个博客主名称
 */
open class AuthorViewModel : BaseViewModel() {
    private var intIds = ""
    fun getAuthors() {
        CoroutineUtils.io {
            val list = Db.instance().authorDao().queryByVisible(Author.VISIBLE)
            LogUtils.d("start get author list size: ${list?.size}")
            if (list.isEmpty()) {
                Api.getAuthors().subscribeBy(
                        onResponse = { processData(it) },
                        onFailure = {
                             LogUtils.d("exception e: ${it.errorCode}, ${it.errorMsg}")
                            },
                        toastWhenSucceed = true,
                        toastWhenFailed = false).add()
            }
            /*Api.getAuthors().subscribeBy({
                LogUtils.d("start get author data size: ${it?.size}")
                processData(it)
            }) .add()*/
        }
    }

    private fun processData(data: ArrayList<Author>?) {
        if (data == null || data.isEmpty()) {
            return
        }
        var subscribeSort = 10
        var otherIndexSort = 100

        data.forEach {
            when {
                it.id == Author.ID_HONGYANG -> {
                    it.visible = 1
                    it.sort = 1
                }
                it.id == Author.ID_GUOLIN -> {
                    it.visible = 1
                    it.sort = 2
                }
                subscribeSort < 12 -> {
                    it.visible = 1
                    it.sort = subscribeSort++
                }
                else -> {
                    it.visible = 0
                    it.sort = otherIndexSort++
                }
            }
        }

        CoroutineUtils.io {
            Db.instance().authorDao().insert(data)
        }
    }
}