package com.tao.module_wan.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseFragment
import com.tao.module_wan.databinding.WanFragmentWechatBinding
import com.tao.module_wan.viewmodel.WechatViewModel

@AndroidEntryPoint
class WechatFragment: BaseFragment<WanFragmentWechatBinding, WechatViewModel>() {
    override fun initData() {
        viewModel {

        }
    }

    override fun initView() {
        viewBinding {

        }
    }


}