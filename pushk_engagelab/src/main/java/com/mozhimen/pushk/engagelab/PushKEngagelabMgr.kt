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

        /**
         * 需要在Application.onCreate()方法中调用
         * 请在init之前调用
         *
         * 设置长连接是否加密，默认不加密
         * 需要在Application.onCreate()方法中调用
         * @param isSSL true加密，false不加密
         */
        MTCorePrivatesApi.setTcpSSl(context, false)

        /**
         * 需要在Application.onCreate()方法中调用
         * 请在init之前调用
         * Engagelab日志格式，搜索"ENGAGELAB-PRIVATES"
         * Engagelab会分别在主进程和子进程打印日志
         *
         * 设置是否debug模式，debug模式会打印更对详细日志
         * 需要在Application.onCreate()方法中调用
         * @param context 不为空
         * @param enable 是否调试模式，true为调试模式，false不是
         */
        // 必须在application.onCreate中配置，不要判断进程，sdk内部有判断
        MTCorePrivatesApi.configDebugMode(context, BuildConfig.DEBUG);

        /**
         * 后台没升级tag: V3.5.4-newportal-20210823-gamma.57版本，前端必须调用此方法，否则通知点击跳转有问题
         * 在Application.onCreate()方法中调用
         * 请在init前调用
         *
         * 配置push版本号为3.9.X
         * @param context 不为空
         */
        // 后台没升级tag: V3.5.4-newportal-20210823-gamma.57版本，前端必须调用此方法，否则通知点击跳转有问题
        // MTPushPrivatesApi.configOldPushVersion(context.getApplicationContext());

        /**
         * 建议在Application.onCreate()方法中调用
         *
         * MTPush初始化
         * 建议在Application.onCreate()方法中调用
         * @param context 不为空，请使用applicationContext对象
         */
        // 初始化推送
         MTPushPrivatesApi.init(context);
    }
}