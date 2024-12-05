package com.mozhimen.pushk.engagelab.utils

import android.R
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.engagelab.privates.push.api.NotificationLayout

/**
 * @ClassName UtilKNotificationLayout
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/3
 * @Version 1.0
 */
object UtilKNotificationLayout {
    /**
    // 首先设置自定义布局到Engagelab私有云SDK
     */
    @JvmStatic
    fun get(
        @LayoutRes intResLayout: Int,
        @IdRes intResIconId: Int,
        @DrawableRes intResIconDrawable: Int,
        @IdRes intResTitleId: Int,
        @IdRes intResContentId: Int,
        @IdRes intResTimeId: Int,
    ): NotificationLayout =
        NotificationLayout()
            .setLayoutId(intResLayout)
            .setIconViewId(intResIconId)
            .setIconResourceId(intResIconDrawable)
            .setTitleViewId(intResTitleId)
            .setContentViewId(intResContentId)
            .setTimeViewId(intResTimeId)
}