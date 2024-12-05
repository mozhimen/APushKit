package com.engagelab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.engagelab.privates.common.log.MTCommonLog;

import com.engagelab.app.R;
import com.engagelab.app.listener.OnStatusListener;
import com.engagelab.app.listener.StatusObserver;
import com.engagelab.app.utils.ExampleUtil;
import com.engagelab.privates.core.api.MTCorePrivatesApi;

/**
 * 主要用于初始化
 */
public class MainActivity extends Activity implements View.OnClickListener, OnStatusListener {

    private static final String TAG = "MainActivity";
    private TextView tvUid;
    private TextView tvRid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvUid.setText(MTCorePrivatesApi.getUserId(this.getApplicationContext()) + "");
        tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.getApplicationContext()));
        // 监听状态变化
        StatusObserver.getInstance().addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 监听移除
        StatusObserver.getInstance().removeListener();
    }

    private void initView() {
        findViewById(R.id.btn_push).setOnClickListener(this);

        TextView tvPackageName = findViewById(R.id.tv_package_name);
        TextView tvAppKey = findViewById(R.id.tv_app_key);
        TextView tvAppChannel = findViewById(R.id.tv_app_channel);
        TextView tvAppProcess = findViewById(R.id.tv_app_process);
        TextView tvUserService = findViewById(R.id.tv_user_service);
        TextView tvUserReceiver = findViewById(R.id.tv_user_receiver);
        tvUid = findViewById(R.id.tv_uid);
        tvRid = findViewById(R.id.tv_rid);

        tvPackageName.setText(this.getApplicationContext().getPackageName());
        tvAppKey.setText(ExampleUtil.getAppKey(this.getApplicationContext()));
        tvAppChannel.setText(ExampleUtil.getAppChannel(this.getApplicationContext()));
        tvAppProcess.setText(ExampleUtil.getAppProcess(this.getApplicationContext()));
        tvUserService.setText(ExampleUtil.getCommonServiceName(this.getApplicationContext()));
        tvUserReceiver.setText(ExampleUtil.getCommonReceiverName(this.getApplicationContext()));
    }

    @Override
    public void onNotificationStatus(boolean status) {
        // do nothing
    }

    @Override
    public void onConnectStatus(boolean status) {
        if (status) {
            tvUid.setText(MTCorePrivatesApi.getUserId(this.getApplicationContext()) + "");
            tvRid.setText(MTCorePrivatesApi.getRegistrationId(this.getApplicationContext()));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_push){
            if (TextUtils.isEmpty(ExampleUtil.getCommonServiceName(this.getApplicationContext()))) {
                MTCommonLog.e(TAG, "userService is null, please create new Service extends MTCommonService");
                Toast.makeText(this.getApplicationContext(), "userService is null, please create new Service extends MTCommonService", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(ExampleUtil.getCommonReceiverName(this.getApplicationContext()))) {
                MTCommonLog.e(TAG, "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver");
                Toast.makeText(this.getApplicationContext(), "userReceiver is null, please create new BroadcastReceiver extends MTCommonReceiver", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent pushIntent = new Intent(this, PushActivity.class);
            startActivity(pushIntent);
        }
    }
}