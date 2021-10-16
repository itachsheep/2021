/**
 * @ClassName:      ArticleAdapter.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 5:07 PM
 */
package com.tao.module_wan.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.hao.library.adapter.BaseMultiTypePagedAdapter
import com.hao.library.adapter.ItemViewDelegate
import com.hao.library.adapter.ViewHolder
import com.hao.library.extensions.gone
import com.hao.library.extensions.load
import com.hao.library.extensions.visible
import com.tao.module_wan.R
import com.tao.module_wan.databinding.WanArticleItemNoImageBinding
import com.tao.module_wan.databinding.WanArticleItemWithImageBinding
import com.tao.module_wan.databinding.WanItemArticleBottomBinding
import com.tao.module_wan.model.Article

class ArticleAdapter : BaseMultiTypePagedAdapter<Article>(ArticleDiff()) {

    init {
        addDelegate(ItemWithImage())
        addDelegate(ItemNoImage())
    }

    inner class ItemNoImage : ItemViewDelegate<WanArticleItemNoImageBinding,Article> {
        override fun bindViewHolder(viewHolder: ViewHolder<WanArticleItemNoImageBinding>, item: Article, position: Int, payloads: MutableList<Any>) {
            if (payloads.isEmpty() || "fav" != payloads[0]) {
                viewHolder.viewBinding {
                    tvTitle.text = item.title
                    if (TextUtils.isEmpty(item.desc)) {
                        tvDesc.gone()
                    } else {
                        tvDesc.visible()
                        tvDesc.text = item.desc
                    }

                    bindBottom(bottom, item, position)
                }
            } else {
                viewHolder.viewBinding { bindFav(bottom, item) }
            }
        }

        override fun getViewBinding(layoutInflater: LayoutInflater,
                                    parent: ViewGroup): WanArticleItemNoImageBinding {
            return WanArticleItemNoImageBinding.inflate(layoutInflater,
                    parent,false)
        }

        override fun isViewType(item: Article, position: Int): Boolean {
            return TextUtils.isEmpty(item.envelopePic)
        }

    }

    inner class ItemWithImage : ItemViewDelegate<WanArticleItemWithImageBinding, Article> {
        override fun bindViewHolder(viewHolder: ViewHolder<WanArticleItemWithImageBinding>, item: Article, position: Int, payloads: MutableList<Any>) {
            if (payloads.isEmpty() || "fav" != payloads[0]) {
                viewHolder.viewBinding {
                    ivThumbnail.load(item.envelopePic)
                    tvTitle.text = item.title
                    tvDesc.text = item.desc
                    bindBottom(bottom, item, position)
                }
            } else {
                viewHolder.viewBinding {
                    bindFav(bottom, item)
                }
            }
        }

        override fun getViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup): WanArticleItemWithImageBinding {
            return WanArticleItemWithImageBinding.inflate(layoutInflater,parent,false)
        }

        override fun isViewType(item: Article, position: Int): Boolean {
            return !TextUtils.isEmpty(item.envelopePic)
        }

    }


    private fun bindBottom(bottom: WanItemArticleBottomBinding, item: Article, position: Int) {
        val click: (View) -> Unit = {
            itemClickListener?.apply {
                itemClicked(it, item, position)
            }
        }
        bottom.apply {
            if (TextUtils.isEmpty(item.author)) {
                tvAuthor.gone()
            } else {
                tvAuthor.visible()
                tvAuthor.text = item.author
            }

            tvTime.text = item.niceDate

            if (TextUtils.isEmpty(item.projectLink)) {
                tvLink.gone()
                tvLink.setOnClickListener(null)
            } else {
                tvLink.visible()
                tvLink.setOnClickListener(click)
            }
            bindFav(bottom, item)
            ivFavorite.setOnClickListener(click)
        }
    }

    private fun bindFav(bottom: WanItemArticleBottomBinding, item: Article) {
        bottom.ivFavorite.setImageResource(if (item.collect) R.drawable.wan_ic_favorite_1 else R.drawable.wan_ic_favorite_0)
    }

    class ArticleDiff : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem.collect == newItem.collect
    }

}