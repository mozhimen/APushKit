package com.mozhimen.pushk.engagelab.test.activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import com.engagelab.privates.push.api.NotificationLayout
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.kotlin.utilk.android.widget.showToast
import com.mozhimen.pushk.engagelab.PushKEngagelab
import com.mozhimen.pushk.engagelab.commons.IMTCommonListener
import com.mozhimen.pushk.engagelab.cons.CPushKEngagelabCons
import com.mozhimen.pushk.engagelab.test.R
import com.mozhimen.pushk.engagelab.test.databinding.ActivityPushBinding
import com.mozhimen.pushk.engagelab.utils.UtilKMTPushPrivatesApi

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
class PushActivity : BaseActivityVB<ActivityPushBinding>(), IMTCommonListener {
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

    override fun initLayout() {
        super.initLayout()
        PushKEngagelab.instance.addMTCommonListener(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
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
        PushKEngagelab.instance.removeMTCommonListener(this)
        super.onDestroy()
    }

    ////////////////////////////////////////////////////////////////////

    private fun initNotificationLayout() {
        vb.btnSetNotificationLayout.setOnClickListener {
            val text: Editable = vb.etNotificationLayout.getText()
            if (TextUtils.isEmpty(text)) {
                "内容为空".showToast()
                return@setOnClickListener
            }
            val builderId = text.toString().toInt()
            val notificationLayout = NotificationLayout()
                .setLayoutId(R.layout.custom_notification_layout)
                .setIconViewId(R.id.iv_notification_icon)
                .setIconResourceId(R.drawable.mtpush_notification_icon)
                .setTitleViewId(R.id.tv_notification_title)
                .setContentViewId(R.id.tv_notification_content)
//                .setTimeViewId(R.id.tv_notification_time)
            UtilKMTPushPrivatesApi.setNotificationLayout(this.applicationContext, builderId, notificationLayout)
        }
        vb.btnResetNotificationLayout.setOnClickListener {
            try {
                val texts: Editable = vb.etNotificationLayout.getText()
                if (TextUtils.isEmpty(texts)) {
                    "内容为空".showToast()
                    return@setOnClickListener
                }
                val resetNotificationLayoutId = texts.toString().toInt()
                UtilKMTPushPrivatesApi.resetNotificationLayout(this.applicationContext, resetNotificationLayoutId)
            } catch (e: Exception) {
                "请设置正确的自定义通知布局id".showToast()
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
            UtilKMTPushPrivatesApi.setNotificationSilenceTime(this.applicationContext, silenceBeginHour, silenceBeginMinute, silenceEndHour, silenceEndMinute)
        }
        vb.btnResetSilenceTime.setOnClickListener {
            UtilKMTPushPrivatesApi.resetNotificationSilenceTime(this.applicationContext)
        }
    }

    private fun initShowTime() {
        vb.btnSetShowTime.setOnClickListener{
            getShowTime()
            UtilKMTPushPrivatesApi.setNotificationShowTime(this.applicationContext, showBeginHour, showEndHour, showWeekDay)
        }
        vb.btnResetShowTime.setOnClickListener {
            UtilKMTPushPrivatesApi.resetNotificationShowTime(this.applicationContext)
        }
    }

    private fun initTagAlias() {
        vb.btnAddTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                UtilKMTPushPrivatesApi.addTag(this, CPushKEngagelabCons.TAG_ADD, tag)
            } else {
                "tag is empty, can't add tag".showToast()
            }
        }
        vb.btnDeleteTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                UtilKMTPushPrivatesApi.deleteTag(this, CPushKEngagelabCons.TAG_DELETE, tag)
            } else {
                "tag is empty, can't delete tag".showToast()
            }
        }
        vb.btnUpdateTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                UtilKMTPushPrivatesApi.updateTag(this, CPushKEngagelabCons.TAG_UPDATE, tag)
            } else {
                "tag is empty, can't update tag".showToast()
            }
        }
        vb.btnQueryTag.setOnClickListener {
            tag = vb.etTag.getText().toString()
            if (!TextUtils.isEmpty(tag)) {
                UtilKMTPushPrivatesApi.queryTag(this, CPushKEngagelabCons.TAG_QUERY, tag)
            } else {
                "tag is empty, can't query tag".showToast()
            }
        }
        vb.btnDeleteAllTag.setOnClickListener {
            UtilKMTPushPrivatesApi.deleteAllTag(this, CPushKEngagelabCons.TAG_DELETE_ALL)
        }
        vb.btnQueryAllTag.setOnClickListener {
            UtilKMTPushPrivatesApi.queryAllTag(this, CPushKEngagelabCons.TAG_QUERY_ALL)
        }
        vb.btnSetAlias.setOnClickListener {
            alias = vb.etAlias.getText().toString()
        }
        vb.btnGetAlias.setOnClickListener{
            alias = vb.etAlias.getText().toString()
            UtilKMTPushPrivatesApi.getAlias(this, CPushKEngagelabCons.ALIAS_GET)
        }
        vb.btnClearAlias.setOnClickListener{
            UtilKMTPushPrivatesApi.clearAlias(this, CPushKEngagelabCons.ALIAS_CLEAR)
        }
    }

    private fun initState() {
        vb.switchNotification.setChecked(PushKEngagelab.instance.isNotificationEnable)
        vb.switchConnect.setChecked(PushKEngagelab.instance.isConnectEnable)
        vb.switchGeofence.setChecked(PushKEngagelab.instance.isConnectEnable)

        vb.btnGo2NotificationSetting.setOnClickListener {
            UtilKMTPushPrivatesApi.goToAppNotificationSettings(this)
        }
        vb.btnTurnOnPush.setOnClickListener {
            UtilKMTPushPrivatesApi.turnOnPush(this)
        }
        vb.btnTurnOffPush.setOnClickListener {
            UtilKMTPushPrivatesApi.turnOffPush(this)
        }
        vb.btnTurnOnGeofence.setOnClickListener {
            UtilKMTPushPrivatesApi.turnOnGeofenceSwitch(this)
            vb.switchGeofence.setChecked(true)
        }
        vb.btnTurnOffGeofence.setOnClickListener {
            UtilKMTPushPrivatesApi.turnOffGeofenceSwitch(this)
            vb.switchGeofence.setChecked(false)
        }
    }

    override fun onConnectStatus(context: Context, enable: Boolean, registrationId: String?) {
        vb.switchNotification.setChecked(enable)
    }

    override fun onNotificationStatus(context: Context, enable: Boolean) {
        vb.switchConnect.setChecked(enable)
    }
}