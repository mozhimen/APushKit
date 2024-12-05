package com.engagelab.app;

import android.app.Application;

import com.engagelab.privates.core.api.Address;
import com.engagelab.privates.core.api.MTCorePrivatesApi;
import com.engagelab.privates.push.api.MTPushPrivatesApi;

/**
 * 用于演示ENGAGELAB-sdk配置
 */
public class MainApplication extends Application {

    private static final String TAG = "MainApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        MTCorePrivatesApi.setTcpSSl(this, false);
        MTCorePrivatesApi.configDebugMode(this, true);
        // 初始化
        MTPushPrivatesApi.init(this);
    }

}
