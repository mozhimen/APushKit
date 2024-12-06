package com.mozhimen.pushk.engagelab.test.component

import android.app.Activity
import com.mozhimen.pushk.engagelab.impls.PushKEngagelabReceiver

/**
 * @ClassName UserReceiver
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:52
 * @Version 1.0
 */
/**
 * 开发者继承MTCommonReceiver，可以获得sdk的方法回调
 * 所有回调均在主线程
 */
class UserReceiver : PushKEngagelabReceiver(){
    override fun getCustomMessageActivity(): Class<out Activity>? {
        return CustomMessageActivity34X::class.java
    }
}