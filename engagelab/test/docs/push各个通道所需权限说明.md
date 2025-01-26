# push各个通道所需权限说明

> 除可选权限，必须权限均打进aar包，无需配置

## ENGAGELAB

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    
    <!-- 可选，需自行配置，通知震动 -->
    <uses-permission android:name="android.permission.VIBRATE" />
```

## google

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM"/>
    <uses-permission android:name="android.permission.WAKE_LOCK"/>
    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
```

## huawei

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM"/>
    <uses-permission android:name="com.huawei.android.launcher.permission.CHANGE_BADGE"/>
    <permission android:name="${applicationId}.permission.PROCESS_PUSH_MSG" 
    	android:protectionLevel="signature"/>
    <permission android:name="${applicationId}.permission.PUSH_PROVIDER" 
    	android:protectionLevel="signature"/>
    <permission android:name="${applicationId}.permission.PUSH_WRITE_PROVIDER" 
    	android:protectionLevel="signature"/>
    <uses-permission android:name="${applicationId}.permission.PROCESS_PUSH_MSG"/>
    <uses-permission android:name="${applicationId}.permission.PUSH_PROVIDER"/>
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
    <uses-permission android:name="com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA"/>
    <queries>
        <intent>
            <action android:name="com.apptouch.intent.action.update_hms"/>
        </intent>
        <intent>
            <action android:name="com.huawei.appmarket.intent.action.AppDetail"/>
        </intent>
        <package android:name="com.hisilicon.android.hiRMService"/>
        <intent>
            <action android:name="com.huawei.hms.core.aidlservice"/>
        </intent>
        <intent>
            <action android:name="com.huawei.hms.core"/>
        </intent>
    </queries>
```

## mi

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.VIBRATE"/>
```

## mz

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="com.meizu.flyme.permission.PUSH" />
    
    <!-- 可选，需自行配置，用于兼容 Flyme5 且推送服务是旧版本的情况-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- 可选，需自行配置，兼容 Flyme5 的权限配置-->
    <uses-permission android:name="com.meizu.flyme.push.permission.RECEIVE" />
    <permission
        android:name="${applicationId}.push.permission.MESSAGE"
        android:protectionLevel="signature" />
    <uses-permission android:name="${applicationId}.push.permission.MESSAGE" />
```

## oppo

```
    <!-- 必须 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="com.coloros.mcs.permission.RECIEVE_MCS_MESSAGE" />
    <uses-permission android:name="com.heytap.mcs.permission.RECIEVE_MCS_MESSAGE" />
```

## vivo

```
		<!-- 必须 -->
		<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
		<uses-permission android:name="com.push.permission.UPSTAGESERVICE" />
```



## honor

```
    <!--honor 角标-->
    <uses-permission android:name="com.hihonor.android.launcher.permission.CHANGE_BADGE" />
	<uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```



