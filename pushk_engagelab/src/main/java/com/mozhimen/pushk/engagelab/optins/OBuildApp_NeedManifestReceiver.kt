package com.mozhimen.pushk.engagelab.optins

import com.mozhimen.kotlin.lintk.annors.AManifestRequire
import com.mozhimen.pushk.engagelab.cons.CBuild

/**
 * @ClassName OBuildApp_NeedManifestPlaceholders
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/12/2
 * @Version 1.0
 */
@AManifestRequire(CBuild.ManifestReceiver)
@RequiresOptIn("The api is must add < Receiver > to your app/ AndroidManifest.xml. 这个Api需要将Receiver加到你app/目录下的build.gradle里", RequiresOptIn.Level.WARNING)
annotation class OBuildApp_NeedManifestReceiver
