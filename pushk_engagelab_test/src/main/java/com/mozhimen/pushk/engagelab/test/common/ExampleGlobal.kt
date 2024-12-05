package com.mozhimen.pushk.engagelab.test.common

/**
 * @ClassName ExampleGlobal
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/3
 * @Version 1.0
 */
object ExampleGlobal {
    var isConnectEnable: Boolean = false
    var isNotificationEnable: Boolean = false

    const val TAG_ADD: Int = 1001
    const val TAG_DELETE: Int = 1002
    const val TAG_UPDATE: Int = 1003
    const val TAG_QUERY: Int = 1004

    const val TAG_DELETE_ALL: Int = 1005
    const val TAG_QUERY_ALL: Int = 1006

    const val ALIAS_SET: Int = 1007
    const val ALIAS_GET: Int = 1008
    const val ALIAS_CLEAR: Int = 1009
}