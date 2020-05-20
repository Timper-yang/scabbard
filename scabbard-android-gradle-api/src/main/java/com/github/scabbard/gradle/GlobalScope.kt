package com.github.scabbard.gradle

import com.android.build.gradle.internal.scope.GlobalScope

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */

fun GlobalScope.hasDynamicFeatures(): Boolean = when {
    GTE_V3_2 -> GlobalScopeV32.hasDynamicFeatures(this)
    else -> false
}
