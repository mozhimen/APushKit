package com.mozhimen.pushk.engagelab.test.component

import android.content.Intent
import android.os.Bundle
import com.engagelab.privates.push.api.CustomMessage
import com.mozhimen.uik.databinding.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.kotlin.utilk.android.content.gainParcelableExtra
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.pushk.engagelab.test.databinding.ActivityIntentBinding

/**
 * @ClassName CustomMessageActivity
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/3
 * @Version 1.0
 */
/**
 * 用于演示自定义消息展示
 */
class CustomMessageActivity34X : BaseActivityVB<ActivityIntentBinding>() {

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
            val customMessage = intent.gainParcelableExtra<CustomMessage>("message") ?: return
            UtilKLogWrapper.d(TAG, "customMessage:$customMessage")
            vb.tvMessage.text = customMessage.toString()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            UtilKLogWrapper.d(TAG, "onIntent failed:" + throwable.message)
        }
    }
}