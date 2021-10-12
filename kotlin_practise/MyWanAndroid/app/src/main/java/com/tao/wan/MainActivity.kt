package com.tao.wan

import android.view.Gravity
import androidx.fragment.app.Fragment
import com.hao.library.adapter.FragmentAdapter
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseActivity
import com.hao.library.ui.FragmentCreator
import com.hao.library.view.dialog.ConfirmDialog
import com.hao.library.view.dialog.ConfirmDialogListener
import com.tao.module_base.LogUtils
import com.tao.module_user.fragment.UserFragment
import com.tao.module_wan.fragment.ProjectFragment
import com.tao.module_wan.fragment.WechatFragment
import com.tao.module_wan.viewmodel.UpgradeViewModel
import com.tao.wan.databinding.AppActivityMainBinding
import com.tencent.bugly.beta.UpgradeInfo

@AndroidEntryPoint
class MainActivity : BaseActivity<AppActivityMainBinding, UpgradeViewModel>() {
    override fun initData() {
        viewModel {
            lifecycle.addObserver(this)
            LogUtils.d("initData viewModel")
            upgradeLiveData.observe(this@MainActivity) {
                showUpgradeDialog(it)
            }
        }
    }

    private fun showUpgradeDialog(upgradeInfo: UpgradeInfo) {
        LogUtils.d("showUpgradeDialog")
        val message = "版本：${upgradeInfo.versionName}\n\n更新说明：\n${upgradeInfo.newFeature}"
        ConfirmDialog.Builder(this@MainActivity)
                .setTitle("发现新版本")
                .setMessage(message)
                .setMessageGravity(Gravity.LEFT)
                .setCancelBtnText("下次再说")
                .setConfirmBtnText("立即更新")
                .setListener(object : ConfirmDialogListener {
                    override fun confirm() {
                        viewModel {
                            //startDownload()
                            LogUtils.d("ConfirmDialogListener confirm")
                        }
                    }

                    override fun cancel() {
                        viewModel {
                            //cancelDownload()
                            LogUtils.d("ConfirmDialogListener cancel")
                        }
                    }

                }).build().show()
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

