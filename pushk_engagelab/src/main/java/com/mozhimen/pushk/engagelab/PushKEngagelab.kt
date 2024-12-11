package com.mozhimen.pushk.engagelab

import android.content.Context
import com.engagelab.privates.core.api.WakeMessage
import com.engagelab.privates.push.api.AliasMessage
import com.engagelab.privates.push.api.CustomMessage
import com.engagelab.privates.push.api.InAppMessage
import com.engagelab.privates.push.api.MobileNumberMessage
import com.engagelab.privates.push.api.NotificationMessage
import com.engagelab.privates.push.api.PlatformTokenMessage
import com.engagelab.privates.push.api.TagMessage
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.commons.IUtilK
import com.mozhimen.pushk.engagelab.commons.IMTCommonListener
import com.mozhimen.pushk.engagelab.cons.CPushKEngagelabCons
import java.util.concurrent.CopyOnWriteArrayList

/**
 * @ClassName PushKEngagelab
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/10
 * @Version 1.0
 */
class PushKEngagelab : IMTCommonListener, IUtilK {

    private var _iMTCommonListeners = CopyOnWriteArrayList<IMTCommonListener>()

    /////////////////////////////////////////////////////////////////////

    init {

    }

    /////////////////////////////////////////////////////////////////////

    var isNotificationEnable: Boolean = false
    var isConnectEnable: Boolean = false

    /////////////////////////////////////////////////////////////////////

    fun addMTCommonListener(listener: IMTCommonListener) {
        if (!_iMTCommonListeners.contains(listener))
            _iMTCommonListeners.add(listener)
    }

    fun removeMTCommonListener(listener: IMTCommonListener){
        if (_iMTCommonListeners.contains(listener))
            _iMTCommonListeners.remove(listener)
    }

    fun getMTCommonListeners(): List<IMTCommonListener> =
        _iMTCommonListeners

    /////////////////////////////////////////////////////////////////////

    override fun onNotificationStatus(context: Context, enable: Boolean) {
        UtilKLogWrapper.i(TAG, "onNotificationStatus: $enable")
        isNotificationEnable = enable
        getMTCommonListeners().forEach { it.onNotificationStatus(context, enable) }
    }

    override fun onConnectStatus(context: Context, enable: Boolean, registrationId: String?) {
        UtilKLogWrapper.i(TAG, "onConnectStatus: enable $enable registrationId $registrationId")
        isConnectEnable = enable
        getMTCommonListeners().forEach { it.onConnectStatus(context, enable, registrationId) }
    }

    override fun onNotificationArrived(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationArrived: $notificationMessage")
        getMTCommonListeners().forEach { it.onNotificationArrived(context, notificationMessage) }
    }

    override fun onNotificationUnShow(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationUnShow: $notificationMessage")
        getMTCommonListeners().forEach { it.onNotificationUnShow(context, notificationMessage) }
    }

    override fun onNotificationClicked(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationClicked: $notificationMessage")
        getMTCommonListeners().forEach { it.onNotificationClicked(context, notificationMessage) }
    }

    override fun onNotificationDeleted(context: Context, notificationMessage: NotificationMessage) {
        UtilKLogWrapper.i(TAG, "onNotificationDeleted: $notificationMessage")
        getMTCommonListeners().forEach { it.onNotificationDeleted(context, notificationMessage) }
    }

    override fun onCustomMessage(context: Context, customMessage: CustomMessage) {
        UtilKLogWrapper.i(TAG, "onCustomMessage: $customMessage")
//        if (getCustomMessageActivity() != null) {
//            // 用于演示自定义消息展示
//            val intent = Intent()
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.setClass(context, getCustomMessageActivity()!!)
//            intent.putExtra(CCons.EXTRA_MESSAGE, customMessage)
//            context.startActivity(intent)
//        }
        getMTCommonListeners().forEach { it.onCustomMessage(context, customMessage) }
    }

    override fun onTagMessage(context: Context, tagMessage: TagMessage) {
        val message = when (tagMessage.sequence) {
            CPushKEngagelabCons.TAG_ADD -> "addTag code:${tagMessage.code} tag:${tagMessage.tags.contentToString()}"
            CPushKEngagelabCons.TAG_DELETE -> "deleteTag code:${tagMessage.code} tag:${tagMessage.tags.contentToString()}"
            CPushKEngagelabCons.TAG_UPDATE -> "updateTag code:${tagMessage.code} tag:${tagMessage.tags.contentToString()}"
            CPushKEngagelabCons.TAG_QUERY -> "queryTag code:${tagMessage.code} tag:${tagMessage.queryTag}, valid:${tagMessage.isQueryTagValid}"
            CPushKEngagelabCons.TAG_DELETE_ALL -> "deleteAllTag code:${tagMessage.code}"
            CPushKEngagelabCons.TAG_QUERY_ALL -> "queryAllTag code:${tagMessage.code} tag:${tagMessage.tags.contentToString()}"
            else -> "code:? tag:$tagMessage"
        }
        UtilKLogWrapper.i(TAG, "onTagMessage: message $message tagMessage $tagMessage")
        getMTCommonListeners().forEach { it.onTagMessage(context, tagMessage) }
    }

    override fun onAliasMessage(context: Context, aliasMessage: AliasMessage) {
        val message = when (aliasMessage.sequence) {
            CPushKEngagelabCons.ALIAS_SET -> "setAlias code:${aliasMessage.code}, alias:${aliasMessage.alias}"
            CPushKEngagelabCons.ALIAS_GET -> "getAlias code:${aliasMessage.code}, alias:${aliasMessage.alias}"
            CPushKEngagelabCons.ALIAS_CLEAR -> "clearAlias code:${aliasMessage.code}, alias:${aliasMessage.alias}"
            else -> "code:?, alias:$aliasMessage"
        }
        UtilKLogWrapper.i(TAG, "onAliasMessage: $aliasMessage message $message")
        getMTCommonListeners().forEach { it.onAliasMessage(context, aliasMessage) }
    }

    override fun onPlatformToken(context: Context, platformTokenMessage: PlatformTokenMessage) {
        UtilKLogWrapper.i(TAG, "onPlatformToken: $platformTokenMessage")
        getMTCommonListeners().forEach { it.onPlatformToken(context, platformTokenMessage) }
    }

    override fun onMobileNumber(context: Context, mobileNumberMessage: MobileNumberMessage) {
        UtilKLogWrapper.i(TAG, "onMobileNumber: $mobileNumberMessage")
        getMTCommonListeners().forEach { it.onMobileNumber(context, mobileNumberMessage) }
    }

    override fun onWake(context: Context, wakeMessage: WakeMessage) {
        UtilKLogWrapper.i(TAG, "onWake: $wakeMessage")
        getMTCommonListeners().forEach { it.onWake(context, wakeMessage) }
    }

    override fun onInAppMessageShow(context: Context, inAppMessage: InAppMessage) {
        UtilKLogWrapper.i(TAG, "onInAppMessageShow: $inAppMessage")
        getMTCommonListeners().forEach { it.onInAppMessageShow(context, inAppMessage) }
    }

    override fun onInAppMessageClick(context: Context, inAppMessage: InAppMessage) {
        UtilKLogWrapper.i(TAG, "onInAppMessageClick: $inAppMessage")
        getMTCommonListeners().forEach { it.onInAppMessageClick(context, inAppMessage) }
    }

    /////////////////////////////////////////////////////////////////////

    companion object {
        @JvmStatic
        val instance = INSTANCE.holder
    }

    private object INSTANCE {
        val holder = PushKEngagelab()
    }
}