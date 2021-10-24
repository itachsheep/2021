package com.tao.module_wan.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseFragment
import com.hao.library.ui.BaseListFragment
import com.tao.module_wan.adapter.ArticleAdapter
import com.tao.module_wan.databinding.WanFragmentProjectBinding
import com.tao.module_wan.model.Article
import com.tao.module_wan.viewmodel.project.ProjectViewModel

@AndroidEntryPoint
class ProjectFragment: BaseListFragment<WanFragmentProjectBinding,
                Article, ProjectViewModel,ArticleAdapter>() {
    override fun initView() {
        super.initView()

    }


}