package com.mozhimen.pushk.engagelab.test.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;

import java.util.List;

import com.engagelab.privates.common.component.MTCommonReceiver;
import com.engagelab.privates.common.component.MTCommonService;
import com.mozhimen.pushk.engagelab.test.log.ExampleLogger;

public class ExampleUtil {

    private static final String TAG = "ExampleUtil";

    private static final String APP_KEY = "ENGAGELAB_PRIVATES_APPKEY";
    private static final String APP_CHANNEL = "ENGAGELAB_PRIVATES_CHANNEL";
    private static final String APP_PROCESS = "ENGAGELAB_PRIVATES_PROCESS";

    private static final String INTENT_COMMON_SERVICE = "com.engagelab.privates.intent.USER_SERVICE";
    private static final String INTENT_COMMON_RECEIVER = "com.engagelab.privates.intent.USER_RECEIVER";

    private static String appKey;
    private static String appChannel;
    private static String appProcess;

    private static int appVersionCode;
    private static String appVersionName;

    private static String commonServiceName;
    private static String commonReceiverName;

    public static String getAppVersionName(Context context) {
        if (!TextUtils.isEmpty(appVersionName)) {
            return appVersionName;
        }
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersionName = info.versionName;
            return appVersionName;
        } catch (Throwable throwable) {
            ExampleLogger.w(TAG, "getAppVersionName failed: " + throwable.getMessage());
        }
        return "";
    }

    public static int getAppVersionCode(Context context) {
        if (appVersionCode != 0) {
            return appVersionCode;
        }
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appVersionCode = info.versionCode;
            return appVersionCode;
        } catch (Throwable throwable) {
            ExampleLogger.w(TAG, "getAppVersionCode failed: " + throwable.getMessage());
        }
        return 0;
    }

    public static String getAppKey(Context context) {
        if (TextUtils.isEmpty(appKey)) {
            appKey = getMetaData(context, APP_KEY);
        }
        return appKey;
    }

    public static String getAppChannel(Context context) {
        if (TextUtils.isEmpty(appChannel)) {
            appChannel = getMetaData(context, APP_CHANNEL);
        }
        return appChannel;
    }

    public static String getAppProcess(Context context) {
        if (TextUtils.isEmpty(appProcess)) {
            appProcess = getMetaData(context, APP_PROCESS);
        }
        return appProcess;
    }

    public static String getMetaData(Context context, String metaDataName) {
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (info != null && info.metaData != null) {
                return String.valueOf(info.metaData.get(metaDataName));
            }
        } catch (Throwable throwable) {
            ExampleLogger.w(TAG, "getMetaData failed " + throwable.getMessage());
        }
        return "";
    }

    // ************************ Engagelab组件 ************************

    public static String getCommonServiceName(Context context) {
        if (!TextUtils.isEmpty(commonServiceName)) {
            return commonServiceName;
        }
        try {
            Intent intent = new Intent();
            intent.setAction(INTENT_COMMON_SERVICE);
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentServices(intent, 0);
            for (ResolveInfo info : resolveInfoList) {
                ServiceInfo serviceInfo = info.serviceInfo;
                if (serviceInfo == null) {
                    continue;
                }
                String className = serviceInfo.name;
                if (TextUtils.isEmpty(className)) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                // MTCommonService是clazz的父类
                if (MTCommonService.class.isAssignableFrom(clazz)) {
                    return commonServiceName = clazz.getCanonicalName();
                }
            }
        } catch (Throwable throwable) {
            ExampleLogger.w(TAG, "getCommonService failed " + throwable.getMessage());
        }
        return "";
    }

    public static String getCommonReceiverName(Context context) {
        if (!TextUtils.isEmpty(commonReceiverName)) {
            return commonReceiverName;
        }
        try {
            Intent intent = new Intent();
            intent.setAction(INTENT_COMMON_RECEIVER);
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> resolveInfoList = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            for (ResolveInfo info : resolveInfoList) {
                if (info.activityInfo == null) {
                    continue;
                }
                String className = info.activityInfo.name;
                if (TextUtils.isEmpty(className)) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                // MTCommonReceiver是clazz的父类
                if (MTCommonReceiver.class.isAssignableFrom(clazz)) {
                    return commonReceiverName = ((MTCommonReceiver) clazz.newInstance()).getClass().getCanonicalName();
                }
            }
        } catch (Throwable throwable) {
            ExampleLogger.w(TAG, "getCommonReceiver failed " + throwable.getMessage());
        }
        return "";
    }

}
