package com.tao.module_wan.viewmodel.project

import com.hao.library.viewmodel.BaseViewModel
import com.tao.module_base.LogUtils
import com.tao.module_wan.model.Article
import com.tao.module_wan.viewmodel.BaseArticleViewModel

class ProjectViewModel: BaseArticleViewModel() {
    override fun loadData(page: Int, onResponse: (ArrayList<Article>?) -> Unit) {
        LogUtils.d("todo:: loadData")
    }
}