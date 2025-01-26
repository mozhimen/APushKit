package com.mozhimen.pushk.engagelab.test

import androidx.lifecycle.MutableLiveData
import com.mozhimen.basick.impls.MutableLiveDataStrict
import com.mozhimen.kotlin.elemk.androidx.lifecycle.bases.BaseViewModel
import com.mozhimen.kotlin.utilk.android.content.UtilKPackage
import com.mozhimen.pushk.engagelab.test.utils.ExampleUtil

/**
 * @ClassName MainViewModel
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/11
 * @Version 1.0
 */
class MainViewModel : BaseViewModel() {
    val livePackageName = MutableLiveData(UtilKPackage.getPackageName())
    val liveAppKey = MutableLiveData(ExampleUtil.getAppKey())
    val liveAppChannel = MutableLiveData(ExampleUtil.getAppChannel())
    val liveAppProcess = MutableLiveData(ExampleUtil.getAppProcess())
    val liveServiceName = MutableLiveData(ExampleUtil.getCommonServiceName())
    val liveReceiverName = MutableLiveData(ExampleUtil.getCommonReceiverName())
    val liveUid = MutableLiveData("")
    val liveRid = MutableLiveData("")
}