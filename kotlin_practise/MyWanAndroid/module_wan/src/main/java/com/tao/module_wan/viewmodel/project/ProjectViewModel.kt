package com.tao.module_wan.viewmodel.project

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hao.library.http.subscribeBy
import com.hao.library.viewmodel.BaseViewModel
import com.tao.module_base.LogUtils
import com.tao.module_wan.http.Api
import com.tao.module_wan.model.Article
import com.tao.module_wan.model.ProjectAdjust
import com.tao.module_wan.viewmodel.BaseArticleViewModel

class ProjectViewModel: BaseArticleViewModel() {
    val projectAdjustLiveData = MutableLiveData<ArrayList<ArrayList<ProjectAdjust>>>()

    override fun refresh(callback: PageKeyedDataSource.LoadInitialCallback<Int, Article>) {
        super.refresh(callback)
        LogUtils.d("refresh ")
        Api.getProjectType().subscribeBy({
                    if (it != null && it.isNotEmpty()) {
                        val list = ArrayList<ArrayList<ProjectAdjust>>()
                        val size = it.size
                        LogUtils.d("refresh size: $size")
                        if (size <= 8) {
                            list.add(it)
                            projectAdjustLiveData.value = list
                        }
                    }
                },
                {
                    LogUtils.d("refresh exception: ${it.errorMsg}")
                    projectAdjustLiveData.value = null
                }
        ).add()
    }

    override fun loadData(page: Int, onResponse: (ArrayList<Article>?) -> Unit) {
        LogUtils.d("todo:: loadData")
    }
}