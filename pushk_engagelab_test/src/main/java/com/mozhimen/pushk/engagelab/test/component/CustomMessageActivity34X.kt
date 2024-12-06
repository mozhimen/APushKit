package com.mozhimen.pushk.engagelab.test.component

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.engagelab.privates.push.api.CustomMessage
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.pushk.engagelab.test.databinding.ActivityIntentBinding
import com.mozhimen.pushk.engagelab.test.log.ExampleLogger

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
            Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show()
            if (intent == null) {
                return
            }
            val customMessage = intent.getParcelableExtra<CustomMessage>("message") ?: return
            ExampleLogger.d(TAG, "customMessage:$customMessage")
            vb.tvMessage.text = customMessage.toString()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            ExampleLogger.d(TAG, "onIntent failed:" + throwable.message)
        }
    }
}