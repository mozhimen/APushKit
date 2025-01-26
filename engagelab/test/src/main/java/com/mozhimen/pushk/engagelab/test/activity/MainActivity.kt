package com.mozhimen.pushk.engagelab.test.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.engagelab.privates.common.log.MTCommonLog
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.CustomMessage
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVBVM
import com.mozhimen.bindk.bases.viewdatabinding.activity.BaseActivityVDBVM
import com.mozhimen.kotlin.elemk.android.content.cons.CIntent
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.pushk.engagelab.PushKEngagelab
import com.mozhimen.pushk.engagelab.commons.IMTCommonListener
import com.mozhimen.pushk.engagelab.test.MainViewModel
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
class MainActivity : BaseActivityVDBVM<ActivityMainBinding, MainViewModel>(), IMTCommonListener {
    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        vm.liveUid.value = MTCorePrivatesApi.getUserId(this.applicationContext).toString()
        vm.liveRid.value = MTCorePrivatesApi.getRegistrationId(this.applicationContext)
        // 监听状态变化
        PushKEngagelab.instance.addMTCommonListener(this)
    }

    override fun onPause() {
        // 监听移除
        PushKEngagelab.instance.removeMTCommonListener(this)
        super.onPause()
    }

    override fun initView(savedInstanceState: Bundle?) {
        vdb.btnPush.setOnClickListener {
            if (TextUtils.isEmpty(ExampleUtil.getCommonServiceName())) {
                MTCommonLog.e(TAG, "userService is null, please create new Service extends MTCommonService")
                "userService is null, please create new Service extends MTCommonService".showToast()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(ExampleUtil.getCommonReceiverName())) {
                MTCommonLog.e(TAG, "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver")
                "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver".showToast()
                return@setOnClickListener
            }
            startContext<PushActivity>()
        }
    }

    override fun bindViewVM(vdb: ActivityMainBinding) {
        vdb.vm = vm
    }

    override fun onCustomMessage(context: Context, customMessage: CustomMessage) {
        // 用于演示自定义消息展示
        startContext<CustomMessageActivity34X> {
            setFlags(CIntent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("message", customMessage)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onConnectStatus(context: Context, enable: Boolean, registrationId: String?) {
        if (enable) {
            vm.liveUid.value = MTCorePrivatesApi.getUserId(this.applicationContext).toString()
            vm.liveRid.value = MTCorePrivatesApi.getRegistrationId(this.applicationContext)
        }
    }
}