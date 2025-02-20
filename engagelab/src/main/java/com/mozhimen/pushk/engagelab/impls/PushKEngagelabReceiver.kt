package com.mozhimen.pushk.engagelab.impls

import android.content.Context
import androidx.annotation.CallSuper
import com.engagelab.privates.common.component.MTCommonReceiver
import com.engagelab.privates.core.api.MTCorePrivatesApi
import com.engagelab.privates.core.api.WakeMessage
import com.engagelab.privates.push.api.AliasMessage
import com.engagelab.privates.push.api.CustomMessage
import com.engagelab.privates.push.api.InAppMessage
import com.engagelab.privates.push.api.MobileNumberMessage
import com.engagelab.privates.push.api.NotificationMessage
import com.engagelab.privates.push.api.PlatformTokenMessage
import com.engagelab.privates.push.api.TagMessage
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.pushk.engagelab.PushKEngagelab


/**
 * @ClassName PushKEngagelabReceiver
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
/**
 * 开发者继承MTCommonReceiver，可以获得sdk的方法回调
 * 所有回调均在主线程
 *
 * <!-- 用于接收Engagelab业务的回调，包含了长连接状态/通知开关状态/通知消息到达/通知消息点击/通知消息删除/自定义消息/厂商token回调 -->
 * <!-- 不需要配置子进程，此回调中在主进程中，方便业务操作 -->
 * <receiver
 *     android:name=".impls.PushKEngagelabReceiver"
 *     android:exported="false">
 *     <intent-filter>
 *         <action android:name="com.engagelab.privates.intent.USER_RECEIVER" />
 *     </intent-filter>
 * </receiver>
 */
open class PushKEngagelabReceiver : MTCommonReceiver(), IUtilK {

    @CallSuper
    override fun onNotificationStatus(context: Context, enable: Boolean) {
        PushKEngagelab.instance.onNotificationStatus(context, enable)
    }

    @CallSuper
    override fun onConnectStatus(context: Context, enable: Boolean) {
        // 长连接成功可获取registrationId
        if (enable) {
            val registrationId = MTCorePrivatesApi.getRegistrationId(context)
            PushKEngagelab.instance.onConnectStatus(context, enable, registrationId)
        } else {
            PushKEngagelab.instance.onConnectStatus(context, enable, null)
        }
    }

    @CallSuper
    override fun onNotificationArrived(context: Context, notificationMessage: NotificationMessage) {
        PushKEngagelab.instance.onNotificationArrived(context, notificationMessage)
    }

    @CallSuper
    override fun onNotificationUnShow(context: Context, notificationMessage: NotificationMessage) {
        PushKEngagelab.instance.onNotificationUnShow(context, notificationMessage)
    }

    @CallSuper
    override fun onNotificationClicked(context: Context, notificationMessage: NotificationMessage) {
        PushKEngagelab.instance.onNotificationClicked(context, notificationMessage)
    }

    @CallSuper
    override fun onNotificationDeleted(context: Context, notificationMessage: NotificationMessage) {
        PushKEngagelab.instance.onNotificationDeleted(context, notificationMessage)
    }

    @CallSuper
    override fun onCustomMessage(context: Context, customMessage: CustomMessage) {
        PushKEngagelab.instance.onCustomMessage(context, customMessage)
    }

    @CallSuper
    override fun onTagMessage(context: Context, tagMessage: TagMessage) {
        PushKEngagelab.instance.onTagMessage(context, tagMessage)
    }

    @CallSuper
    override fun onAliasMessage(context: Context, aliasMessage: AliasMessage) {
        PushKEngagelab.instance.onAliasMessage(context, aliasMessage)
    }

    @CallSuper
    override fun onPlatformToken(context: Context, platformTokenMessage: PlatformTokenMessage) {
        PushKEngagelab.instance.onPlatformToken(context, platformTokenMessage)
    }

    @CallSuper
    override fun onMobileNumber(context: Context, mobileNumberMessage: MobileNumberMessage) {
        PushKEngagelab.instance.onMobileNumber(context, mobileNumberMessage)
    }

    @CallSuper
    override fun onWake(context: Context, wakeMessage: WakeMessage) {
        PushKEngagelab.instance.onWake(context, wakeMessage)
    }

    @CallSuper
    override fun onInAppMessageShow(context: Context, inAppMessage: InAppMessage) {
        PushKEngagelab.instance.onInAppMessageShow(context, inAppMessage)
    }

    @CallSuper
    override fun onInAppMessageClick(context: Context, inAppMessage: InAppMessage) {
        PushKEngagelab.instance.onInAppMessageClick(context, inAppMessage)
    }
}