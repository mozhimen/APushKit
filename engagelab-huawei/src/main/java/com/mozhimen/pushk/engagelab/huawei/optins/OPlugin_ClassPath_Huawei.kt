package com.mozhimen.pushk.engagelab.huawei.optins

/**
 * @ClassName OPlugin_ClassPath_Huawei
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
@RequiresOptIn("""
project build.gradle

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        // huawei push need
        maven { url 'https://developer.huawei.com/repo/' }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:4.1.0'
        // google push need，and google push need AndroidX
        classpath 'com.google.gms:google-services:4.3.15'
        // huawei push need
        classpath 'com.huawei.agconnect:agcp:1.6.0.300'

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // huawei push need
        maven { url 'https://developer.huawei.com/repo/' }
    }
}

////////////////////////////////////////////////////////////

app build.gradle

plugins {
    id 'com.android.application'
    // google push need，不需要 google 通道，则删除
    // id 'com.google.gms.google-services'
    // huawei push need，不需要 huawei 通道，则删除
    // id 'com.huawei.agconnect'
}
""")
annotation class OPlugin_ClassPath_Huawei
