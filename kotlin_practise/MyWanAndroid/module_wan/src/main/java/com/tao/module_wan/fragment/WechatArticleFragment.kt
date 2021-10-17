/**
 * @ClassName:      WechatArticleFragment.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:28 PM
 */
package com.tao.module_wan.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseFragment
import com.hao.library.ui.BaseListFragment
import com.tao.module_base.LogUtils
import com.tao.module_base.constant.ExtraKey
import com.tao.module_wan.R
import com.tao.module_wan.adapter.ArticleAdapter
import com.tao.module_wan.databinding.WanWechatFramgentArticleBinding
import com.tao.module_wan.model.Article
import com.tao.module_wan.model.Author
import com.tao.module_wan.viewmodel.WechatArticleViewModel

@AndroidEntryPoint
class WechatArticleFragment :
    BaseListFragment<WanWechatFramgentArticleBinding,
            Article,
            WechatArticleViewModel,
            ArticleAdapter>(){

    override fun initData() {
        viewModel {
            lifecycle.addObserver(this)
            arguments?.apply {
                authorId = getInt(ExtraKey.INT, Author.ID_HONGYANG)
            }
        }
        super.initData()
    }

    override fun itemClicked(view: View, item: Article, position: Int) {
        //super.itemClicked(view, item, position)
        LogUtils.d("pos: $position , viewId: ${view.id} , view: $view ")
        when (view.id) {
            R.id.tvLink -> showSimpleToast("项目链接,跳转web页面")
            R.id.ivFavorite -> showSimpleToast("收藏")
            else -> showSimpleToast("其他，跳转项目页")
        }
    }

    private fun showSimpleToast( msg: String ) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun instance(authorId: Int): WechatArticleFragment {
            val fragment = WechatArticleFragment()
            val bundle = Bundle()
            bundle.putInt(ExtraKey.INT, authorId)
            fragment.arguments = bundle
            return fragment
        }
    }
}