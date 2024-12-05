package com.mozhimen.pushk.engagelab.test.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.engagelab.privates.push.api.NotificationLayout
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.pushk.engagelab.test.R
import com.mozhimen.pushk.engagelab.test.common.ExampleGlobal
import com.mozhimen.pushk.engagelab.test.databinding.ActivityPushBinding
import com.mozhimen.pushk.engagelab.test.listener.OnStatusListener
import com.mozhimen.pushk.engagelab.test.listener.StatusObserver

/**
 * @ClassName PushActivity
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/3
 * @Version 1.0
 */
/**
 * 用于演示MTPush功能
 */
class PushActivity : BaseActivityVB<ActivityPushBinding>(), OnStatusListener {
    private var tag: String = ""
    private var alias: String = ""
    private var showBeginHour = 0
    private var showEndHour = 0
    private var showWeekDay = 0

    private var silenceBeginHour = 0
    private var silenceBeginMinute = 0
    private var silenceEndHour = 0
    private var silenceEndMinute = 0

    ////////////////////////////////////////////////////////////////////

    override fun initView(savedInstanceState: Bundle?) {
        // 初始化
//        MTPushPrivatesApi.init(this);

        vb.btnBack.setOnClickListener {
            finish()
        }
//        vb.btnGoKeepalive.setOnClickListener{
            //演示保活功能
////                Intent keepaliveIntent = new Intent(this, KeepaliveActivity.class);
////                startActivity(keepaliveIntent);
//        }

        initState()
        initTagAlias()
        initShowTime()
        initSilenceTime()
        initNotificationLayout()
    }

    override fun onDestroy() {
        StatusObserver.getInstance().removeListener()
        super.onDestroy()
    }

    ////////////////////////////////////////////////////////////////////

    private fun initNotificationLayout() {
        vb.btnSetNotificationLayout.setOnClickListener {
            val text: Editable = vb.etNotificationLayout.getText()
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "内容为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val builderId = text.toString().toInt()
            val notificationLayout = NotificationLayout()
                .setLayoutId(R.layout.custom_notification_layout)
                .setIconViewId(R.id.iv_notification_icon)
                .setIconResourceId(R.drawable.mtpush_notification_icon)
                .setTitleViewId(R.id.tv_notification_title)
                .setContentViewId(R.id.tv_notification_content)
                .setTimeViewId(R.id.tv_notification_time)
            MTPushPrivatesApi.setNotificationLayout(this.applicationContext, builderId, notificationLayout)
        }
        vb.btnResetNotificationLayout.setOnClickListener {
            try {
                val texts: Editable = vb.etNotificationLayout.getText()
                if (TextUtils.isEmpty(texts)) {
                    Toast.makeText(this, "内容为空", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val resetNotificationLayoutId = texts.toString().toInt()
                MTPushPrivatesApi.resetNotificationLayout(this.applicationContext, resetNotificationLayoutId)
            } catch (e: Exception) {
                Toast.makeText(this.applicationContext, "请设置正确的自定义通知布局id", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getSilenceTime() {
        if (TextUtils.isEmpty(vb.etSilenceStartHour.getText().toString())) {
            silenceBeginHour = 0
        } else {
            silenceBeginHour = vb.etSilenceStartHour.getText().toString().toInt()
        }
        if (TextUtils.isEmpty(vb.etSilenceStartMinute.getText().toString())) {
            silenceBeginMinute = 0
        } else {
            silenceBeginMinute = vb.etSilenceStartMinute.getText().toString().toInt()
        }
        if (TextUtils.isEmpty(vb.etSilenceEndHour.getText().toString())) {
            silenceEndHour = 0
        } else {
            silenceEndHour = vb.etSilenceEndHour.getText().toString().toInt()
        }
        if (TextUtils.isEmpty(vb.etSilenceEndMinute.getText().toString())) {
            silenceEndMinute = 0
        } else {
            silenceEndMinute = vb.etSilenceEndMinute.getText().toString().toInt()
        }
    }

    private fun getShowTime() {
        if (TextUtils.isEmpty(vb.etPushStartHour.getText().toString())) {
            showBeginHour = 0
        } else {
            showBeginHour = vb.etPushStartHour.getText().toString().toInt()
        }
        if (TextUtils.isEmpty(vb.etPushEndHour.getText().toString())) {
            showEndHour = 0
        } else {
            showEndHour = vb.etPushEndHour.getText().toString().toInt()
        }
        if (TextUtils.isEmpty(vb.etPushWeek.getText().toString())) { // 这里代表空数组，任何时间都不可以展示通知
            showWeekDay = 0
        } else {
            showWeekDay = vb.etPushWeek.getText().toString().toInt()
        }
    }

    private fun initSilenceTime() {
        vb.btnSetSilenceTime.setOnClickListener {
            getSilenceTime()
            MTPushPrivatesApi.setNotificationSilenceTime(this.applicationContext, silenceBeginHour, silenceBeginMinute, silenceEndHour, silenceEndMinute)
        }
        vb.btnResetSilenceTime.setOnClickListener {
            MTPushPrivatesApi.resetNotificationSilenceTime(this.applicationContext)
        }
    }

    private fun initShowTime() {
        vb.btnSetShowTime.setOnClickListener{
            getShowTime()
            MTPushPrivatesApi.setNotificationShowTime(this.applicationContext, showBeginHour, showEndHour, showWeekDay)
        }
        vb.btnResetShowTime.setOnClickListener {
            MTPushPrivatesApi.resetNotificationShowTime(this.applicationContext)
        }
    }

    private fun initTagAlias() {
        vb.btnAddTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.addTag(this, ExampleGlobal.TAG_ADD, tag)
            } else {
                Toast.makeText(this.applicationContext, "tag is empty, can't add tag", Toast.LENGTH_SHORT).show()
            }
        }
        vb.btnDeleteTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.deleteTag(this, ExampleGlobal.TAG_DELETE, tag)
            } else {
                Toast.makeText(this.applicationContext, "tag is empty, can't delete tag", Toast.LENGTH_SHORT).show()
            }
        }
        vb.btnUpdateTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.updateTag(this, ExampleGlobal.TAG_UPDATE, tag)
            } else {
                Toast.makeText(this.applicationContext, "tag is empty, can't update tag", Toast.LENGTH_SHORT).show()
            }
        }
        vb.btnQueryTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.queryTag(this, ExampleGlobal.TAG_QUERY, tag)
            } else {
                Toast.makeText(this.applicationContext, "tag is empty, can't query tag", Toast.LENGTH_SHORT).show()
            }
        }
        vb.btnDeleteAllTag.setOnClickListener {
            MTPushPrivatesApi.deleteAllTag(this, ExampleGlobal.TAG_DELETE_ALL)
        }
        vb.btnQueryAllTag.setOnClickListener {
            MTPushPrivatesApi.queryAllTag(this, ExampleGlobal.TAG_QUERY_ALL)
        }
        vb.btnSetAlias.setOnClickListener {
            alias = vb.etAlias.getText().toString()
        }
        vb.btnGetAlias.setOnClickListener{
            alias = vb.etAlias.getText().toString()
            MTPushPrivatesApi.getAlias(this, ExampleGlobal.ALIAS_GET)
        }
        vb.btnClearAlias.setOnClickListener{
            MTPushPrivatesApi.clearAlias(this, ExampleGlobal.ALIAS_CLEAR)
        }
    }

    private fun initState() {
        vb.switchNotification.setChecked(ExampleGlobal.isNotificationEnable)
        vb.switchConnect.setChecked(ExampleGlobal.isConnectEnable)
        vb.switchGeofence.setChecked(ExampleGlobal.isConnectEnable)

        vb.btnGo2NotificationSetting.setOnClickListener {
            MTPushPrivatesApi.goToAppNotificationSettings(this)
        }
        vb.btnTurnOnPush.setOnClickListener {
            MTPushPrivatesApi.turnOnPush(this)
        }
        vb.btnTurnOffPush.setOnClickListener {
            MTPushPrivatesApi.turnOffPush(this)
        }
        vb.btnTurnOnGeofence.setOnClickListener {
            MTPushPrivatesApi.turnOnGeofenceSwitch(this)
            vb.switchGeofence.setChecked(true)
        }
        vb.btnTurnOffGeofence.setOnClickListener {
            MTPushPrivatesApi.turnOffGeofenceSwitch(this)
            vb.switchGeofence.setChecked(false)
        }
        StatusObserver.getInstance().addListener(this)
    }

    override fun onConnectStatus(status: Boolean) {
        vb.switchNotification.setChecked(status)
    }

    override fun onNotificationStatus(status: Boolean) {
        vb.switchConnect.setChecked(status)
    }
}