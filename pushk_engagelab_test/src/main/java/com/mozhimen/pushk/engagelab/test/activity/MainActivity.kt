package com.mozhimen.pushk.engagelab.test.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.engagelab.privates.common.log.MTCommonLog
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.pushk.engagelab.test.databinding.ActivityMainBinding
import com.mozhimen.pushk.engagelab.test.listener.OnStatusListener
import com.mozhimen.pushk.engagelab.test.listener.StatusObserver
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
class MainActivity : BaseActivityVB<ActivityMainBinding>(), OnStatusListener {
    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        vb.tvUid.setText(MTCorePrivatesApi.getUserId(this.applicationContext).toString() + "")
        vb.tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.applicationContext))

        // 监听状态变化
        StatusObserver.getInstance().addListener(this)
    }

    override fun onPause() {
        // 监听移除
        StatusObserver.getInstance().removeListener()
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
                Toast.makeText(this.applicationContext, "userService is null, please create new Service extends MTCommonService", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(ExampleUtil.getCommonReceiverName(this.applicationContext))) {
                MTCommonLog.e(TAG, "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver")
                Toast.makeText(this.applicationContext, "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val pushIntent = Intent(this, PushActivity::class.java)
            startActivity(pushIntent)
        }
    }

    override fun onNotificationStatus(status: Boolean) {

    }

    @SuppressLint("SetTextI18n")
    override fun onConnectStatus(status: Boolean) {
        if (status) {
            vb.tvUid.setText(MTCorePrivatesApi.getUserId(this.applicationContext).toString() + "")
            vb.tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.applicationContext))
        }
    }
}