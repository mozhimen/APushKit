# 快速测试自定义消息
最新更新：2023-02-28
## 后台下发自定义消息
>下发合集：电脑上下载 postMan 然后软件左上方 import-link：https://www.getpostman.com/collections/618d2460851f697dc22c

- 自定义消息格式如下：
```
curl --location --request POST 'http://pricloud-master-api.glqas.mtpushoa.com/v3/push' \
--header 'Authorization: Basic MWM0Yjc0OWExN2Y2YWNhMzM5NjBhNTYwOjQ5ZjlkODI2MDExMmI0YmVlMDVhMzQzOA==' \
--header 'Content-Type: application/json' \
--data-raw 
'{
    "platform": "android",
    "audience": {
        "registration_id": [
            "1a0018970ab4981bc47"
        ]
    },
    "message": {
        "content_type": "text",
        "title": "custom_title",
        "msg_content": "custom_content",
        "extras": {
            "key": "value"
        }
    }
}'
```
- 参数说明

|参数|类型|定义|
|:--|:--|:--|
|content_type|String|可选，自定义内容类型|
|title|String|可选，自定义标题|
|sg_content|String|必须，自定义内容|
|extras|JsonObject|可选，自定义额外字段，key/value 格式均为 String|

***

# 快速测试通知消息
最新更新：2023-02-28

## 后台下发通知消息
>下发合集：电脑上下载 postMan 然后软件左上方 import-link：https://www.getpostman.com/collections/618d2460851f697dc22c

通知格式如下：

```
curl --location --request POST 'http://pricloud-master-api.glqas.mtpushoa.com/v3/push' \
--header 'Authorization: Basic MWM0Yjc0OWExN2Y2YWNhMzM5NjBhNTYwOjQ5ZjlkODI2MDExMmI0YmVlMDVhMzQzOA==' \
--header 'Content-Type: application/json' \
--data-raw 
'{
    "platform": "android",
    "audience": {
        "registration_id": [
            "1104a89792bbb659ec5"
        ]
    },
    "notification": {
        "android": {
            "title": "通知 title",
            "alert": "通知内容",
            "builder_id": 0,
            "category": "alarm",
            "small_icon": "mtpush_notification_icon",
            "large_icon": "mtpush_notification_icon",
            "extras": {
                "通知 key": "通知 value"
            },
            "priority": 1,
            "alert_type": 7,
            "sound": "coin",
            "channel_id": "money",
            "badge_add_num": 1,
            "badge_class": "com.engagelab.app.activity.MainActivity",
            "style": 2,
            "big_text": "党的十八大提出，倡导富强、民主、文明、和谐，倡导自由、平等、公正、法治，倡导爱国、敬业、诚信、友善，积极培育和践行社会主义核心价值观。富强、民主、文明、和谐是国家层面的价值目标，自由、平等、公正、法治是社会层面的价值取向，爱国、敬业、诚信、友善是公民个人层面的价值准则，这 24 个字是社会主义核心价值观的基本内容。",
            "inbox": {
                "inbox1": "this is inbox one",
                "inbox2": "this is inbox two",
                "inbox3": "this is inbox three"
            },
            "big_pic_path": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=96071541,1913562332&fm=26&gp=0.jpg",
            "intent": {
                "url": "intent:#Intent;component=com.engagelab.oaapp/com.engagelab.app.component.UserActivity400;end"
            }
        }
    },
    "options": {
        "third_party_channel": {
            "vivo": {
                "classification": 1,
                "pushMode": 1
            }
        }
    }
}'
```

- 参数说明

|属性|支持通道|类型|含义|
|:--|:--|:--|:--|
|title|所有|String|可选，标题|
|alert|所有|String|必选，内容|
|builder_id	|仅 Engagelab	|int	|可选，自定义样式 id，默认使用系统样式|
|category|	仅 Engagelab	|String	|可选，通知类型，用于通知栏的排序或者过滤。种类繁多，类似 Notification.CATEGORY_ALARM、Notification#CATEGORY_CALL|
|large_icon|	仅 Engagelab	|String	|可选，大图标，一般位于通知右边|
|extras	|所有	|JsonObject|	可选，额外字段，key/value 格式均为 String|
|priority|	仅 Engagelab|	int|	可选，通知的紧急程度，默认值为 0。低级，通知没有 led 灯、震动和铃声，Notification.PRIORITY_LOW：-1 普通，通知可以 led 灯、震动和铃声，Notification.PRIORITY_DEFAULT：0 高级，通知可以 led 灯、震动和铃声，还可以悬浮窗弹出，Notification.PRIORITY_HIGH：1|
|alert_type	|仅 Engagelab	|int	|可选，通知用于铃声、震动、led 灯，三种场景可自由组合，Android 8.0 开始此属性跟随 channel。例如为 7 时，代表铃声、震动、led 灯都有，默认值为 Notification.DEFAULT_ALL：-1。铃声，Notification.DEFAULT_SOUND：1震动，Notification.DEFAULT_VIBRATE：2 led 灯，Notification.DEFAULT_LIGHTS：4|
|sound|	Engagelab/google/xiaomi|	String	|可选，通知附带的铃声，需要提前在 res/raw 目录存在铃声文件。Android8.0 开始，铃声跟随 channel，如果通知使用的 channel 没有设置铃声，此通知不会对自定义铃声生效。建议提前将铃声设置在 channel 中，后续下发通知时的 sound 和 channelId 中的铃声保持一致，即可通知铃声效果保持一致性。|
|channel_id|	Engagelab/google/huawei/xiaomi /oppo|	String|	可选，从 Android8.0 开始需要，如果没有提前设置 sdk 会帮忙设置（仅限 Engagelab 通道和应用在前台时的 google 通道）。|
|badge_add_num	|Engagelab/huawei/xiaomi	|int	|可选，应用角标增加数量，累加逻辑|
|badge_class	|Engagelab/huawei/xiaomi	|String|	可选，应用 MainActivity，用于角标展示|
|style	|仅 Engagelab	|int	|可选，通知风格，默认值为 0大文本风格OTIFICATION_STYLE_BIG_TEXT：1收件箱风格NOTIFICATION_STYLE_INBOX：2大图片风格NOTIFICATION_STYLE_BIG_PICTURE：3|
|big_text	|仅 Engagelab	|String	|可选，大文本，style 为 NOTIFICATION_STYLE_BIG_TEXT 生效|
|inbox|	仅 Engagelab	|JsonObject	|可选，收件箱，style 为 NOTIFICATION_STYLE_INBOX 生效|
|big_pic_path|	仅 Engagelab	|String|	可选，大图片，style 为 NOTIFICATION_STYLE_BIG_PICTURE 生效|
|Intent:url|	所有|	String	|可选，MTPush4.0.0 开始生效，点击通知（Engagelab+所有厂商）之后的跳转，目前仅支持 activity，activity 跳转使用Intent.toURI()转换得到|
在 Android 客户端配置 activity 的示例代码：

```
package com.engagelab.app.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.engagelab.app.R;
import com.engagelab.app.log.ExampleLogger;
import com.engagelab.privates.push.api.MTPushPrivatesApi;
import com.engagelab.privates.push.api.NotificationMessage;
import com.engagelab.privates.push.constants.MTPushConstants;

/**
 * 用于演示点击通知后 activity 跳转
 *
 * 确保没有调用{@link MTPushPrivatesApi#configOldPushVersion(Context)}，否则通知点击跳转不会跳转到此页面
 * <p>
 * 不需要调用{@link MTPushPrivatesApi#reportNotificationOpened(Context, String, byte, String)}，sdk 内部已做处理
 */
public class UserActivity400 extends Activity {

    private static final String TAG = "UserActivity400";

    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        tvMessage = findViewById(R.id.tv_message);
        onIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onIntent(intent);
    }

    private void onIntent(Intent intent) {
        try {
            Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
            if (intent == null) {
                return;
            }
            Bundle bundle = intent.getExtras();
            NotificationMessage notificationMessage = bundle.getParcelable("message");
            if (notificationMessage == null) {
                return;
            }
            ExampleLogger.d(TAG, "notificationMessage:" + notificationMessage.toString());
            tvMessage.setText(notificationMessage.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}

```
```
          
  <!-- 用于演示通知点击跳转 -->
  <activity
      android:name="com.engagelab.app.component.UserActivity400"
      android:exported="false"
      android:launchMode="singleTask" />
  <!--白名单本地中转验证：配置 ENGAGELAB_PRIVATES_TRANSFER 后，sdk 会做本地验证；不配置就不做本地验证。如果开启本地验证，请将要跳转的目标 Activity 配置在这里；如果有多个目标 Activity, 请用/分割开。-->
  <meta-data
      android:name="ENGAGELAB_PRIVATES_TRANSFER"
      android:value="com.engagelab.app.component.UserActivity400" />
```
        
# SDK FAQ
最新更新：2023-03-31
## 初始化没有成功？
设置debug，查看log，看看是否有错误日志。
查看继承MTCommonService的service是否配置了进程，如：
```
<service
    android:name="com.engagelab.app.component.UserService"
    android:exported="false"
    android:process="${ENGAGELAB_PRIVATES_PROCESS}">
    <intent-filter>
        <action android:name="com.engagelab.privates.intent.USER_SERVICE" />
    </intent-filter>
</service>
``` 
查看appkey和包名是否匹配。
## 如何离线也能收到推送信息？
集成厂商sdk
在官网上配置对应app的集成设置的厂商集成信息
并且有运行过app，即，上报过厂商token
## 为何厂商收不到推送信息？
查看官网上推送记录详情，可查看到具体原因
查看是否是厂商在不同环境下有不同的限制
## 信息为何不显示通知栏？
查看手机 通知展示相关问题 进行排查
## 第三方系统收不到推送的消息？
由于第三方 ROM 的管理软件需要用户手动操作

## 小米【 MIUI 】
自启动管理：需要把应用加到【自启动管理】列表，否则杀进程或重新开机后进程无法开启
通知栏设置：应用默认都是显示通知栏通知，如果关闭，则收到通知也不会提示
网络助手：可以手动禁止已安装的第三方程序访问 2G/3G 和 WIFI 的网络和设置以后新安装程序是否允许访问 2G/3G 和 WIFI 的网络
MIUI 7 神隐模式： 允许应用进行自定义配置模式，应用在后台保持联网可用，否则应用进入后台时，应用无法正常接收消息。【设置】下电量和性能中【神隐模式】
## 华为【 Emotion 】
自启动管理：需要把应用加到【自启动管理】列表，否则杀进程或重新开机后进程不会开启，只能手动开启应用
后台应用保护：需要手动把应用加到此列表，否则设备进入睡眠后会自动杀掉应用进程，只有手动开启应用才能恢复运行
通知管理：应用状态有三种：提示、允许、禁止。禁止应用则通知栏不会有任何提醒
## 魅族【 Flyme 】
自启动管理：需要把应用加到【自启动管理】列表，否则杀进程或重新开机后进程无法开启
通知栏推送：关闭应用通知则收到消息不会有任何展示
省电管理： 安全中心里设置省电模式，在【待机耗电管理】中允许应用待机时，保持允许，否则手机休眠或者应用闲置一段时间，无法正常接收消息。
## VIVO【 Funtouch OS 】
内存一键清理：需要将应用加入【白名单】列表，否则系统自带的“一键加速”，会杀掉进程
自启动管理：需要将应用加入 “i 管家”中的【自启动管理】列表，否则重启手机后进程不会自启。但强制手动杀进程，即使加了这个列表中，后续进程也无法自启动。
## OPPO【 ColorOS 】
冻结应用管理：需要将应用加入纯净后台，否则锁屏状态下无法及时收到消息
自启动管理：将应用加入【自启动管理】列表的同时，还需要到设置-应用程序-正在运行里
## 三星
记忆体一键优化：需要将应用加入【白名单】列表，否则系统记忆体优化后，会杀掉应用进程。
