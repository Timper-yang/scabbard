package com.github.scabbard.task.spi

import com.android.build.gradle.api.BaseVariant

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
interface VariantProcessor {

    fun process(variant: BaseVariant)

}
