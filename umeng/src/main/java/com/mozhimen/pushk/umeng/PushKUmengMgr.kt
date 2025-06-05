package com.mozhimen.pushk.umeng

import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.libk.umeng.push.UmengPushMgr

/**
 * @ClassName PushKUmengMgr
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/6/3
 * @Version 1.0
 */
@OApiInit_InApplication
object PushKUmengMgr {
    @JvmStatic
    fun init(){
        UmengPushMgr.init()
    }
}