package com.tao.module_wan.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseFragment
import com.tao.module_wan.databinding.WanFragmentProjectBinding
import com.tao.module_wan.viewmodel.ProjectViewModel

@AndroidEntryPoint
class ProjectFragment: BaseFragment<WanFragmentProjectBinding,ProjectViewModel>() {
    override fun initData() {
        viewModel {

        }
    }

    override fun initView() {
        viewBinding {

        }
    }
}