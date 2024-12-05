package com.mozhimen.pushk.engagelab.test.listener

import kotlin.concurrent.Volatile

/**
 * @ClassName StatusObserver
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/6 0:54
 * @Version 1.0
 */
class StatusObserver {
    private var onStatusListener: OnStatusListener? = null

    fun addListener(listener: OnStatusListener?) {
        onStatusListener = listener
    }

    fun removeListener() {
        onStatusListener = null
    }

    fun getListener(): OnStatusListener? {
        return onStatusListener
    }

    companion object {
        @Volatile
        var instance: StatusObserver? = null
            get() {
                if (field == null) {
                    synchronized(StatusObserver::class.java) {
                        field = StatusObserver()
                    }
                }
                return field
            }
            private set
    }
}
