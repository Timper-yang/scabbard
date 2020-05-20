package com.github.scabbard.gradle

import com.android.build.gradle.internal.scope.GlobalScope


/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
object GlobalScopeV32 {
    fun hasDynamicFeatures(scope: GlobalScope): Boolean = scope.hasDynamicFeatures()
}
