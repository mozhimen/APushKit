package com.mozhimen.pushk.engagelab.cons

/**
 * @ClassName CBuild
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
object CBuild {
    const val ManifestPlaceholders = """
plugins {
    id 'com.android.application'
}

android {
    ...

    defaultConfig {
        // app packageName，需要与控制台上的一样
        applicationId "com.engagelab.app"
        ...
                
        manifestPlaceholders = [
                //appKey，需要与控制台上的一样，与packageName是一对一关系
                ENGAGELAB_PRIVATES_APPKEY : "您的appkey",
                // Engagelab appChannel，用于渠道统计
                ENGAGELAB_PRIVATES_CHANNEL: "developer",
                // Engagelab process，Engagelabsdk工作所在的进程，注意:开头
                ENGAGELAB_PRIVATES_PROCESS: ":remote"
        ]
    }
}
    """
}