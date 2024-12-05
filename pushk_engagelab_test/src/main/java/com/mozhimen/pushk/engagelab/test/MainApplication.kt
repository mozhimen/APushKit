package com.mozhimen.pushk.engagelab.test

import android.app.Application
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.MTPushPrivatesApi

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
    override fun onCreate() {
        super.onCreate()

        MTCorePrivatesApi.setTcpSSl(this, false)
        MTCorePrivatesApi.configDebugMode(this, true)
        // 初始化
        MTPushPrivatesApi.init(this)
    }
}
