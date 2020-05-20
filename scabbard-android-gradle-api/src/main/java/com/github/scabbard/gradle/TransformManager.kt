package com.github.scabbard.gradle

import com.android.build.api.transform.QualifiedContent
import com.android.build.gradle.internal.pipeline.TransformManager

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */
val SCOPE_PROJECT: MutableSet<in QualifiedContent.Scope> = TransformManager.PROJECT_ONLY

val SCOPE_FULL_WITH_FEATURES: MutableSet<in QualifiedContent.Scope> = when {
    GTE_V3_2 -> TransformManagerV32.SCOPE_FULL_WITH_FEATURES
    else -> TransformManager.SCOPE_FULL_PROJECT
}

val SCOPE_FULL_LIBRARY_WITH_FEATURES: MutableSet<in QualifiedContent.Scope> = when {
    GTE_V3_2 -> TransformManagerV32.SCOPE_FULL_LIBRARY_WITH_FEATURES
    else -> TransformManager.PROJECT_ONLY
}
