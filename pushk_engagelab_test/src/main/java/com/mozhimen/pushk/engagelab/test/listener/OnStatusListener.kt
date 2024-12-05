package com.mozhimen.pushk.engagelab.test.listener

/**
 * @ClassName OnStatusListener
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:54
 * @Version 1.0
 */
interface OnStatusListener {
    fun onConnectStatus(status: Boolean)
    fun onNotificationStatus(status: Boolean)
}
