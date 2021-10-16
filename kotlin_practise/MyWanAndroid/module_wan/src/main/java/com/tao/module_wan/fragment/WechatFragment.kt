package com.tao.module_wan.fragment

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.annotation.Inject
import com.hao.library.extensions.loadCircle
import com.hao.library.ui.BaseFragment
import com.tao.module_base.callback.ActivityCallback
import com.tao.module_wan.R
import com.tao.module_wan.adapter.BannerAdapter
import com.tao.module_wan.databinding.WanFragmentWechatBinding
import com.tao.module_wan.viewmodel.WechatViewModel

@AndroidEntryPoint
class WechatFragment: BaseFragment<WanFragmentWechatBinding, WechatViewModel>() {

    private var activityCallback: ActivityCallback ?= null

    @Inject
    lateinit var bannerAdapter: BannerAdapter

    override fun initView() {
        viewBinding {
            ivAvatar.loadCircle(R.mipmap.wan_avatar)
            ivAvatar.setOnClickListener {
                activityCallback?.openDraw()
            }

            banner.adapter = bannerAdapter
            indicator.setViewPager(banner)
            bannerAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        }
    }

    override fun initData() {
        viewModel {
            adLiveData.observe(this@WechatFragment) {
                bannerAdapter.resetData(it)
            }
            initData()
        }
    }

    fun setActivityCallback(callback: ActivityCallback) {
        this.activityCallback = callback
    }


}