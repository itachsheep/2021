/**
 * @ClassName:      ArticleAdapter.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 5:07 PM
 */
package com.tao.module_wan.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hao.library.adapter.BaseMultiTypePagedAdapter
import com.tao.module_wan.model.Article

class ArticleAdapter : BaseMultiTypePagedAdapter<Article>(ArticleDiff()) {

    class ArticleDiff : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
                oldItem.collect == newItem.collect
    }
}