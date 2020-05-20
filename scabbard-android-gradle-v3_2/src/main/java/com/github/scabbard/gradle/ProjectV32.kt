package com.github.scabbard.gradle

import com.android.build.gradle.options.BooleanOption
import com.android.build.gradle.options.ProjectOptions
import org.gradle.api.Project

/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
object ProjectV32{
    fun isAapt2Enabled(project: Project) = ProjectOptions(project).get(BooleanOption.ENABLE_AAPT2)
}