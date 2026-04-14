厂商通道集成配置
meizu通道获取不到token，尝试在 gradle.properties 中添加 android.enableR8 = false 进行关闭 R8

魅族通道送达回执配置
完成华为厂商通道 SDK 集成后，需要开发者在Flyme 推送平台开通并配置消息回执，方可把魅族的送达统计等数据回调到AppPush平台，
新加坡数据中心回调地址： https://pushcb.api.engagelab.cc/callback/mz
美国弗吉尼亚数据中心回调地址： https://pushcb-usva.api.engagelab.cc/callback/mz
德国法兰克福数据中心回调地址 https://pushcb-defra.api.engagelab.cc/callback/mz
中国香港数据中心回调地址 https://pushcb-hk.api.engagelab.cc/callback/mz