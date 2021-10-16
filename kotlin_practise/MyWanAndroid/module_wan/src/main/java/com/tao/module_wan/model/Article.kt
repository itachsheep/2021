/**
 * @ClassName:      Article.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 4:48 PM
 */
package com.tao.module_wan.model

import com.hao.library.adapter.PagedAdapterItem
import com.hao.library.extensions.removeSymbol

class Article(var id: Int,
              var author: String,
              var niceDate: String,
              var link: String,
              var projectLink: String,
              var envelopePic: String,
              var collect: Boolean,
              var originId: Int): PagedAdapterItem{

    var title : String = ""
        get() {
            return if(null == field ||  "" == field) {
                ""
            } else {
                field.removeSymbol()
            }
        }

    var desc: String = ""
        get() {
            return if (null == field || "" == field) {
                ""
            } else {
                field.removeSymbol()
            }
        }

    override fun getKey(): Any {
        return id
    }
}