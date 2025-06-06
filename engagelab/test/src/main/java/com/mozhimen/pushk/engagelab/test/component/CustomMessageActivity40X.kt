package com.mozhimen.pushk.engagelab.test.component

import android.content.Intent
import android.os.Bundle
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.mozhimen.uik.databinding.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.kotlin.utilk.org.json.jSONObject2strDump
import com.mozhimen.pushk.engagelab.test.databinding.ActivityIntentBinding
import org.json.JSONObject

/**
 * @ClassName UserActivity400
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:49
 * @Version 1.0
 */
/**
 * 用于演示MTPush4.0.0开始-点击通知后activity跳转
 * 确保没有调用[MTPushPrivatesApi.configOldPushVersion]，否则通知点击跳转不会跳转到此页面
 * 不需要调用[MTPushPrivatesApi.reportNotificationOpened]，sdk内部已做处理
 */
class CustomMessageActivity40X : BaseActivityVB<ActivityIntentBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        onIntent(intent)
        UtilKLogWrapper.d(TAG, "onCreate")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        onIntent(intent)
        UtilKLogWrapper.d(TAG, "onNewIntent")
    }

    private fun onIntent(intent: Intent?) {
        try {
            TAG.showToast()
            if (intent == null) {
                return
            }
            val notificationMessage = intent.getStringExtra("message_json") ?: return
            val jsonString = JSONObject(notificationMessage).jSONObject2strDump()
            UtilKLogWrapper.d(TAG, "notificationMessage:$jsonString")
            vb.tvMessage.text = jsonString
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}