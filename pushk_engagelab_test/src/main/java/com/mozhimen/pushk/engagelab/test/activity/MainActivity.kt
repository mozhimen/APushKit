package com.mozhimen.pushk.engagelab.test.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.engagelab.privates.common.log.MTCommonLog
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.CustomMessage
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.pushk.engagelab.PushKEngagelab
import com.mozhimen.pushk.engagelab.commons.IMTCommonListener
import com.mozhimen.pushk.engagelab.test.component.CustomMessageActivity34X
import com.mozhimen.pushk.engagelab.test.databinding.ActivityMainBinding
import com.mozhimen.pushk.engagelab.test.utils.ExampleUtil

/**
 * @ClassName MainActivity
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/3
 * @Version 1.0
 */
/**
 * 主要用于初始化
 */
class MainActivity : BaseActivityVB<ActivityMainBinding>(), IMTCommonListener {
    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        vb.tvUid.setText(MTCorePrivatesApi.getUserId(this.applicationContext).toString() + "")
        vb.tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.applicationContext))

        // 监听状态变化
        PushKEngagelab.instance.addMTCommonListener(this)
    }

    override fun onPause() {
        // 监听移除
        PushKEngagelab.instance.removeMTCommonListener(this)
        super.onPause()
    }

    override fun initView(savedInstanceState: Bundle?) {
        vb.tvPackageName.setText(this.applicationContext.packageName)
        vb.tvAppKey.setText(ExampleUtil.getAppKey(this.applicationContext))
        vb.tvAppChannel.setText(ExampleUtil.getAppChannel(this.applicationContext))
        vb.tvAppProcess.setText(ExampleUtil.getAppProcess(this.applicationContext))
        vb.tvUserService.setText(ExampleUtil.getCommonServiceName(this.applicationContext))
        vb.tvUserReceiver.setText(ExampleUtil.getCommonReceiverName(this.applicationContext))
        vb.btnPush.setOnClickListener {
            if (TextUtils.isEmpty(ExampleUtil.getCommonServiceName(this.applicationContext))) {
                MTCommonLog.e(TAG, "userService is null, please create new Service extends MTCommonService")
                "userService is null, please create new Service extends MTCommonService".showToast()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(ExampleUtil.getCommonReceiverName(this.applicationContext))) {
                MTCommonLog.e(TAG, "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver")
                "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver".showToast()
                return@setOnClickListener
            }
            val pushIntent = Intent(this, PushActivity::class.java)
            startActivity(pushIntent)
        }
    }

    override fun onCustomMessage(context: Context, customMessage: CustomMessage) {
        // 用于演示自定义消息展示
        val intent = Intent()
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setClass(context, CustomMessageActivity34X::class.java)
        intent.putExtra("message", customMessage)
        context.startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    override fun onConnectStatus(context: Context, enable: Boolean, registrationId: String?) {
        if (enable) {
            vb.tvUid.setText(MTCorePrivatesApi.getUserId(this.applicationContext).toString() + "")
            vb.tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.applicationContext))
        }
    }
}