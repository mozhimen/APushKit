package com.mozhimen.pushk.engagelab.mi.inland.optins

/**
 * @ClassName OBuildApp_NeedManifestPlaceholders
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
@RequiresOptIn("""
defaultConfig {
applicationId "com.aurora.app"
......

    manifestPlaceholders = [

            AURORA_PRIVATES_APPKEY : "1c4b749a17f6aca33960a560",
            XIAOMI_APPID           : "MI-2882303761519903247",
            XIAOMI_APPKEY          : "MI-5511990317247",

    ]
}
""")
annotation class OBuildApp_NeedManifestPlaceholders