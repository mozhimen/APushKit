package com.mozhimen.pushk.engagelab.impls

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.pushk.engagelab.R

/**
 * 用于演示-点击通知后activity跳转
 *
 * 确保没有调用[MTPushPrivatesApi.configOldPushVersion]，否则通知点击跳转不会跳转到此页面
 *
 * 不需要调用[MTPushPrivatesApi.reportNotificationOpened]，sdk内部已做处理
 */
class PushKEngagelabActivity : Activity(), IUtilK {
    private var tvMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        tvMessage = findViewById<TextView>(R.id.tv_message)
        onIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        onIntent(intent)
    }

    private fun onIntent(intent: Intent?) {
        try {
            Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show()
            if (intent == null) {
                return
            }
            //3.4.0版本开始不再使用对象，而使用json数据
            val notificationMessage = intent.getStringExtra("message_json") ?: return
            UtilKLogWrapper.d(TAG, "notificationMessage:$notificationMessage")
            tvMessage!!.text = notificationMessage
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
