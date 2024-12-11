package com.mozhimen.pushk.engagelab.test

import android.app.Application
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.mozhimen.pushk.engagelab.PushKEngagelabMgr
import com.mozhimen.pushk.engagelab.optins.OBuildApp_NeedManifestPlaceholders

/**
 * @ClassName MainApplication
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:57
 * @Version 1.0
 */
/**
 * 用于演示ENGAGELAB-sdk配置
 */
class MainApplication : Application() {
    @OptIn(OBuildApp_NeedManifestPlaceholders::class)
    override fun onCreate() {
        super.onCreate()

        PushKEngagelabMgr.init(this)
    }
}
