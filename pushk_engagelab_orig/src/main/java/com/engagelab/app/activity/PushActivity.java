package com.engagelab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.engagelab.app.R;
import com.engagelab.app.common.ExampleGlobal;
import com.engagelab.app.listener.OnStatusListener;
import com.engagelab.app.listener.StatusObserver;

import com.engagelab.privates.push.api.MTPushPrivatesApi;
import com.engagelab.privates.push.api.NotificationLayout;

/**
 * 用于演示MTPush功能
 */
public class PushActivity extends Activity implements View.OnClickListener, OnStatusListener {

    private static final String TAG = "PushActivity";

    private Switch switchNotification;
    private Switch switchConnect;
    private Switch switchGeofence;

    private EditText etTag;
    private EditText etAlias;

    private String tag;
    private String alias;

    private EditText etShowStartHour;
    private EditText etShowEndHour;
    private EditText etShowWeekDay;

    private int showBeginHour;
    private int showEndHour;
    private int showWeekDay;

    private EditText etSilenceStartHour;
    private EditText etSilenceStartMinute;
    private EditText etSilenceEndHour;
    private EditText etSilenceEndMinute;

    private int silenceBeginHour;
    private int silenceBeginMinute;
    private int silenceEndHour;
    private int silenceEndMinute;

    private EditText etNotificationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        // 初始化
//        MTPushPrivatesApi.init(this);

        findViewById(R.id.btn_back).setOnClickListener(this);
//        findViewById(R.id.btn_go_keepalive).setOnClickListener(this);

        initState();
        initTagAlias();
        initShowTime();
        initSilenceTime();
        initNotificationLayout();
    }

    private void initState() {
        switchNotification = findViewById(R.id.switch_notification);
        switchConnect = findViewById(R.id.switch_connect);
        switchGeofence = findViewById(R.id.switch_geofence);

        switchNotification.setChecked(ExampleGlobal.isNotificationEnable);
        switchConnect.setChecked(ExampleGlobal.isConnectEnable);
        switchGeofence.setChecked(ExampleGlobal.isConnectEnable);

        findViewById(R.id.btn_go_2_notification_setting).setOnClickListener(this);
        findViewById(R.id.btn_turn_on_push).setOnClickListener(this);
        findViewById(R.id.btn_turn_off_push).setOnClickListener(this);

        findViewById(R.id.btn_turn_on_geofence).setOnClickListener(this);
        findViewById(R.id.btn_turn_off_geofence).setOnClickListener(this);

        StatusObserver.getInstance().addListener(this);
    }

    private void initTagAlias() {
        etTag = findViewById(R.id.et_tag);
        findViewById(R.id.btn_addTag).setOnClickListener(this);
        findViewById(R.id.btn_deleteTag).setOnClickListener(this);
        findViewById(R.id.btn_updateTag).setOnClickListener(this);
        findViewById(R.id.btn_queryTag).setOnClickListener(this);
        findViewById(R.id.btn_delete_all_tag).setOnClickListener(this);
        findViewById(R.id.btn_query_all_tag).setOnClickListener(this);

        etAlias = findViewById(R.id.et_alias);
        findViewById(R.id.btn_setAlias).setOnClickListener(this);
        findViewById(R.id.btn_getAlias).setOnClickListener(this);
        findViewById(R.id.btn_clearAlias).setOnClickListener(this);
    }

    private void initShowTime() {
        etShowStartHour = findViewById(R.id.et_push_start_hour);

        etShowEndHour = findViewById(R.id.et_push_end_hour);

        etShowWeekDay = findViewById(R.id.et_push_week);

        findViewById(R.id.btn_set_show_time).setOnClickListener(this);
        findViewById(R.id.btn_reset_show_time).setOnClickListener(this);
    }

    private void initSilenceTime() {
        etSilenceStartHour = findViewById(R.id.et_silence_start_hour);
        etSilenceStartMinute = findViewById(R.id.et_silence_start_minute);
        etSilenceEndHour = findViewById(R.id.et_silence_end_hour);
        etSilenceEndMinute = findViewById(R.id.et_silence_end_minute);

        findViewById(R.id.btn_set_silence_time).setOnClickListener(this);
        findViewById(R.id.btn_reset_silence_time).setOnClickListener(this);
    }

    private void initNotificationLayout() {
        etNotificationLayout = findViewById(R.id.et_notification_layout);

        findViewById(R.id.btn_set_notification_layout).setOnClickListener(this);
        findViewById(R.id.btn_reset_notification_layout).setOnClickListener(this);
    }


    @Override
    public void onNotificationStatus(boolean enable) {
        if (switchNotification != null) {
            switchNotification.setChecked(enable);
        }
    }

    @Override
    public void onConnectStatus(boolean enable) {
        if (switchConnect != null) {
            switchConnect.setChecked(enable);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_go_2_notification_setting){
            MTPushPrivatesApi.goToAppNotificationSettings(this);
        }else if (view.getId()==R.id.btn_turn_on_push){
            MTPushPrivatesApi.turnOnPush(this);
        }else if (view.getId()==R.id.btn_turn_off_push){
            MTPushPrivatesApi.turnOffPush(this);
        }else if (view.getId()==R.id.btn_turn_on_geofence){
            MTPushPrivatesApi.turnOnGeofenceSwitch(this);
            switchGeofence.setChecked(true);
        }else if (view.getId()==R.id.btn_turn_off_geofence){
            MTPushPrivatesApi.turnOffGeofenceSwitch(this);
            switchGeofence.setChecked(false);
        }/*else if (view.getId()==R.id.btn_go_keepalive){
            //演示保活功能
////                Intent keepaliveIntent = new Intent(this, KeepaliveActivity.class);
////                startActivity(keepaliveIntent);
        }*/
        else if (view.getId()==R.id.btn_addTag){
            tag = etTag.getText().toString();
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.addTag(this, ExampleGlobal.TAG_ADD, tag);
            } else {
                Toast.makeText(this.getApplicationContext(), "tag is empty, can't add tag", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btn_deleteTag){
            tag = etTag.getText().toString();
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.deleteTag(this, ExampleGlobal.TAG_DELETE, tag);
            } else {
                Toast.makeText(this.getApplicationContext(), "tag is empty, can't delete tag", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btn_updateTag){
            tag = etTag.getText().toString();
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.updateTag(this, ExampleGlobal.TAG_UPDATE, tag);
            } else {
                Toast.makeText(this.getApplicationContext(), "tag is empty, can't update tag", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btn_queryTag){
            tag = etTag.getText().toString();
            if (!TextUtils.isEmpty(tag)) {
                MTPushPrivatesApi.queryTag(this, ExampleGlobal.TAG_QUERY, tag);
            } else {
                Toast.makeText(this.getApplicationContext(), "tag is empty, can't query tag", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btn_delete_all_tag){
            MTPushPrivatesApi.deleteAllTag(this, ExampleGlobal.TAG_DELETE_ALL);
        }else if (view.getId()==R.id.btn_query_all_tag){
            MTPushPrivatesApi.queryAllTag(this, ExampleGlobal.TAG_QUERY_ALL);
        }else if (view.getId()==R.id.btn_setAlias){
            alias = etAlias.getText().toString();
            MTPushPrivatesApi.setAlias(this, ExampleGlobal.ALIAS_SET, alias);
        }else if (view.getId()==R.id.btn_getAlias){
            alias = etAlias.getText().toString();
            MTPushPrivatesApi.getAlias(this, ExampleGlobal.ALIAS_GET);
        }else if (view.getId()==R.id.btn_clearAlias){
            MTPushPrivatesApi.clearAlias(this, ExampleGlobal.ALIAS_CLEAR);
        }else if (view.getId()==R.id.btn_set_show_time){
            getShowTime();
            MTPushPrivatesApi.setNotificationShowTime(this.getApplicationContext(), showBeginHour, showEndHour, showWeekDay);
        }else if (view.getId()==R.id.btn_reset_show_time){
            MTPushPrivatesApi.resetNotificationShowTime(this.getApplicationContext());
        }else if (view.getId()==R.id.btn_set_silence_time){
            getSilenceTime();
            MTPushPrivatesApi.setNotificationSilenceTime(this.getApplicationContext(), silenceBeginHour, silenceBeginMinute, silenceEndHour, silenceEndMinute);
        }else if (view.getId()==R.id.btn_reset_silence_time){
            MTPushPrivatesApi.resetNotificationSilenceTime(this.getApplicationContext());
        }else if (view.getId()==R.id.btn_set_notification_layout){
            Editable text = etNotificationLayout.getText();
            if(TextUtils.isEmpty(text)){
                Toast.makeText(this,"内容为空",Toast.LENGTH_SHORT).show();
                return;
            }
            int builderId = Integer.parseInt(text.toString());
            NotificationLayout notificationLayout = new NotificationLayout()
                    .setLayoutId(R.layout.custom_notification_layout)
                    .setIconViewId(R.id.iv_notification_icon)
                    .setIconResourceId(R.drawable.mtpush_notification_icon)
                    .setTitleViewId(R.id.tv_notification_title)
                    .setContentViewId(R.id.tv_notification_content)
                    .setTimeViewId(R.id.tv_notification_time);
            MTPushPrivatesApi.setNotificationLayout(this.getApplicationContext(), builderId, notificationLayout);
        }else if (view.getId()==R.id.btn_reset_notification_layout){
            try {
                Editable texts = etNotificationLayout.getText();
                if(TextUtils.isEmpty(texts)){
                    Toast.makeText(this,"内容为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                int resetNotificationLayoutId = Integer.parseInt(texts.toString());
                MTPushPrivatesApi.resetNotificationLayout(this.getApplicationContext(), resetNotificationLayoutId);
            } catch (Exception e) {
                Toast.makeText(this.getApplicationContext(), "请设置正确的自定义通知布局id", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId()==R.id.btn_back){
            finish();
        }
    }

    private void getShowTime() {
        if (TextUtils.isEmpty(etShowStartHour.getText().toString())) {
            showBeginHour = 0;
        } else {
            showBeginHour = Integer.parseInt(etShowStartHour.getText().toString());
        }
        if (TextUtils.isEmpty(etShowEndHour.getText().toString())) {
            showEndHour = 0;
        } else {
            showEndHour = Integer.parseInt(etShowEndHour.getText().toString());
        }
        if (TextUtils.isEmpty(etShowWeekDay.getText().toString())) { // 这里代表空数组，任何时间都不可以展示通知
            showWeekDay = 0;
        } else {
            showWeekDay = Integer.parseInt(etShowWeekDay.getText().toString());
        }
    }

    private void getSilenceTime() {
        if (TextUtils.isEmpty(etSilenceStartHour.getText().toString())) {
            silenceBeginHour = 0;
        } else {
            silenceBeginHour = Integer.parseInt(etSilenceStartHour.getText().toString());
        }
        if (TextUtils.isEmpty(etSilenceStartMinute.getText().toString())) {
            silenceBeginMinute = 0;
        } else {
            silenceBeginMinute = Integer.parseInt(etSilenceStartMinute.getText().toString());
        }
        if (TextUtils.isEmpty(etSilenceEndHour.getText().toString())) {
            silenceEndHour = 0;
        } else {
            silenceEndHour = Integer.parseInt(etSilenceEndHour.getText().toString());
        }
        if (TextUtils.isEmpty(etSilenceEndMinute.getText().toString())) {
            silenceEndMinute = 0;
        } else {
            silenceEndMinute = Integer.parseInt(etSilenceEndMinute.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatusObserver.getInstance().removeListener();
    }

}
