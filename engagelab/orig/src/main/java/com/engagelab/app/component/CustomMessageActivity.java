package com.engagelab.app.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.engagelab.app.R;
import com.engagelab.app.log.ExampleLogger;
import com.engagelab.privates.push.api.CustomMessage;

/**
 * 用于演示自定义消息展示
 */
public class CustomMessageActivity extends Activity {

    private static final String TAG = "CustomMessageActivity";

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
            CustomMessage customMessage = intent.getParcelableExtra("message");
            if (customMessage == null) {
                return;
            }
            ExampleLogger.d(TAG, "customMessage:" + customMessage.toString());
            tvMessage.setText(customMessage.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            ExampleLogger.d(TAG, "onIntent failed:" + throwable.getMessage());
        }
    }

}
