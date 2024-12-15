package com.mozhimen.pushk.engagelab.cons

/**
 * @ClassName CBuild
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
object CBuild {
    const val ManifestPlaceholders = """
plugins {
    id 'com.android.application'
}

android {
    ...

    defaultConfig {
        // app packageName，需要与控制台上的一样
        applicationId "com.engagelab.app"
        ...
                
        manifestPlaceholders = [
                //appKey，需要与控制台上的一样，与packageName是一对一关系
                ENGAGELAB_PRIVATES_APPKEY : "您的appkey",
                // Engagelab appChannel，用于渠道统计
                ENGAGELAB_PRIVATES_CHANNEL: "developer",
                // Engagelab process，Engagelabsdk工作所在的进程，注意:开头
                ENGAGELAB_PRIVATES_PROCESS: ":remote"
        ]
    }
}
    """

    const val ManifestReceiver = """
 <!-- 用于接收Engagelab业务的回调，包含了长连接状态/通知开关状态/通知消息到达/通知消息点击/通知消息删除/自定义消息/厂商token回调 -->
<!-- 不需要配置子进程，此回调中在主进程中，方便业务操作 -->
<receiver
    android:name=".impls.PushKEngagelabReceiver"
    android:exported="false">
    <intent-filter>
        <action android:name="com.engagelab.privates.intent.USER_RECEIVER" />
    </intent-filter>
</receiver>  
    """
}