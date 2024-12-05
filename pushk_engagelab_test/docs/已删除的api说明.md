# 已删除的API说明

## ~~getConnectionState~~

原：获取当前连接状态

现：请开发者在以下方法中获取结果

```
public class UserReceiver extends MTCommonReceiver {

    private static final String TAG = "UserReceiver";

    /**
     * 长连接状态回调
     *
     * @param context 不为空
     * @param enable  是否连接
     */
    @Override
    public void onConnectStatus(Context context, boolean enable) {
        
    }
}    
```

## ~~isPushStopped~~

原：用来检查 Push Service 是否已经被停止

现：请开发者在以下方法中获取结果

```
public class UserReceiver extends MTCommonReceiver {

    private static final String TAG = "UserReceiver";

    /**
     * 长连接状态回调
     *
     * @param context 不为空
     * @param enable  是否连接
     */
    @Override
    public void onConnectStatus(Context context, boolean enable) {
        
    }
}    
```

## ~~filterValidTags~~

原：过滤掉无效的 tags，得到有效的 tags

现：不提供此功能，请开发者在以下方法中获取结果

```
public class UserReceiver extends MTCommonReceiver {

    private static final String TAG = "UserReceiver";

    /**
     * 标签消息回调
     *
     * @param context    不为空
     * @param tagMessage 标签消息
     */
    @Override
    public void onTagMessage(Context context, TagMessage tagMessage) {
        
    }
}    
```

## ~~onResume~~

原：用于统计用户活跃
现：sdk使用activityLifecycle替代

## ~~onPause~~

原：用于统计用户活跃
现：sdk使用activityLifecycle替代

## ~~requestPermission~~

原：动态申请sdk所需必要权限

现：sdk无需用户敏感权限，不用动态申请权限

## ~~setPowerSaveMode~~

原：设置省电模式
现：sdk无需设置省电模式

## ~~setDefaultPushNotificationBuilder~~

原：设置通知样式

现：使用以下方法代替

```
    int builderId = 123;
    NotificationLayout notificationLayout = new NotificationLayout()
            .setLayoutId(R.layout.custom_notification_layout)
            .setIconViewId(R.id.iv_notification_icon)
            .setIconResourceId(R.drawable.mtpush_notification_icon)
            .setTitleViewId(R.id.tv_notification_title)
            .setContentViewId(R.id.tv_notification_content)
            .setTimeViewId(R.id.tv_notification_time);
    MTPushPrivatesApi.setNotificationLayout(this.getApplicationContext(), builderId, notificationLayout);
```

## ~~initCrashHandler~~

原：采集crashLog上报

现：此功能移到engagelab-option-collect包，后台下发开启采集

## ~~stopCrashHandler~~

原：采集crashLog上报

现：此功能移到engagelab-option-collect包，后台下发开启采集

## ~~addLocalNotification~~

原：本地通知

现：使用以下方法代替

```
MTPushPrivatesApi.showNotification(context,notificationMessage)
```

