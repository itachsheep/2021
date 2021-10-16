package com.tao.module_wan.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.extensions.loadCircle
import com.hao.library.ui.BaseFragment
import com.tao.module_base.callback.ActivityCallback
import com.tao.module_wan.R
import com.tao.module_wan.databinding.WanFragmentWechatBinding
import com.tao.module_wan.viewmodel.WechatViewModel

@AndroidEntryPoint
class WechatFragment: BaseFragment<WanFragmentWechatBinding, WechatViewModel>() {

    private var activityCallback: ActivityCallback ?= null
    //lateinit var bannerAdapter:

    override fun initView() {
        viewBinding {
            ivAvatar.loadCircle(R.mipmap.wan_avatar)
            ivAvatar.setOnClickListener {
                activityCallback?.openDraw()
            }

            //banner.adapter =
        }
    }

    override fun initData() {
        viewModel {

        }
    }

    fun setActivityCallback(callback: ActivityCallback) {
        this.activityCallback = callback
    }


}