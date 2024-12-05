package com.mozhimen.pushk.engagelab.impls

import android.content.Context
import android.widget.Toast
import com.engagelab.privates.common.component.MTCommonReceiver
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.push.api.CustomMessage
import com.engagelab.privates.push.api.NotificationMessage
import com.engagelab.privates.push.api.PlatformTokenMessage
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.commons.IUtilK


/**
 * @ClassName PushKEngagelabReceiver
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
/**
 * 开发者继承MTCommonReceiver，可以获得sdk的方法回调
 *
 *
 * 所有回调均在主线程
 */
open class PushKEngagelabReceiver : MTCommonReceiver(), IUtilK {
    /**
     * 应用通知开关状态回调
     * @param context 不为空
     * @param enable  通知开关是否开，true为打开，false为关闭
     */
    override fun onNotificationStatus(context: Context, enable: Boolean) {
        UtilKLogWrapper.i(TAG, "onNotificationStatus: $enable")
        Toast.makeText(context.applicationContext, "onNotificationStatus $enable", Toast.LENGTH_SHORT).show()
        ExampleGlobal.isNotificationEnable = enable
        if (StatusObserver.getInstance().getListener() != null) {
            StatusObserver.getInstance().getListener().onNotificationStatus(enable)
        }
    }

    /**
     * 长连接状态回调
     * @param context 不为空
     * @param enable  是否连接
     */
    override fun onConnectStatus(context: Context, enable: Boolean) {
        UtilKLogWrapper.i(TAG, "onConnectState: $enable")
        Toast.makeText(context.applicationContext, "onConnectStatus $enable", Toast.LENGTH_SHORT).show()
        ExampleGlobal.isConnectEnable = enable
        if (StatusObserver.getInstance().getListener() != null) {
            StatusObserver.getInstance().getListener().onConnectStatus(enable)
        }
        // 长连接成功可获取registrationId
        if (enable) {
            val registrationId = MTCorePrivatesApi.getRegistrationId(context)
            UtilKLogWrapper.i(TAG, "registrationId:$registrationId")
        }
    }

    /**
     * 通知消息到达回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    override fun onNotificationArrived(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationArrived: $notificationMessage")
    }

    /**
     * 通知消息在前台不显示
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    override fun onNotificationUnShow(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationUnShow: $notificationMessage")
    }

    /**
     * 通知消息点击回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    override fun onNotificationClicked(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationClicked: $notificationMessage")
    }

    /**
     * 通知消息删除回调
     * @param context             不为空
     * @param notificationMessage 通知消息
     */
    override fun onNotificationDeleted(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationDeleted: $notificationMessage")
    }

    /**
     * 自定义消息回调
     * @param context       不为空
     * @param customMessage 自定义消息
     */
    override fun onCustomMessage(context: Context, customMessage: CustomMessage) {
        UtilKLogWrapper.i(TAG, "onCustomMessage: $customMessage")
    }

    /**
     * 厂商token消息回调
     * @param context              不为空
     * @param platformTokenMessage 厂商token消息
     */
    override fun onPlatformToken(context: Context, platformTokenMessage: PlatformTokenMessage) {
        UtilKLogWrapper.i(TAG, "onPlatformToken: $platformTokenMessage")
    }
}