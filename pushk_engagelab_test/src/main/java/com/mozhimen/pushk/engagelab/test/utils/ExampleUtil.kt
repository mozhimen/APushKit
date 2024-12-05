package com.mozhimen.pushk.engagelab.test.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.TextUtils
import com.engagelab.privates.common.component.MTCommonReceiver
import com.engagelab.privates.common.component.MTCommonService
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

    private var appVersionCode = 0
    private var appVersionName: String? = null

    private var commonServiceName: String? = null
    private var commonReceiverName: String? = null

    fun getAppVersionName(context: Context): String? {
        if (!TextUtils.isEmpty(appVersionName)) {
            return appVersionName
        }
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            appVersionName = info.versionName
            return appVersionName
        } catch (throwable: Throwable) {
            ExampleLogger.w(TAG, "getAppVersionName failed: " + throwable.message)
        }
        return ""
    }

    fun getAppVersionCode(context: Context): Int {
        if (appVersionCode != 0) {
            return appVersionCode
        }
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            appVersionCode = info.versionCode
            return appVersionCode
        } catch (throwable: Throwable) {
            ExampleLogger.w(TAG, "getAppVersionCode failed: " + throwable.message)
        }
        return 0
    }

    fun getAppKey(context: Context): String? {
        if (TextUtils.isEmpty(appKey)) {
            appKey = getMetaData(context, APP_KEY)
        }
        return appKey
    }

    fun getAppChannel(context: Context): String? {
        if (TextUtils.isEmpty(appChannel)) {
            appChannel = getMetaData(context, APP_CHANNEL)
        }
        return appChannel
    }

    fun getAppProcess(context: Context): String? {
        if (TextUtils.isEmpty(appProcess)) {
            appProcess = getMetaData(context, APP_PROCESS)
        }
        return appProcess
    }

    fun getMetaData(context: Context, metaDataName: String?): String {
        try {
            val info = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            if (info?.metaData != null) {
                return info.metaData[metaDataName].toString()
            }
        } catch (throwable: Throwable) {
            ExampleLogger.w(TAG, "getMetaData failed " + throwable.message)
        }
        return ""
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
