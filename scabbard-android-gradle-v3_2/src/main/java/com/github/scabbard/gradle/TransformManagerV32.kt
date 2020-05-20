package com.github.scabbard.gradle

import com.android.build.api.transform.QualifiedContent
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.internal.impldep.com.google.common.collect.ImmutableSet

/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
object TransformManagerV32 {

    val SCOPE_FULL_WITH_FEATURES =
        TransformManager.SCOPE_FULL_WITH_FEATURES

    val SCOPE_FULL_LIBRARY_WITH_FEATURES =
        ImmutableSet.Builder<QualifiedContent.ScopeType>().addAll(
            TransformManager.SCOPE_FEATURES
        ).add(QualifiedContent.Scope.PROJECT).build()
}