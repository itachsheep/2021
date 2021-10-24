package com.tao.module_base

import android.util.Log

class LogUtils {
    companion object {
        var TAG: String = "T_WanAndroid."
        val flag: Boolean = true

        fun d(a: Any) = log(a)

        private fun log(any: Any) {
            if(!flag) {
                return
            }
            var msg = any.toString()
            var tag = getTag(getCallerStackTraceElement())
            Log.d(tag, msg)
        }

        /**生成TAG*/
        private fun getTag(element: StackTraceElement): String {
            //获取类名（去掉包名）
            var callerClazzName: String = element.className
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
            //生成TAG
            return "$TAG:$callerClazzName.${element.methodName}(${element.lineNumber})"
        }

        /**获取函数堆栈*/
        private fun getCallerStackTraceElement(): StackTraceElement = Thread.currentThread().getStackTrace()[5]
    }
}