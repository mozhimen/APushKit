package com.mozhimen.pushk.engagelab.utils

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.Intent
import com.engagelab.privates.push.api.MTPushPrivatesApi
import com.engagelab.privates.push.api.NotificationLayout
import com.engagelab.privates.push.api.NotificationMessage
import com.mozhimen.kotlin.utilk.android.content.intent2strURI
import com.mozhimen.kotlin.utilk.android.os.UtilKBuildVersion
import com.mozhimen.kotlin.utilk.bases.BaseUtilK


/**
 * @ClassName UtilKNotificationMessage
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
object UtilKNotificationMessage : BaseUtilK() {
    /**
     * 低级通知
     * 没有铃声/震动/led灯
     * 仅在通知栏展示通知消息
     */
    @JvmStatic
    fun get_PRIORITY_LOW(messageId: String, title: String, content: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
            .setPriority(Notification.PRIORITY_LOW)

    /**
     * 普通通知
     * 有铃声/震动/led灯，可通过defaultProperties配置铃声/震动/led灯
     * 在通知栏展示通知消息
     * 在状态栏展示通知小图标
     */
    @JvmStatic
    fun get_PRIORITY_DEFAULT(messageId: String, title: String, content: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content) // 这里演示仅铃声和震动生效
//            .setDefaultProperties(Notification.DEFAULT_SOUND | Notification . DEFAULT_VIBRATE)
            .setPriority(Notification.PRIORITY_DEFAULT)

    /**
     * 高级通知
     * 有铃声/震动/led灯，可通过defaultProperties配置铃声/震动/led灯
     * 在通知栏展示通知消息
     * 在状态栏展示通知小图标
     * 设置应用允许悬浮窗后，可在屏幕顶部悬浮窗弹出
     */
    @JvmStatic
    fun get_PRIORITY_HIGH(messageId: String, title: String, content: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content) // 这里演示铃声/震动/led灯全部生效
//        .setDefaultProperties(Notification.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)

    /**
     * 大文本风格通知
     */
    @JvmStatic
    fun get_NOTIFICATION_STYLE_BIG_TEXT(messageId: String, title: String, content: String, bigTxt: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
            .setStyle(NotificationMessage.NOTIFICATION_STYLE_BIG_TEXT)
            .setBigText(bigTxt);

    /**
     * 收件箱风格通知
     */
    @JvmStatic
    fun get_NOTIFICATION_STYLE_INBOX(messageId: String, title: String, content: String, strs: Array<String>): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
//            .setStyle(MTPushPrivatesApi.NOTIFICATION_STYLE_INBOX)
            .setInbox(strs);

    /**
     * 大图片风格通知
     */
    @JvmStatic
    fun get_NOTIFICATION_STYLE_BIG_PICTURE(messageId: String, title: String, content: String, picUrl: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
//            .setStyle(MTPushPrivatesApi.NOTIFICATION_STYLE_BIG_PICTURE)
            .setBigPicture(picUrl)

    /**
     * 自定义角标通知
     * 仅支持华为/荣耀/小米
     * 小米设备上，删除通知，角标数量自动减1
     * 华为/荣耀设备上，删除通知，角标数量不会自动减1，需要调用MTPushPrivatesApi.setNotificationBadge(context,number)设置最终角标数量
     */
    @JvmStatic
    fun get_badge(messageId: String, title: String, content: String, badgeNum: Int): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
            // 累加逻辑，这里演示角标数量+1
            .setBadge(badgeNum)

    /**
     * 自定义铃声通知
     * 需要提前在res/raw/下放好对应铃声，这里以"coin.mp3"举例
     * Android8.0开始，铃声在channel中设置
     */
    @JvmStatic
    fun get_sound(messageId: String, title: String, content: String, rawName: String): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
            .setSound(rawName)

    /**
     * 自定义channel通知
     */
    @JvmStatic
    fun get_channel(messageId: String, title: String, content: String, notificationChannel: NotificationChannel): NotificationMessage =
        NotificationMessage()
            .setMessageId(messageId)
            .setTitle(title)
            .setContent(content)
            .setChannelId(if (UtilKBuildVersion.isAfterV_26_8_O()) notificationChannel.id else "")

//    // 演示如何创建一个channel，普通级别，附带铃声coin
//    fun buildChannel(context: Context, channelId: String, channelName: String, description: String): NotificationChannel? {
//        try {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//                return null
//            }
//            val notificationManager = UtilKNotificationManager.get(context)
//            var notificationChannel = UtilKNotificationChannel.get(notificationManager, channelId)
//            if (notificationChannel == null) {
//                notificationChannel = UtilKNotificationChannel.get(channelId, channelName, CNotificationManager.IMPORTANCE_DEFAULT)
//                notificationChannel.description = description
//                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
//                notificationChannel.setSound(UtilKStrUri.getStrUri_ofRaw(context, "coin").strUri2uri(), Notification.AUDIO_ATTRIBUTES_DEFAULT)
//                notificationManager.createNotificationChannel(notificationChannel)
//            } else {
//                Log.d(TAG, "buildChannel: [$channelId] is already exists")
//            }
//            return notificationChannel
//        } catch (e: Throwable) {
//            e.printStackTrace()
//        }
//        return null
//    }

    /**
     * 自定义布局通知
     */
    @JvmStatic
    fun get_layout(messageId: String, title: String, content: String, context: Context, notificationLayout: NotificationLayout, builderId: Int): NotificationMessage {
        MTPushPrivatesApi.setNotificationLayout(context, builderId, notificationLayout)// 这里定义一个常量，代表自定义布局的builderId
        return NotificationMessage()// 之后发送自定义布局通知
            .setMessageId(messageId)
            .setBuilderId(builderId)
            .setTitle(title)
            .setContent(content)
    }

    @JvmStatic
    fun get_intent(messageId: String, title: String, intent: Intent): NotificationMessage {
//        val intent = UtilKIntent.get()// 首先获取intentUri
//        intent.setClassName(this.getPackageName(), IntentActivity.class. getCanonicalName ());
//        intent.setAction(ExampleGlobal.ACTION_INTENT_NOTIFICATION);
//        Bundle bundle = new Bundle();
//        bundle.putString("description", "this is a intent notification");
//        bundle.putString("form", MainActivity.class. getSimpleName ());
//        bundle.putString("to", IntentActivity.class. getSimpleName ());
//        intent.putExtras(bundle);
        return NotificationMessage()// 之后发送自定义跳转通知
            .setMessageId(messageId)
            .setTitle(title)
//            .setContent(content)
            .setContent(intent.intent2strURI())
    }
}