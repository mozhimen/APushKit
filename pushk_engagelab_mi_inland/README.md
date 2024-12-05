小米国内推送说明
1.引入小米国内推送支持包：mt-sdk-mi.aar。
2.在小米国内推送平台注册一个国内应用，详情参考 厂商通道参数申请指南。
将 app 信息配置到 XIAOMI_APPID 和 XIAOMI_APPKEY，例如：


defaultConfig {
applicationId "com.aurora.app"
......

    manifestPlaceholders = [

            AURORA_PRIVATES_APPKEY : "1c4b749a17f6aca33960a560",
            XIAOMI_APPID           : "MI-2882303761519903247",
            XIAOMI_APPKEY          : "MI-5511990317247",

    ]
}


请使用小米中国大陆版手机版测试。