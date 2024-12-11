package com.mozhimen.pushk.engagelab.test.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.engagelab.privates.common.component.MTCommonReceiver
import com.engagelab.privates.common.component.MTCommonService
import com.mozhimen.kotlin.utilk.android.content.UtilKPackageManagerWrapper
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.wrapper.UtilKApp

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

    fun getCommonServiceName(context: Context): String? {
        if (!TextUtils.isEmpty(commonServiceName))
            return commonServiceName
        return UtilKPackageManagerWrapper.getServiceClazzName(context, INTENT_COMMON_SERVICE, MTCommonService::class.java).also { commonServiceName = it }
    }

    fun getCommonReceiverName(context: Context): String? {
        if (!TextUtils.isEmpty(commonReceiverName))
            return commonReceiverName
        return UtilKPackageManagerWrapper.getReceiverClazzName(context, INTENT_COMMON_RECEIVER, MTCommonReceiver::class.java).also { commonReceiverName = it }
    }
}
