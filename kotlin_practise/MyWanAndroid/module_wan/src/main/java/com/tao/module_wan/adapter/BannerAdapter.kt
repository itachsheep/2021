/**
 * @ClassName:      BannerAdapter.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 10:48 AM
 */
package com.tao.module_wan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hao.library.adapter.BaseNormalAdapter
import com.hao.library.adapter.ViewHolder
import com.hao.library.extensions.load
import com.tao.module_base.LogUtils
import com.tao.module_wan.databinding.WanWechatBannerItemBinding
import com.tao.module_wan.model.Ad

class BannerAdapter : BaseNormalAdapter<WanWechatBannerItemBinding, Ad>() {
    override fun bindViewHolder(
            viewHolder: ViewHolder<WanWechatBannerItemBinding>,
            item: Ad,
            position: Int,
            payloads: MutableList<Any>) {
        viewHolder.viewBinding {
            LogUtils.d("load url: ${item.imagePath}")
            root.load(item.imagePath)
        }

    }

    override fun getViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup)
        : WanWechatBannerItemBinding {
        return WanWechatBannerItemBinding.inflate(layoutInflater,parent,false)
    }

}