package com.tao.module_base.model

data class ListPaged<T>(
        var curPage: Int,
        var pageCount: Int,
        var size: Int,
        var total: Int,
        var datas: ArrayList<T>
)
