package com.mozhimen.pushk.engagelab.test.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.engagelab.privates.common.component.MTCommonReceiver
import com.engagelab.privates.common.component.MTCommonService
import com.mozhimen.kotlin.utilk.wrapper.UtilKApp
import com.mozhimen.pushk.engagelab.test.log.ExampleLogger

/**
 * @ClassName ExampleUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:56
 * @Version 1.0
 */
object ExampleUtil {
    private const val TAG = "ExampleUtil"

    private const val APP_KEY = "ENGAGELAB_PRIVATES_APPKEY"
    private const val APP_CHANNEL = "ENGAGELAB_PRIVATES_CHANNEL"
    private const val APP_PROCESS = "ENGAGELAB_PRIVATES_PROCESS"

    private const val INTENT_COMMON_SERVICE = "com.engagelab.privates.intent.USER_SERVICE"
    private const val INTENT_COMMON_RECEIVER = "com.engagelab.privates.intent.USER_RECEIVER"

    private var appKey: String? = null
    private var appChannel: String? = null
    private var appProcess: String? = null

    private var commonServiceName: String? = null
    private var commonReceiverName: String? = null

    fun getAppKey(context: Context): String? {
        if (TextUtils.isEmpty(appKey)) {
            appKey = UtilKApp.getMetaDataStr(APP_KEY, context = context)
        }
        return appKey
    }

    fun getAppChannel(context: Context): String? {
        if (TextUtils.isEmpty(appChannel)) {
            appChannel = UtilKApp.getMetaDataStr(APP_CHANNEL, context = context)
        }
        return appChannel
    }

    fun getAppProcess(context: Context): String? {
        if (TextUtils.isEmpty(appProcess)) {
            appProcess = UtilKApp.getMetaDataStr(APP_PROCESS, context = context)
        }
        return appProcess
    }

    // ************************ Engagelab组件 ************************
    fun getCommonServiceName(context: Context): String? {
        if (!TextUtils.isEmpty(commonServiceName)) {
            return commonServiceName
        }
        try {
            val intent = Intent()
            intent.setAction(INTENT_COMMON_SERVICE)
            intent.setPackage(context.packageName)
            val resolveInfoList = context.packageManager.queryIntentServices(intent, 0)
            for (info in resolveInfoList) {
                val serviceInfo = info.serviceInfo ?: continue
                val className = serviceInfo.name
                if (TextUtils.isEmpty(className)) {
                    continue
                }
                val clazz = Class.forName(className)
                // MTCommonService是clazz的父类
                if (MTCommonService::class.java.isAssignableFrom(clazz)) {
                    return clazz.canonicalName.also { commonServiceName = it }
                }
            }
        } catch (throwable: Throwable) {
            ExampleLogger.w(TAG, "getCommonService failed " + throwable.message)
        }
        return ""
    }

    fun getCommonReceiverName(context: Context): String? {
        if (!TextUtils.isEmpty(commonReceiverName)) {
            return commonReceiverName
        }
        try {
            val intent = Intent()
            intent.setAction(INTENT_COMMON_RECEIVER)
            intent.setPackage(context.packageName)
            val resolveInfoList = context.packageManager.queryBroadcastReceivers(intent, 0)
            for (info in resolveInfoList) {
                if (info.activityInfo == null) {
                    continue
                }
                val className = info.activityInfo.name
                if (TextUtils.isEmpty(className)) {
                    continue
                }
                val clazz = Class.forName(className)
                // MTCommonReceiver是clazz的父类
                if (MTCommonReceiver::class.java.isAssignableFrom(clazz)) {
                    return (clazz.newInstance() as MTCommonReceiver).javaClass.canonicalName.also { commonReceiverName = it }
                }
            }
        } catch (throwable: Throwable) {
            ExampleLogger.w(TAG, "getCommonReceiver failed " + throwable.message)
        }
        return ""
    }
}
