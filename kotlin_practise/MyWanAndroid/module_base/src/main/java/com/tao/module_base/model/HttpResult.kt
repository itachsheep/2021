/**
 * @ClassName:      HttpResult.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/16 1:52 PM
 */
package com.tao.module_base.model

import com.hao.library.http.HttpResponseModel

data class HttpResult<D>(
        var errorCode: Int,
         var errorMsg: String,
         @JvmField
         var data: D?
):HttpResponseModel<D> {
    override fun getCode(): String = errorCode.toString()

    override fun getData(): D? = data

    override fun getMessage(): String = errorMsg

    override fun isSucceed(): Boolean = (0 == errorCode)

}