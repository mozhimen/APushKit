package com.mozhimen.pushk.engagelab.commons

import android.content.Context
import com.engagelab.privates.core.api.WakeMessage
import com.engagelab.privates.push.api.AliasMessage
import com.engagelab.privates.push.api.CustomMessage
import com.engagelab.privates.push.api.InAppMessage
import com.engagelab.privates.push.api.MobileNumberMessage
import com.engagelab.privates.push.api.NotificationMessage
import com.engagelab.privates.push.api.PlatformTokenMessage
import com.engagelab.privates.push.api.TagMessage

/**
 * @ClassName IMTCommonListener
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/10
 * @Version 1.0
 */
interface IMTCommonListener {
    /**
     * 应用通知开关状态回调
     * @param context 不为空
     * @param enable  通知开关是否开，true为打开，false为关闭
     */
    fun onNotificationStatus(context: Context, enable: Boolean) {}

    /**
     * 长连接状态回调
     * @param context 不为空
     * @param enable  是否连接
     */
    fun onConnectStatus(context: Context, enable: Boolean, registrationId: String?) {}

    /**
     * 通知消息到达回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    fun onNotificationArrived(context: Context, notificationMessage: NotificationMessage) {}

    /**
     * 通知消息在前台不显示
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    fun onNotificationUnShow(context: Context, notificationMessage: NotificationMessage) {}

    /**
     * 通知消息点击回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    fun onNotificationClicked(context: Context, notificationMessage: NotificationMessage) {}

    /**
     * 通知消息删除回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    fun onNotificationDeleted(context: Context, notificationMessage: NotificationMessage) {}

    /**
     * 自定义消息回调
     * @param context       不为空
     * @param customMessage 自定义消息
     */
    fun onCustomMessage(context: Context, customMessage: CustomMessage) {}

    /**
     * 标签消息回调
     * @param context    不为空
     * @param tagMessage 标签消息
     */
    fun onTagMessage(context: Context, tagMessage: TagMessage) {}

    /**
     * 别名消息回调
     * @param context      不为空
     * @param aliasMessage 别名消息
     */
    fun onAliasMessage(context: Context, aliasMessage: AliasMessage) {}

    /**
     * 厂商token消息回调
     * @param context              不为空
     * @param platformTokenMessage 厂商token消息
     */
    fun onPlatformToken(context: Context, platformTokenMessage: PlatformTokenMessage) {}

    /**
     * 移动号码消息回调
     * @param context             不为空
     * @param mobileNumberMessage 移动号码消息
     */
    fun onMobileNumber(context: Context, mobileNumberMessage: MobileNumberMessage) {}

    /**
     * 被拉起回调
     * @param context     不为空
     * @param wakeMessage 被拉起消息
     */
    fun onWake(context: Context, wakeMessage: WakeMessage) {}

    fun onInAppMessageShow(context: Context, inAppMessage: InAppMessage) {}

    fun onInAppMessageClick(context: Context, inAppMessage: InAppMessage) {}
}