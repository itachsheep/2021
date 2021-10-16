/**
 * @ClassName:     WelcomActivity
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/14 10:36 PM
 */
package com.tao.wan

import android.content.Intent
import com.hao.library.annotation.AndroidEntryPoint
import com.hao.library.ui.BaseActivity
import com.hao.library.ui.UIParams
import com.hao.library.viewmodel.PlaceholderViewModel
import com.tao.module_base.LogUtils
import com.tao.wan.databinding.AppActivityWelcomeBinding

@AndroidEntryPoint
class WelcomeActivity: BaseActivity<AppActivityWelcomeBinding,PlaceholderViewModel>(){
    override fun prepare(uiParams: UIParams, intent: Intent?) {
        uiParams.isTransparentStatusBar = true
    }

    override fun initData() {
        LogUtils.d("initData")
    }

    override fun initView() {
        LogUtils.d("initView")
        toA(MainActivity::class.java, true)
    }
}