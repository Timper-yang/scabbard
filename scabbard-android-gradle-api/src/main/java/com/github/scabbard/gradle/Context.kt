package com.github.scabbard.gradle

import com.android.build.api.transform.Context
import com.android.build.gradle.internal.pipeline.TransformTask

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */
val Context.task: TransformTask
    get() = when (this) {
        is TransformTask -> this
        else -> javaClass.getDeclaredField("this$1").apply {
            isAccessible = true
        }.get(this).run {
            javaClass.getDeclaredField("this$0").apply {
                isAccessible = true
            }.get(this) as TransformTask
        }
    }