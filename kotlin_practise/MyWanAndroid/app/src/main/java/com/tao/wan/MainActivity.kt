package com.tao.wan

import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseActivity
import com.tao.module_wan.viewmodel.UpgradeViewModel
import com.tao.wan.databinding.AppActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<AppActivityMainBinding, UpgradeViewModel>() {
    override fun initData() {
        viewModel {
            lifecycle.addObserver(this)
            
        }
    }

    override fun initView() {
        viewBinding {

            true
        }
    }
}

