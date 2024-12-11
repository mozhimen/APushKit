package com.mozhimen.pushk.engagelab.test.component

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.kotlin.utilk.org.json.jSONObject2strDump
import com.mozhimen.pushk.engagelab.test.databinding.ActivityIntentBinding
import org.json.JSONObject

/**
 * @ClassName UserActivity39X
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:46
 * @Version 1.0
 */
/**
 * 用于演示MTPush4.0.0之前-点击通知后activity跳转
 * 确保已调用[MTPushPrivatesApi.configOldPushVersion]，否则通知点击跳转不会跳转到此页面
 * 请调用[MTPushPrivatesApi.reportNotificationOpened]，上报通知点击打开activity，确保后台能统计
 */
class CustomMessageActivity39X : BaseActivityVB<ActivityIntentBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        onIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        onIntent(intent)
    }

    private fun onIntent(intent: Intent?) {
        try {
            TAG.showToast()
            if (intent == null) {
                return
            }
            val bundle = intent.extras
            var platformMessage: String? = ""
            // huawei
            if (intent.data != null) {
                platformMessage = intent.data.toString()
            }
            // 其他厂商
            if (TextUtils.isEmpty(platformMessage) && intent.extras != null) {
                platformMessage = if (bundle!!.containsKey("JMessageExtra")) {
                    bundle.getString("JMessageExtra")
                } else {
                    bundle.getString("MTMessageExtra")
                }
            }
            if (TextUtils.isEmpty(platformMessage)) {
                return
            }
            val messageJson = JSONObject(platformMessage)
            UtilKLogWrapper.d(TAG, "notificationMessage:" + messageJson)
            vb.tvMessage.text = messageJson.jSONObject2strDump()
            // 解析
            val messageId = messageJson.optString("msg_id")
            val platform = messageJson.optInt("rom_type").toByte()
            val title = messageJson.optString("n_title")
            val content = messageJson.optString("n_content")
            // 上报通知点击activity打开，建议所有厂商跳转都加上，仅MTPush4.0.0以下版本需要
            MTPushPrivatesApi.reportNotificationOpened(this, messageId, platform, "")
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            UtilKLogWrapper.w(TAG, "onIntent failed " + throwable.message)
        }
    }
}
