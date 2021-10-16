package com.tao.module_wan.fragment

import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hao.library.adapter.FragmentAdapter
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.annotation.Inject
import com.hao.library.extensions.loadCircle
import com.hao.library.ui.BaseFragment
import com.hao.library.ui.FragmentCreator
import com.tao.module_base.LogUtils
import com.tao.module_base.callback.ActivityCallback
import com.tao.module_wan.R
import com.tao.module_wan.adapter.BannerAdapter
import com.tao.module_wan.databinding.WanFragmentWechatBinding
import com.tao.module_wan.db.Db
import com.tao.module_wan.model.Author
import com.tao.module_wan.viewmodel.WechatViewModel

@AndroidEntryPoint
class WechatFragment: BaseFragment<WanFragmentWechatBinding, WechatViewModel>() {

    private var activityCallback: ActivityCallback ?= null

    private val titles = ArrayList<String>()
    private val fragments = ArrayList<FragmentCreator>()
    private var fragmentCount = 0
    private var currentIndex = -1
    private var fragmentAdapter: FragmentAdapter? = null

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
        Db.instance().authorDao().queryLiveDataByVisible(Author.VISIBLE).observe(this) {
            //在主线程
            LogUtils.d("wechat author data size: ${it.size}")
            if(it.isNotEmpty()) {
                titles.clear()
                fragments.clear()
                it.forEach { author ->
                    titles.add(author.name)
                    fragments.add(object : FragmentCreator {
                        override fun createFragment(): Fragment {
                            return WechatArticleFragment.instance(author.id)
                        }
                    })
                }
                fragmentCount = fragments.size
                fragmentAdapter = FragmentAdapter(childFragmentManager,lifecycle,fragments)

                viewBinding {
                    viewPager.adapter = fragmentAdapter
                    tabLayout.tabMode =
                            if(it.size > 4) TabLayout.MODE_SCROLLABLE else TabLayout.MODE_FIXED

                    TabLayoutMediator(tabLayout,viewPager) { tab,position ->
                        if(position in 0 until fragmentCount) {
                            tab.text = titles[position]
                        }
                    }.attach()
                }
            }
        }

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