package com.mozhimen.pushk.engagelab.test.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.engagelab.privates.push.api.MTPushPrivatesApi;
import com.engagelab.privates.push.api.NotificationMessage;
import com.mozhimen.pushk.engagelab.test.R;
import com.mozhimen.pushk.engagelab.test.log.ExampleLogger;

import org.json.JSONObject;

/**
 * 用于演示MTPush4.0.0开始-点击通知后activity跳转
 * <p>
 * 确保没有调用{@link MTPushPrivatesApi#configOldPushVersion(Context)}，否则通知点击跳转不会跳转到此页面
 * <p>
 * 不需要调用{@link MTPushPrivatesApi#reportNotificationOpened(Context, String, byte, String)}，sdk内部已做处理
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
        ExampleLogger.d(TAG, "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onIntent(intent);
        ExampleLogger.d(TAG, "onNewIntent");
    }

    private void onIntent(Intent intent) {
        try {
            Toast.makeText(getApplicationContext(), TAG, Toast.LENGTH_SHORT).show();
            if (intent == null) {
                return;
            }
            String notificationMessage = intent.getStringExtra("message_json");
            if (notificationMessage == null) {
                return;
            }
            String toLogString = ExampleLogger.toLogString(new JSONObject(notificationMessage));
            ExampleLogger.d(TAG, "notificationMessage:" + toLogString);
            tvMessage.setText(toLogString);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}