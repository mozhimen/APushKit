package com.mozhimen.pushk.engagelab.google

/**
 * @ClassName OPlugin_ClassPath_Huawei
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
@RequiresOptIn("""
app build.gradle

plugins {
    id 'com.android.application'
    // google push need，不需要 google 通道，则删除
    // id 'com.google.gms.google-services'
    // huawei push need，不需要 huawei 通道，则删除
    // id 'com.huawei.agconnect'
}
""")
annotation class OPlugin_ClassPath_Google
