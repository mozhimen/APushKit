package com.mozhimen.pushk.engagelab

import android.content.Context
import androidx.annotation.AnyThread
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.mozhimen.kotlin.utilk.BuildConfig
import com.mozhimen.pushk.engagelab.optins.OBuildApp_NeedManifestPlaceholders

/**
 * @ClassName PushKEngagelabMgr
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/11/29
 * @Version 1.0
 */
object PushKEngagelabMgr {
    @JvmStatic
    @AnyThread
    @OBuildApp_NeedManifestPlaceholders
    fun init(context: Context) {

        // 必须在application.onCreate中配置，不要判断进程，sdk内部有判断
        MTCorePrivatesApi.configDebugMode(context, BuildConfig.DEBUG);

        // 后台没升级tag: V3.5.4-newportal-20210823-gamma.57版本，前端必须调用此方法，否则通知点击跳转有问题
        // MTPushPrivatesApi.configOldPushVersion(context.getApplicationContext());

        // 初始化推送
         MTPushPrivatesApi.init(context);
    }
}