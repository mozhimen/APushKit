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
@AManifestRequire(CBuild.ManifestPlaceholders)
@RequiresOptIn("The api is must add < addManifestPlaceholders > to your app/ build.gradle. 这个Api需要将addManifestPlaceholders的几个配置加到你app/目录下的build.gradle里", RequiresOptIn.Level.WARNING)
annotation class OBuildApp_NeedManifestPlaceholders()
