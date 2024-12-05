package com.mozhimen.pushk.engagelab.test.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import com.engagelab.privates.push.api.MTPushPrivatesApi;
import com.mozhimen.pushk.engagelab.test.R;
import com.mozhimen.pushk.engagelab.test.log.ExampleLogger;

/**
 * 用于演示MTPush4.0.0之前-点击通知后activity跳转
 * <p>
 * 确保已调用{@link MTPushPrivatesApi#configOldPushVersion(Context)}，否则通知点击跳转不会跳转到此页面
 * <p>
 * 请调用{@link MTPushPrivatesApi#reportNotificationOpened(Context, String, byte, String)}，上报通知点击打开activity，确保后台能统计
 */
public class UserActivity39X extends Activity {

    private static final String TAG = "UserActivity39X";

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
            Toast.makeText(this.getApplicationContext(), TAG, Toast.LENGTH_SHORT).show();
            if (intent == null) {
                return;
            }
            Bundle bundle = intent.getExtras();
            String platformMessage = "";
            // huawei
            if (intent.getData() != null) {
                platformMessage = intent.getData().toString();
            }
            // 其他厂商
            if (TextUtils.isEmpty(platformMessage) && intent.getExtras() != null) {
                if (bundle.containsKey("JMessageExtra")) {
                    platformMessage = bundle.getString("JMessageExtra");
                }else {
                    platformMessage = bundle.getString("MTMessageExtra");
                }
            }
            if (TextUtils.isEmpty(platformMessage)) {
                return;
            }
            JSONObject messageJson = new JSONObject(platformMessage);
            ExampleLogger.d(TAG, "notificationMessage:" + ExampleLogger.toLogString(messageJson));
            tvMessage.setText(toLogString(messageJson));
            // 解析
            String messageId = messageJson.optString("msg_id");
            byte platform = (byte) messageJson.optInt("rom_type");
            String title = messageJson.optString("n_title");
            String content = messageJson.optString("n_content");
            // 上报通知点击activity打开，建议所有厂商跳转都加上，仅MTPush4.0.0以下版本需要
            MTPushPrivatesApi.reportNotificationOpened(this, messageId, platform, "");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            ExampleLogger.w(TAG, "onIntent failed " + throwable.getMessage());
        }
    }

    public static String toLogString(JSONObject json) {
        if (json == null) {
            return "null";
        }
        try {
            String ret = json.toString(2);
            return System.getProperty("line.separator") + ret;
        } catch (Throwable throwable) {
            return json.toString();
        }
    }

}
