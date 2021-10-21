/**
 * @ClassName:      WechatArticleViewModel.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:52 PM
 */
package com.tao.module_wan.viewmodel

import com.hao.library.http.subscribeBy
import com.tao.module_base.LogUtils
import com.tao.module_wan.http.Api
import com.tao.module_wan.model.Article
import com.tao.module_wan.model.Author

/**
 * 公众号页面 - 每个博客主- 对应的文章数据
 */
class WechatArticleViewModel : BaseArticleViewModel() {
    var authorId: Int = Author.ID_HONGYANG

    override fun loadData(page: Int, onResponse: (ArrayList<Article>?) -> Unit) {
        LogUtils.d("start load wechat articles data")
        Api.getWechatArticles(authorId,page).subscribeBy ({
            onResponse(it?.datas)
        },{
            onResponse(null)
        }).add()
    }
}