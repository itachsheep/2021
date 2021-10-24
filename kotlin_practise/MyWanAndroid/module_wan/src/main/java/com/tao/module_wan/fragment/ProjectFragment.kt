package com.tao.module_wan.fragment

import android.os.Bundle
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.extensions.visibility
import com.hao.library.extensions.visible
import com.hao.library.ui.BaseFragment
import com.hao.library.ui.BaseListFragment
import com.hao.library.ui.UIParams
import com.tao.module_base.LogUtils
import com.tao.module_wan.adapter.ArticleAdapter
import com.tao.module_wan.adapter.ProjectAdjustPageAdapter
import com.tao.module_wan.databinding.WanFragmentProjectBinding
import com.tao.module_wan.model.Article
import com.tao.module_wan.viewmodel.project.ProjectViewModel

@AndroidEntryPoint
class ProjectFragment: BaseListFragment<WanFragmentProjectBinding,
                Article, ProjectViewModel,ArticleAdapter>() {

    lateinit var viewPagerAdapter: ProjectAdjustPageAdapter

    override fun prepare(uiParams: UIParams, bundle: Bundle?) {
        uiParams.isLazy = true
    }

    override fun initView() {
        super.initView()
        viewBinding {
            viewPagerAdapter = ProjectAdjustPageAdapter()
            vpType.adapter = viewPagerAdapter
            indicator.setViewPager(vpType)
            viewPagerAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        }
    }

    override fun initData() {
        LogUtils.d("initData --> ")
        viewModel {
            lifecycle.addObserver(this)
            projectAdjustLiveData.observe(this@ProjectFragment) {
                LogUtils.d("initData observe refreh view")
                viewPagerAdapter.resetData(it)
                viewBinding {
                    line.visible()
                    indicator.visibility(it?.size ?: 0 > 1)
                }
            }
        }
        super.initData()

    }

}