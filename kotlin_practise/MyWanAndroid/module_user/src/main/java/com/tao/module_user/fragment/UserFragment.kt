package com.tao.module_user.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseFragment
import com.tao.module_user.databinding.WanFragmentUserBinding
import com.tao.module_user.viewmodel.UserViewModel

@AndroidEntryPoint
class UserFragment: BaseFragment<WanFragmentUserBinding, UserViewModel>() {
    override fun initData() {
        viewModel {

        }
    }

    override fun initView() {
        viewBinding {

        }
    }

}