package com.tao.wan

import androidx.fragment.app.Fragment
import com.hao.library.adapter.FragmentAdapter
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseActivity
import com.hao.library.ui.FragmentCreator
import com.tao.module_user.fragment.UserFragment
import com.tao.module_wan.fragment.ProjectFragment
import com.tao.module_wan.fragment.WechatFragment
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
        val fragments = listOf(
                object : FragmentCreator {
                    override fun createFragment(): Fragment {
                        val fragment = WechatFragment()
                        return fragment
                    }
                },
                object : FragmentCreator {
                    override fun createFragment(): Fragment {
                        return ProjectFragment()
                    }
                },
                object : FragmentCreator {
                    override fun createFragment(): Fragment {
                        return ProjectFragment()
                    }
                },
                object : FragmentCreator {
                    override fun createFragment(): Fragment {
                        return ProjectFragment()
                    }
                }
        )
        viewBinding {
            viewPager.apply {
                isUserInputEnabled = false
                offscreenPageLimit = 3
                adapter = FragmentAdapter(supportFragmentManager,lifecycle,fragments)
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.leftNavigationView, UserFragment())
                .commit()
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                viewPager.setCurrentItem(
                    when(item.itemId) {
                        R.id.tab_project -> 1
                        R.id.tab_knowledge -> 2
                        R.id.tab_search -> 3
                        else -> 0
                    }, false
                )
                true
            }


            true
        }
    }
}

