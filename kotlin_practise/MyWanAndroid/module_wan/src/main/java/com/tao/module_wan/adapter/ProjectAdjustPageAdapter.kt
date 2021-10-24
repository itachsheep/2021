/**
 * @ClassName:      ProjectAdjustPageAdapter.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/24 10:49 AM
 */
package com.tao.module_wan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hao.library.adapter.BaseNormalAdapter
import com.hao.library.adapter.ViewHolder
import com.hao.library.extensions.init
import com.tao.module_base.LogUtils
import com.tao.module_wan.R
import com.tao.module_wan.databinding.WanItemProjectAdjustVpBinding
import com.tao.module_wan.databinding.WanItemofItemProjectAdjustVpBinding
import com.tao.module_wan.model.ProjectAdjust

class ProjectAdjustPageAdapter: BaseNormalAdapter<WanItemProjectAdjustVpBinding,
        ArrayList<ProjectAdjust>>() {
    override fun bindViewHolder(viewHolder: ViewHolder<WanItemProjectAdjustVpBinding>,
                                item: ArrayList<ProjectAdjust>,
                                position: Int,
                                payloads: MutableList<Any>) {
        viewHolder.viewBinding {
            val itemAdapter = ProjectAdjustItemAdapter()
            root.init(itemAdapter,4)
            itemAdapter.resetData(item)
        }
    }

    override fun getViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup)
        : WanItemProjectAdjustVpBinding {
        LogUtils.d("getViewBinding parent: $parent")
        return WanItemProjectAdjustVpBinding.inflate(layoutInflater,parent,false)
    }
}

class ProjectAdjustItemAdapter : BaseNormalAdapter<WanItemofItemProjectAdjustVpBinding,ProjectAdjust>() {
    override fun bindViewHolder(viewHolder: ViewHolder<WanItemofItemProjectAdjustVpBinding>,
                                item: ProjectAdjust,
                                position: Int,
                                payloads: MutableList<Any>) {
        viewHolder.viewBinding {
            tvText.text = item.name
            ivIcon.setImageResource(R.mipmap.ic_icon)
        }
    }

    override fun getViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup)
    : WanItemofItemProjectAdjustVpBinding {
        return WanItemofItemProjectAdjustVpBinding.inflate(layoutInflater,parent,false)
    }

}