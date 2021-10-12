package com.tao.module_wan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hao.library.viewmodel.BaseViewModel
import com.tao.module_base.BaseApplication
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.UpgradeInfo
import com.tencent.bugly.beta.upgrade.UpgradeListener

class UpgradeViewModel : BaseViewModel() {
    val upgradeLiveData = MutableLiveData<UpgradeInfo>()

    override fun onCreate() {
        super.onCreate()
        Beta.initDelay = 2 * 1000
        Beta.enableNotification = false
        Beta.upgradeListener = UpgradeListener { _, upgradeInfo, _, _ ->
            if (upgradeInfo != null) {
                upgradeLiveData.value = upgradeInfo
            }
        }
        Beta.init(BaseApplication.instance, true)

    }
}